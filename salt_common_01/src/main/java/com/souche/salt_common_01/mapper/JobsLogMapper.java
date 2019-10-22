package com.souche.salt_common_01.mapper;

import com.souche.salt_common_01.entity.JobsLog;
import com.souche.salt_common_01.entity.JobsLogExample;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

public interface JobsLogMapper {
    int countByExample(JobsLogExample example);

    int deleteByExample(JobsLogExample example);

    int insert(JobsLog record);

    int insertSelective(JobsLog record);

    List<JobsLog> selectByExample(JobsLogExample example);

    int updateByExampleSelective(@Param("record") JobsLog record, @Param("example") JobsLogExample example);

    int updateByExample(@Param("record") JobsLog record, @Param("example") JobsLogExample example);


    List<JobsLog> queryAllJoblog();

    List<JobsLog> conditionQuery(Map<String,String> map);


    JobsLog selectById(String jobLogId);
}
