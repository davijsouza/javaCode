package com.souche.salt_common_01.mapper;

import com.souche.salt_common_01.entity.TimeJobs;
import com.souche.salt_common_01.entity.TimeJobsExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TimeJobsMapper {
    int countByExample(TimeJobsExample example);

    int deleteByExample(TimeJobsExample example);

    int deleteByPrimaryKey(String id);

    int insert(TimeJobs record);

    int insertSelective(TimeJobs record);

    List<TimeJobs> selectByExample(TimeJobsExample example);

    TimeJobs selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") TimeJobs record, @Param("example") TimeJobsExample example);

    int updateByExample(@Param("record") TimeJobs record, @Param("example") TimeJobsExample example);

    int updateByPrimaryKeySelective(TimeJobs record);

    int updateByPrimaryKey(TimeJobs record);



    List<TimeJobs> timeJobBy(@Param("name") String name, @Param("status") String status, @Param("user") String user,@Param("jobId") String jobId);

}
