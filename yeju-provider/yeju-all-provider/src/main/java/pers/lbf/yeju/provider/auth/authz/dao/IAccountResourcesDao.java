package pers.lbf.yeju.provider.auth.authz.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import pers.lbf.yeju.common.domain.entity.AccountResources;

/**
 * 这是一张冗余的关系表。主要是用来便于权限查询(AccountResources)表数据库访问层
 *
 * @author 赖柄沣 bingfengdev@aliyun.com
 * @since 2020-12-13 10:50:52
 */
public interface IAccountResourcesDao extends BaseMapper<AccountResources> {
}
