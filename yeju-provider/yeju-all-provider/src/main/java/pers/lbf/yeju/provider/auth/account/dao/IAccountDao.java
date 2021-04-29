package pers.lbf.yeju.provider.auth.account.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import pers.lbf.yeju.common.domain.entity.Account;

/**
 * @author 赖柄沣 bingfengdev@aliyun.com
 * @version 1.0
 * @Description TODO
 * @date 2020/11/29 20:24
 */
public interface IAccountDao extends BaseMapper<Account> {

    @Select("SELECT \n" +
            "  COUNT(t.`account_id`) \n" +
            "FROM\n" +
            "  table_system_account t \n" +
            "WHERE t.`phone_number` = #{phoneNumber} \n" +
            "LIMIT 1 ;")
    boolean isExitPhoneNumber(String phoneNumber) throws RuntimeException;

    @Select("SELECT \n" +
            "  * \n" +
            "FROM\n" +
            "  table_system_account t \n" +
            "WHERE t.`subject_id` = #{subjectId} \n" +
            "  AND t.`account_type` = #{accountType} ;")
    Long selectIdBySubjectIdAndAccountType(
            @Param("subjectId") Long subjectId,
            @Param("accountType") String accountType) throws RuntimeException;

}
