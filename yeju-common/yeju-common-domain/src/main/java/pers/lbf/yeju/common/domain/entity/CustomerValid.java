package pers.lbf.yeju.common.domain.entity;

import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;

/**
 * 客户信息表，实名认证完成后搬table_business_customer_valid.所属账户超半年未登录系统，客户信(TableBusinessCustomerValid)表实体类
 *
 * @author 赖柄沣 bingfengdev@aliyun.com
 * @since 2021-02-08 13:14:40
 */
@TableName("table_business_customer_valid")
public class CustomerValid extends Customer implements Serializable {

    /**
     * 获取主键值
     *
     * @return 主键值
     */
    @Override
    protected Serializable pkVal() {
        return this.customerId;
    }
}