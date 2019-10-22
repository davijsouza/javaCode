package com.souche.salt_common_01.service;

import java.util.Map;

public interface ScriptServerService {
    Integer addServer(String ScriptID,String account,String servers);

    Map<String, Object> getServer(String scriptID);

    void delServer(String scriptID);
}
