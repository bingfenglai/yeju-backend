package pers.lbf.yeju.service.interfaces.auth.interfaces;

import pers.lbf.yeju.common.core.exception.service.ServiceException;
import pers.lbf.yeju.common.core.result.IResult;
import pers.lbf.yeju.common.core.result.PageResult;
import pers.lbf.yeju.service.interfaces.auth.dto.OnlineInfoBean;
import pers.lbf.yeju.service.interfaces.auth.dto.SessionDetails;

/**
 * @author 赖柄沣 bingfengdev@aliyun.com
 * @version 1.0
 * @Description TODO
 * @date 2020/11/29 14:07
 */
public interface ISessionService {


    void addOnline(OnlineInfoBean onlineInfoBean) throws ServiceException;

    PageResult<OnlineInfoBean> findPage(Long currentPage, Long size) throws ServiceException;



    /**
     * 登出方法，销毁会话信息
     * @param principal 账号
     * @return r
     */

    void destroySession(String principal);

    /** 登出方法，销毁会话信息
     * @author 赖柄沣 bingfengdev@aliyun.com
     * @version 1.0
     * @date 2021/2/20 22:19
     * @param onlineInfoBean online info
     */
    void destroySession(OnlineInfoBean onlineInfoBean) throws ServiceException;


    /**
     * 初始化会话信息，认证成功后将会话信息存入redis
     * @param principal 员工账号、用户手机号
     * @throws ServiceException e
     * @return SessionDetails
     */
    IResult<SessionDetails> initSession(String principal) throws ServiceException;

    IResult<Boolean> isExpired(String principal) throws ServiceException;


}
