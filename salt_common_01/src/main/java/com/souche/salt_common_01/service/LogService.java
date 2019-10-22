package com.souche.salt_common_01.service;

import com.github.pagehelper.PageInfo;
import com.souche.salt_common_01.entity.ExecuteLog;
import com.souche.salt_common_01.entity.JobScriptLog;
import com.souche.salt_common_01.entity.JobsLog;
import com.souche.salt_common_01.utils.TaotaoResult;

import java.util.List;
import java.util.Map;

public interface LogService {
    List<ExecuteLog> queryAllExecuteLog();
    /*  查询所有日志 */
    ExecuteLog queryExecuteLog(int id);
    /*根据ID 查询一条日志*/

    /*查询所有的作业日志*/
    PageInfo<JobsLog> queryAllJobsLog(String jobName, String user, String jobType, String status, Integer pageNum ,Integer pageSize);
    /*
    * 查询某条执行过的作业 包含哪些脚本
    * */
    List<JobScriptLog> findJobScript(String startTime,String endTime,String JobId);
    List<JobScriptLog> stepJobLogScript(String JobId);
    /*查询日志记录中某个job的具体某个脚本执行情况*/

    List<Map<String,String>> findJobScriptResult(String jobLogId, String scriptId);

    TaotaoResult countAll();
}
