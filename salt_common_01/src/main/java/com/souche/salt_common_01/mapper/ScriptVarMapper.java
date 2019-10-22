package com.souche.salt_common_01.mapper;

import com.souche.salt_common_01.entity.ScriptVar;
import com.souche.salt_common_01.entity.ScriptVarExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ScriptVarMapper {
    int countByExample(ScriptVarExample example);

    int deleteByExample(ScriptVarExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(ScriptVar record);

    int insertSelective(ScriptVar record);

    List<ScriptVar> selectByExample(ScriptVarExample example);

    ScriptVar selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") ScriptVar record, @Param("example") ScriptVarExample example);

    int updateByExample(@Param("record") ScriptVar record, @Param("example") ScriptVarExample example);

    int updateByPrimaryKeySelective(ScriptVar record);

    int updateByPrimaryKey(ScriptVar record);
}