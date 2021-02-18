package pers.lbf.yeju.provider.dict.info.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import pers.lbf.yeju.common.domain.entity.DataDictionaryInfo;
import pers.lbf.yeju.service.interfaces.dictionary.pojo.SimpleLabelAndValueBean;

import java.util.LinkedList;

/**
 * 数据字典信息表(TableSystemDataDictionaryInfo)表数据库访问层
 *
 * @author 赖柄沣 bingfengdev@aliyun.com
 * @since 2021-02-13 15:02:45
 */
public interface IDataDictionaryInfoDao extends BaseMapper<DataDictionaryInfo> {

    @Select("SELECT \n" +
            "  t.`dictionary_label` AS label,\n" +
            "  t.`dictionary_value` AS VALUE \n" +
            "FROM\n" +
            "  table_system_data_dictionary_info t \n" +
            "WHERE t.`type_id` = \n" +
            "  (SELECT \n" +
            "    t1.`data_dictionary_type_id` \n" +
            "  FROM\n" +
            "    table_system_data_dictionary_type t1 \n" +
            "  WHERE t1.`type` = #{type} \n" +
            "  LIMIT 1)")
    LinkedList<SimpleLabelAndValueBean> selectLabelAndValueByType(@Param("type") String type) throws RuntimeException;
}