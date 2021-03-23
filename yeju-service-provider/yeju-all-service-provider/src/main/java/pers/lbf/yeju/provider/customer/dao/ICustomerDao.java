package pers.lbf.yeju.provider.customer.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import pers.lbf.yeju.common.domain.entity.Customer;
import pers.lbf.yeju.service.interfaces.customer.pojo.CustomerAuthenticationArgs;

/**
 * 客户信息表，实名认证完成后搬table_business_customer_valid.所属账户超半年未登录系统，客户信(TableBusinessCustomerValid)表数据库访问层
 *
 * @author 赖柄沣 bingfengdev@aliyun.com
 * @since 2021-02-08 13:14:46
 */
public interface ICustomerDao extends BaseMapper<Customer> {


    @Update("UPDATE \n" +
            "  table_business_customer t \n" +
            "SET\n" +
            "  t.`account_id` = #{accountId} \n" +
            "WHERE t.`customer_id` = #{customerId} \n" +
            "LIMIT 1 ")
    void initAccountIdByCustomerId(@Param("customerId") Long customerId, @Param("accountId") Long accountId) throws RuntimeException;

    @Update("UPDATE \n" +
            "  table_business_customer t \n" +
            "SET\n" +
            "  t.`customer_name` = #{args.customerName},\n" +
            "  t.`city` = #{args.city},\n" +
            "  t.`customer_status` = #{args.customerStatus},\n" +
            "  t.`gender` = #{args.gender},\n" +
            "  t.`province` = #{args.province},\n" +
            "  t.`update_time` = #{args.updateTime},\n" +
            "  t.`changed_by` = #{args.changedBy} \n" +
            "WHERE t.`customer_id` = #{args.customerId} \n" +
            "LIMIT 1 ;")
    int saveAuthenticateInfo(@Param("args") CustomerAuthenticationArgs args) throws RuntimeException;

    @Select("SELECT \n" +
            "  COUNT(t.`customer_id`) \n" +
            "FROM\n" +
            "  table_business_customer t \n" +
            "WHERE t.`phone_number` = '17330937086' \n" +
            "LIMIT 1 ;")
    boolean isExist(String phoneNumber);
}