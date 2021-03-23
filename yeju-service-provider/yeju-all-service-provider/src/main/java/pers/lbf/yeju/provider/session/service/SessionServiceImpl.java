package pers.lbf.yeju.provider.session.service;

import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboReference;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import pers.lbf.yeju.common.core.constant.ServiceStatusConstant;
import pers.lbf.yeju.common.core.constant.TokenConstant;
import pers.lbf.yeju.common.core.exception.service.ServiceException;
import pers.lbf.yeju.common.core.result.IResult;
import pers.lbf.yeju.common.core.result.PageResult;
import pers.lbf.yeju.common.core.result.Result;
import pers.lbf.yeju.service.interfaces.auth.dto.AccountDetailsInfoBean;
import pers.lbf.yeju.service.interfaces.auth.enums.AccountOwnerTypeEnum;
import pers.lbf.yeju.service.interfaces.auth.interfaces.IAccountService;
import pers.lbf.yeju.service.interfaces.auth.interfaces.IResourcesService;
import pers.lbf.yeju.service.interfaces.auth.interfaces.IRoleService;
import pers.lbf.yeju.service.interfaces.customer.ICustomerValidService;
import pers.lbf.yeju.service.interfaces.customer.pojo.SimpleCustomerInfoBean;
import pers.lbf.yeju.service.interfaces.platfrom.employee.IEmployeeService;
import pers.lbf.yeju.service.interfaces.platfrom.pojo.SimpleEmployeeInfoBean;
import pers.lbf.yeju.service.interfaces.session.ISessionService;
import pers.lbf.yeju.service.interfaces.session.pojo.OnlineInfoBean;
import pers.lbf.yeju.service.interfaces.session.pojo.SessionAccount;
import pers.lbf.yeju.service.interfaces.session.pojo.SessionDetails;

import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * @author 赖柄沣 bingfengdev@aliyun.com
 * @version 1.0
 * @Description TODO
 * @date 2020/11/29 20:05
 */
@DubboService(interfaceClass = ISessionService.class, timeout = 10000, retries = 0)
@Slf4j
@Service(value = "sessionService")
public class SessionServiceImpl implements ISessionService {

    @DubboReference
    private IAccountService accountService;

    @DubboReference
    private IRoleService roleService;

    @DubboReference
    private IResourcesService resourcesService;

    @DubboReference
    private IEmployeeService employeeService;

    @DubboReference
    private ICustomerValidService customerValidService;

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    public static final String ONLINE_KEY_PREFIX = "yeju:online::";

    public static final String SESSION_KEY_PREFIX = "yeju:session::";

    @Override
    public void addOnline(OnlineInfoBean onlineInfoBean) throws ServiceException {
        redisTemplate.opsForValue().set(ONLINE_KEY_PREFIX + onlineInfoBean.getPrincipal(), onlineInfoBean);
    }

    @Override
    public PageResult<OnlineInfoBean> findPage(Long currentPage, Long size) throws ServiceException {
        long start = 0;
        long end = 0;

        int totalPage = 0;


        Set<String> keys = redisTemplate.keys(ONLINE_KEY_PREFIX + "*");

        List<OnlineInfoBean> results = new LinkedList<>();

        List<String> keyList;
        if (keys != null && keys.size() > 0) {
            keyList = new ArrayList<>(keys);
        } else {
            keyList = new ArrayList<>();
        }

        if (keyList.size() < size) {
            totalPage = 1;
        } else {
            totalPage = Math.toIntExact(keyList.size() / size);
            totalPage = (int) Math.ceil(totalPage);
        }

        if (currentPage > 1L && currentPage <= totalPage) {
            start = currentPage * size;
        } else {
            start = (totalPage - 1) * size;
        }


        if ((start + size) < keyList.size()) {
            end = start + size;
        } else {
            end = keyList.size() - start - 1;
        }

        if (keyList.size() < end) {
            end = keyList.size();
        }


        log.info("start {}", start);


//        if (keyList.size()==end){
//            end = keyList.size()-1;
//        }


        for (long i = start; i <= end; ) {
            String key = keyList.get(Math.toIntExact(i));
            Object o = redisTemplate.opsForValue().get(key);
            results.add((OnlineInfoBean) o);
            i++;
        }


        Long total = (long) keyList.size();


        return PageResult.ok(total, currentPage, size, results);
    }

    /**
     * 登出方法，销毁会话信息
     *
     * @param principal 账号
     * @return r
     */
    @Override
    public void destroySession(String principal) throws ServiceException {
        String onLineKey = ONLINE_KEY_PREFIX + principal;

        OnlineInfoBean onlineInfo = (OnlineInfoBean) redisTemplate.opsForValue().get(onLineKey);

        if (onlineInfo == null) {
            return;
        }

        String currentSessionKey = SESSION_KEY_PREFIX + onlineInfo.getSessionId();

        redisTemplate.delete(currentSessionKey);

        redisTemplate.delete(onLineKey);


    }

    /**
     * 登出方法，销毁会话信息
     *
     * @param onlineInfoBean online info
     * @author 赖柄沣 bingfengdev@aliyun.com
     * @version 1.0
     * @date 2021/2/20 22:19
     */
    @CacheEvict(cacheNames = "yeju:session", key = "#onlineInfoBean.principal")
    @Override
    public void destroySession(OnlineInfoBean onlineInfoBean) throws ServiceException {

        redisTemplate.delete(ONLINE_KEY_PREFIX + onlineInfoBean.getPrincipal());

    }

    /**
     * 初始化会话信息，认证成功后将会话信息存入redis
     *
     * @param sessionId 员工账号、用户手机号
     * @param principal
     * @return void
     * @author 赖柄沣 bingfengdev@aliyun.com
     * @version 1.0
     * @date 2021/2/5 14:20
     */
    //@Cacheable(cacheNames = "yeju:session", key = "#sessionId")
    @Override
    public IResult<SessionDetails> initSession(String sessionId, String principal) throws ServiceException {

        return initSession(sessionId, principal, null);

    }

    @Override
    public IResult<SessionDetails> initSession(String sessionId, String principal, Long expired) throws ServiceException {

        SessionDetails sessionDetails;

        // 1. 查询账户详情信息
        IResult<AccountDetailsInfoBean> accountResult =
                accountService.findAccountDetailsByPrincipal(principal);
        log.info("获取account: {}", accountResult.getData());
        String accountType = accountService.getAccountType(principal).getData();


        //查询账户所属用户信息
        if (accountType.equals(AccountOwnerTypeEnum.Internal_account.getValue())) {
            log.info("账号为: 内部账号");
            IResult<SimpleEmployeeInfoBean> employeeInfoResult =
                    employeeService.findInfoByEmployeeId(
                            accountResult.getData().getSubjectId());

            sessionDetails = new SessionDetails<SimpleEmployeeInfoBean>();
            sessionDetails.setSubjectDetails(employeeInfoResult.getData());
            log.info("获取到员工信息：{}", employeeInfoResult.getData().toString());
        } else {
            IResult<SimpleCustomerInfoBean> infoBeanIResult = customerValidService.findDetailsById(accountResult.getData().getSubjectId());
            sessionDetails = new SessionDetails<SimpleCustomerInfoBean>();
            sessionDetails.setSubjectDetails(infoBeanIResult.getData());


        }
        sessionDetails.setAccountDetailsInfo(this.accountDetailsToSessionAccount(accountResult.getData()));


        // 2. 查询账户关联的角色
        IResult<List<String>> roleListResult = roleService.getRoleListByPrincipal(principal);

        if (roleListResult.getCode().equals(ServiceStatusConstant.SUCCESSFUL_OPERATION_CODE)) {
            sessionDetails.setRoles(roleListResult.getData());
        }
        // 3. 查询账户（角色）关联的资源
        IResult<List<String>> resourceListResult = resourcesService.findAuthorityListByPrincipal(principal);
        if (resourceListResult.getCode().equals(ServiceStatusConstant.SUCCESSFUL_OPERATION_CODE)) {
            sessionDetails.setResources(resourceListResult.getData());
        }
        log.info("会话初始化成功{}", principal);

        // 如果会话过期时间为空 则使用默认的会话过期时间
        if (expired == null) {
            expired = Long.valueOf(TokenConstant.DEFAULT_SESSION_EXPIRES_MINUTE_AT) * 60;
        }

        String sessionKey = SESSION_KEY_PREFIX + sessionId;
        redisTemplate.opsForValue().set(sessionKey, sessionDetails);
        redisTemplate.expire(sessionKey, expired, TimeUnit.SECONDS);
        return Result.ok(sessionDetails);
    }

    @Override
    public IResult<SessionDetails> getSessionDetails(String sessionId) throws ServiceException {
        SessionDetails sessionDetails = (SessionDetails) redisTemplate.opsForValue().get(SESSION_KEY_PREFIX + sessionId);
        return Result.ok(sessionDetails);
    }

    private SessionAccount accountDetailsToSessionAccount(AccountDetailsInfoBean data) {
        SessionAccount sessionAccount = new SessionAccount();
        sessionAccount.setAccountNumber(data.getAccountNumber());
        sessionAccount.setPhoneNumber(data.getPhoneNumber());
        sessionAccount.setLastLoginAddress(data.getLastLoginAddress());
        sessionAccount.setLastLoginDate(data.getLastLoginDate());
        sessionAccount.setAccountStatus(data.getAccountStatus());
        sessionAccount.setAccountLevel(data.getAccountLevel());
        sessionAccount.setAccountType(data.getAccountType());
        return sessionAccount;
    }

    @Override
    public IResult<Boolean> isExpired(String principal) throws ServiceException {
        String pattern = SESSION_KEY_PREFIX + principal + "*";
        Set<String> keys = redisTemplate.keys(pattern);

        Boolean flag = keys != null && keys.size() > 0;

        return Result.ok(flag);
    }

    /**
     * 使会话过期
     *
     * @param principal
     * @param expired
     * @param timeUnit
     * @return pers.lbf.yeju.common.core.result.IResult<java.lang.Boolean>
     * @author 赖柄沣 bingfengdev@aliyun.com
     * @version 1.0
     * @date 2021/3/20 11:18
     */
    @Override
    public IResult<Boolean> expired(String principal, Long expired, TimeUnit timeUnit) throws ServiceException {

        String pattern = SESSION_KEY_PREFIX + principal + "*";
        Set<String> keys = redisTemplate.keys(pattern);

        if (keys != null && keys.size() > 0) {
            for (String key : keys) {
                redisTemplate.expire(key, expired, timeUnit);
            }
        }

        String onlineKey = SESSION_KEY_PREFIX + principal;
        redisTemplate.expire(onlineKey, expired, timeUnit);

        return Result.success();
    }

    /**
     * 使会话过期
     *
     * @param principal
     * @param expired
     * @param timeout
     * @return pers.lbf.yeju.common.core.result.IResult<java.lang.Boolean>
     * @author 赖柄沣 bingfengdev@aliyun.com
     * @version 1.0
     * @date 2021/3/20 11:19
     */
    @Override
    public IResult<Boolean> expiredAt(String principal, String expired, Date timeout) throws ServiceException {

        String pattern = SESSION_KEY_PREFIX + principal + "*";
        Set<String> keys = redisTemplate.keys(pattern);

        if (keys != null && keys.size() > 0) {
            for (String key : keys) {
                redisTemplate.expireAt(key, timeout);
            }
        }

        return Result.success();
    }


}
