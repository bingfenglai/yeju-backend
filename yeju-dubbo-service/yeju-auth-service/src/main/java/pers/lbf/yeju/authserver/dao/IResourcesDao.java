package pers.lbf.yeju.authserver.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import pers.lbf.yeju.common.domain.entity.Resources;

/**
 * (受保护的资源)权限表，包括菜单和API(TableSystemResources)表数据库访问层
 *
 * @author 赖柄沣 bingfengdev@aliyun.com
 * @since 2020-12-13 09:48:19
 */
public interface IResourcesDao extends BaseMapper<Resources> {


}
