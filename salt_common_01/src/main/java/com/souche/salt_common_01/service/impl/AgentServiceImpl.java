package com.souche.salt_common_01.service.impl;

import com.souche.salt_common_01.entity.ExecuteLog;
import com.souche.salt_common_01.entity.ExecuteLogExample;
import com.souche.salt_common_01.mapper.ExecuteLogMapper;
import com.souche.salt_common_01.service.AgentService;
import com.souche.salt_common_01.utils.HttpClientUtil;
import com.souche.salt_common_01.utils.TaotaoResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Slf4j
@Service
public class AgentServiceImpl implements AgentService {
    @Autowired
    private ExecuteLogMapper executeLogMapper;
    @Override
    public void updateLog(String stdout, String logId) {
       // log.info(stdout+"====stdout");

        ExecuteLogExample example=new ExecuteLogExample();
        example.createCriteria().andServerEqualTo(logId);
        List<ExecuteLog> executeLogs=executeLogMapper.selectByExampleWithBLOBs(example);
        if (executeLogs!=null&& executeLogs.size()>0){
            ExecuteLog executeLog=executeLogs.get(0);
            String  out="";
            if (executeLog.getOutput()==null){
                out=stdout;
            }else if(stdout!=null){
                out=executeLog.getOutput()+stdout;

            }else {
                out=executeLog.getOutput();
            }
            executeLog.setOutput(out);
            executeLogMapper.updateByPrimaryKeyWithBLOBs(executeLog);
        }
    }

    @Override
    public TaotaoResult doshell(String content, String vars, String account, List<String> ips) {



        return null;
    }

    @Override
    public void updateRetcode(String pid, String logId, String retcode) {
        System.err.println(retcode+"====recode");
        if(retcode==null){

        }else {
            //脚本执行结束
            ExecuteLogExample example=new ExecuteLogExample();
            example.createCriteria().andServerEqualTo(logId);
            List<ExecuteLog> executeLogs=executeLogMapper.selectByExampleWithBLOBs(example);
            if (executeLogs!=null&& executeLogs.size()>0){
                ExecuteLog executeLog=executeLogs.get(0);
                Date endtime=new Date();
                executeLog.setEndtime(new Date());
                String totalTime=String.format("%.3f",(endtime.getTime()-executeLog.getStarttime().getTime())*1.0/1000);
                executeLog.setTotaltime(totalTime);
                if(!"0".equals(retcode)){
                    executeLog.setStatus("执行失败");
                }
                executeLogMapper.updateByPrimaryKeyWithBLOBs(executeLog);
            }
        }
    }
}
