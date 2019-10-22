package com.souche.salt_common_01.service;

import java.util.List;

public interface ScriptVarSevice {
    Integer addVar(String id,String[] vars);

    List<String> getScriptVars(String scriptID);

    void delScriptVars(String scriptID);
}
