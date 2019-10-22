package com.souche.salt_common_01.mapper;

import com.souche.salt_common_01.entity.Scripts;
import com.souche.salt_common_01.entity.ScriptsExample;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

public interface ScriptsMapper {
    int countByExample(ScriptsExample example);

    int deleteByExample(ScriptsExample example);

    int deleteByPrimaryKey(String id);

    int insert(Scripts record);

    int insertSelective(Scripts record);

    List<Scripts> selectByExampleWithBLOBs(ScriptsExample example);

    List<Scripts> selectByExample(ScriptsExample example);

    Scripts selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") Scripts record, @Param("example") ScriptsExample example);

    int updateByExampleWithBLOBs(@Param("record") Scripts record, @Param("example") ScriptsExample example);

    int updateByExample(@Param("record") Scripts record, @Param("example") ScriptsExample example);

    int updateByPrimaryKeySelective(Scripts record);

    int updateByPrimaryKeyWithBLOBs(Scripts record);

    int updateByPrimaryKey(Scripts record);

    List<Scripts> findAllScripts(Map<String, String> param);

    List<Scripts> queryPriScript(@Param("user") String user,@Param("scriptName")  String scriptName);

    Scripts selectByJobIdAndOrderNum(@Param("jobId") String jobId,  @Param("step") int step);



}
