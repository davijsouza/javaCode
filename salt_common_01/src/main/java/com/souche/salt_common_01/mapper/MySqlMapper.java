package com.souche.salt_common_01.mapper;

import com.souche.salt_common_01.entity.*;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface MySqlMapper {
    Integer addrelation(String instanceId);
   // @Select("select * from relation")
    List<Relation> findAll();

    List<JobsLog> findFailJob();

    List<JobsLog> failJobs(String loginName);
}
