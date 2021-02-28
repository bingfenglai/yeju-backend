package pers.lbf.yeju.provider.session.service;

import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboReference;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import pers.lbf.yeju.common.core.constant.ServiceStatusConstant;
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

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/**
 * @author 赖柄沣 bingfengdev@aliyun.com
 * @version 1.0
 * @Description TODO
 * @date 2020/11/29 20:05
 */
@DubboService(interfaceClass = ISessionService.class)
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

    private static final String ONLINE_KEY_PREFIX = "yeju:online";

    private static final String SESSION_KEY_PREFIX = "yeju:session::";

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
    @CacheEvict(cacheNames = "yeju:session", key = "#principal")
    @Override
    public void destroySession(String principal) {
        redisTemplate.delete(ONLINE_KEY_PREFIX + principal);
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
     * @param principal 员工账号、用户手机号
     * @return void
     * @author 赖柄沣 bingfengdev@aliyun.com
     * @version 1.0
     * @date 2021/2/5 14:20
     */
    @Cacheable(cacheNames = "yeju:session", key = "#principal")
    @Override
    public IResult<SessionDetails> initSession(String principal) throws ServiceException {
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
        Boolean flag = redisTemplate.hasKey(SESSION_KEY_PREFIX + principal);

        return Result.ok(flag);
    }


}
