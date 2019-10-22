package com.souche.salt_common_01.service;

import com.souche.salt_common_01.utils.TaotaoResult;

import java.util.List;

public interface AgentService {
     void updateLog(String stdout, String logId) ;

    TaotaoResult doshell(String content, String vars, String account, List<String> ips);

    void updateRetcode(String pid, String logId, String retcode);
}
