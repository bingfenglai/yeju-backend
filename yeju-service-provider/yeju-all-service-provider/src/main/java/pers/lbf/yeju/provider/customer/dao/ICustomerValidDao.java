package pers.lbf.yeju.provider.customer.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import pers.lbf.yeju.common.domain.entity.CustomerValid;

/**
 * 客户信息表，实名认证完成后搬table_business_customer_valid.所属账户超半年未登录系统，客户信(TableBusinessCustomerValid)表数据库访问层
 *
 * @author 赖柄沣 bingfengdev@aliyun.com
 * @since 2021-02-08 13:14:46
 */
public interface ICustomerValidDao extends BaseMapper<CustomerValid> {

}