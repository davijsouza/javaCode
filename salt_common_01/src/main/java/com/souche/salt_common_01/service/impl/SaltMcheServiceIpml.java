package com.souche.salt_common_01.service.impl;

import com.souche.salt_common_01.entity.SaltMche;
import com.souche.salt_common_01.entity.SaltMcheExample;
import com.souche.salt_common_01.mapper.SaltMcheMapper;
import com.souche.salt_common_01.service.ClientService;
import com.souche.salt_common_01.service.NginxService;
import com.souche.salt_common_01.service.SaltMcheService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
@Slf4j
@Service
public class SaltMcheServiceIpml implements SaltMcheService {
    @Autowired
    private ClientService clientService;
    @Autowired
    private SaltMcheMapper saltMcheMapper;
    @Autowired
    private NginxService nginxService;
    @Override
    public void initSaltMche() {
        String ips=clientService.doClient("netstat -an  | grep 4505| awk '{print $5}' | cut -d ':' -f 1 | grep -v -i STREAM","i-bp12m8kludn9vq2fbkqb","souche",null);

        String stdout= nginxService.getSingleClientResult("i-bp12m8kludn9vq2fbkqb",ips);
        String[]  saltNamesArr=stdout.split("\\r?\\n");
       // System.err.println(saltNamesArr.length);
        //插入
        for (int i=0 ;i<saltNamesArr.length;i++){
            log.info(saltNamesArr[i]);
            SaltMcheExample saltMcheExample=new SaltMcheExample();
            saltMcheExample.or().andIpEqualTo(saltNamesArr[i]);
            List<SaltMche> salts= saltMcheMapper.selectByExample(saltMcheExample);
            if (salts==null||salts.size()<=0){
                SaltMche saltMche=new SaltMche();
                saltMche.setIp(saltNamesArr[i]);
                saltMcheMapper.insert(saltMche);
            }
        }
        //  删除
        List<String> listSaltName= Arrays.asList(saltNamesArr);
        SaltMcheExample saltMcheExample2=new SaltMcheExample();
        saltMcheExample2.or() ;
        List<SaltMche> DBsalts= saltMcheMapper.selectByExample(saltMcheExample2);
        log.info(DBsalts.size()+":DBsalts的数量");
        for (SaltMche saltMche:DBsalts
             ) {
            //log.info(saltMche+":DB的IP");
            if (!listSaltName.contains(saltMche.getIp())){
                log.info(saltMche+":删除DB的IP");
                saltMcheMapper.deleteByPrimaryKey(saltMche.getId());
            }

        }


    }
}
