package pers.lbf.yeju.provider.dict.type.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import pers.lbf.yeju.common.domain.entity.DataDictionaryType;

/**
 * 数据字典类型表,用于简单的动态配置(TableSystemDataDictionaryType)表数据库访问层
 *
 * @author 赖柄沣 bingfengdev@aliyun.com
 * @since 2021-02-13 15:03:23
 */
public interface IDataDictionaryTypeDao extends BaseMapper<DataDictionaryType> {


    @Select("select t.data_dictionary_type_id " +
            "from table_system_data_dictionary_type t " +
            "where t.type = #{type} " +
            "limit 1")
    Long selectOneByType(@Param("type") String type) throws RuntimeException;

}