package pers.lbf.yeju.provider.account.service;


import org.apache.dubbo.config.annotation.DubboService;
import pers.lbf.yeju.service.interfaces.auth.interfaces.IAccountResourcesService;


/**
 * 这是一张冗余的关系表。主要是用来便于权限查询(AccountResources)表服务实现类
 *
 * @author 赖柄沣 bingfengdev@aliyun.com
 * @since 2020-12-13 10:50:53
 */
@DubboService(interfaceClass = IAccountResourcesService.class)
public class AccountResourcesServiceImpl implements IAccountResourcesService {


}
