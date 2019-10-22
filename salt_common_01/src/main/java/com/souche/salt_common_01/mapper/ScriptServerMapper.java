package com.souche.salt_common_01.mapper;

import com.souche.salt_common_01.entity.ScriptServer;
import com.souche.salt_common_01.entity.ScriptServerExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ScriptServerMapper {
    int countByExample(ScriptServerExample example);

    int deleteByExample(ScriptServerExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(ScriptServer record);

    int insertSelective(ScriptServer record);

    List<ScriptServer> selectByExample(ScriptServerExample example);

    ScriptServer selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") ScriptServer record, @Param("example") ScriptServerExample example);

    int updateByExample(@Param("record") ScriptServer record, @Param("example") ScriptServerExample example);

    int updateByPrimaryKeySelective(ScriptServer record);

    int updateByPrimaryKey(ScriptServer record);
}