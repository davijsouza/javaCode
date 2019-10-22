package com.souche.salt_common_01.mapper;

import com.souche.salt_common_01.entity.*;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

public interface JobMapper {
    int countByExample(JobExample example);

    int deleteByExample(JobExample example);

    int deleteByPrimaryKey(String id);

    int insert(Job record);

    int insertSelective(Job record);

    List<Job> selectByExample(JobExample example);

    Job selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") Job record, @Param("example") JobExample example);

    int updateByExample(@Param("record") Job record, @Param("example") JobExample example);

    int updateByPrimaryKeySelective(Job record);

    int updateByPrimaryKey(Job record);
    /*my*/
    /*查询所有的的常见job*/
    List<Job>  queryAlljobs(Map<String,String> map);

     SonJob  queryJob(@Param("id") String id);

    List<Map<String,String>> findIdandSort();

    List<Job> queryPrijobs(Map<String, String> param);

    void delAboutScript(String id);

    List<ScriptSon> jobScripts(String id);

    void delAboutSever(String id);

    void deleteAboutVars(String id);
}
