package com.souche.salt_common_01.service.impl;

import com.souche.salt_common_01.entity.ScriptVar;
import com.souche.salt_common_01.entity.ScriptVarExample;
import com.souche.salt_common_01.mapper.ScriptVarMapper;
import com.souche.salt_common_01.service.ScriptVarSevice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ScriptVarServiceImpl implements ScriptVarSevice {
 @Autowired
 private ScriptVarMapper scriptVarMapper;

    @Override
    public Integer addVar(String id, String[] vars) {
        ScriptVar scriptVar=new ScriptVar();
        scriptVar.setScriptId(id);
       try{
           for (int j=0;j<vars.length;j++){
               scriptVar.setVar(vars[j]);
               scriptVar.setSort(j+1);
               scriptVarMapper.insert(scriptVar);
           }
           return 1;
       }catch (Exception e){
           e.printStackTrace();
           return 0;
       }

    }

    @Override
    public List<String> getScriptVars(String scriptID) {
        ScriptVarExample scriptVarExample=new ScriptVarExample();
        scriptVarExample.setOrderByClause("sort");
        scriptVarExample.or().andScriptIdEqualTo(scriptID);
        List<ScriptVar> scriptVars=scriptVarMapper.selectByExample(scriptVarExample);
        if(scriptVars==null){
            return null;
        }
        List<String> vars=new ArrayList<>();
        for (ScriptVar s :
                scriptVars) {
            vars.add(s.getVar());
        }
        return vars;
    }

    @Override
    public void delScriptVars(String scriptID) {
        ScriptVarExample scriptVarExample=new ScriptVarExample();
        scriptVarExample.or().andScriptIdEqualTo(scriptID);
        scriptVarMapper.deleteByExample(scriptVarExample);
    }
}
