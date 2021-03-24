package pers.lbf.yeju.provider.auth.resource.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import pers.lbf.yeju.common.domain.entity.Resource;

import java.util.List;

/**
 * (受保护的资源)权限表，包括菜单和API(TableSystemResources)表数据库访问层
 *
 * @author 赖柄沣 bingfengdev@aliyun.com
 * @since 2020-12-13 09:48:19
 */
public interface IResourcesDao extends BaseMapper<Resource> {

    /**
     * 查询该账户下的权限
     * @author 赖柄沣 bingfengdev@aliyun.com
     * @version 1.0
     * @date 2020/12/23 10:08
     * @param account 系统账户
     * @return resource_code_list
     * @throws RuntimeException e
     */
    @Select("select t.resource_code from table_system_resources t " +
            " where t.resource_id in (" +
            " select t1.resource_id from t_r_account_resources t1" +
            " where t1.account_number = #{account})")
    List<String> findResourceListByAccount(String account) throws RuntimeException;



    /**
     * 查询该账户下的权限
     * @author 赖柄沣 bingfengdev@aliyun.com
     * @version 1.0
     * @date 2020/12/23 10:14
     * @param phoneNumber 手机号
     * @return resource_code_list
     * @throws RuntimeException e
     */
    @Select("select t.resource_code from table_system_resources t " +
            " where t.resource_id in (" +
            " select t1.resource_id from t_r_account_resources t1 " +
            " where t1.phone_number = #{phoneNumber})")
    List<String> findResourceListByPhoneNumber(String phoneNumber) throws RuntimeException;


    @Select(
            "<script> " +
                    "select t.* from table_system_resources t where (t.resource_type = 0 or t.resource_type = 3) and t.parent_menu_id in"  +
                    " <foreach item='item' index='index' collection='parentMenuIds' open='(' separator=',' close=')'> " +
                    " #{item} " +
                    " </foreach> " +
                    "  " +
                    " </script>"
    )
    List<Resource> findChildrenResources(@Param("parentMenuIds") List<Long> parentMenuIds) throws RuntimeException;
}
