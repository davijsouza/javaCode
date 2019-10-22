package com.souche.salt_common_01.mapper;

import com.souche.salt_common_01.entity.ExecuteLog;
import com.souche.salt_common_01.entity.ExecuteLogExample;

import java.util.Date;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ExecuteLogMapper {
    int countByExample(ExecuteLogExample example);

    int deleteByExample(ExecuteLogExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(ExecuteLog record);

    int insertSelective(ExecuteLog record);

    List<ExecuteLog> selectByExampleWithBLOBs(ExecuteLogExample example);

    List<ExecuteLog> selectByExample(ExecuteLogExample example);

    ExecuteLog selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") ExecuteLog record, @Param("example") ExecuteLogExample example);

    int updateByExampleWithBLOBs(@Param("record") ExecuteLog record, @Param("example") ExecuteLogExample example);

    int updateByExample(@Param("record") ExecuteLog record, @Param("example") ExecuteLogExample example);

    int updateByPrimaryKeySelective(ExecuteLog record);

    int updateByPrimaryKeyWithBLOBs(ExecuteLog record);

    int updateByPrimaryKey(ExecuteLog record);

    /*我自己的方法*/

    /*查询某个作业日志下的脚本*/
    List<ExecuteLog> jobLogScript(@Param("startTime")String startTime,@Param("endTime")String endTime,@Param("jobId")String jobId);

   /*查询某个脚本的执行结果*/
   List<ExecuteLog> jobLogScripResult(@Param("startTime")String startTime,@Param("endTime")String endTime,@Param("scriptName")String scriptName);

   /* 查找执行的服务器数量*/
    int getServerNum(String startTime, String endTime, String jobId);

    List<ExecuteLog> jobLogScripResult2(String jobLogId, String scriptId);
}
