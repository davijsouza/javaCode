package com.souche.salt_common_01.config;



import com.souche.salt_common_01.entity.Current;
import com.souche.salt_common_01.entity.CurrentExample;
import com.souche.salt_common_01.mapper.CurrentMapper;
import com.souche.salt_common_01.service.impl.CurrnetFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;

/**
 * @author lnj
 * createTime 2018-11-07 22:37
 **/
@Component
public class ApplicationRunnerImpl implements ApplicationRunner {
 /*   @Autowired
    private CurrnetFile currnetFile;
    @Autowired
    private CurrentMapper currentMapper;*/
    @Override
    public void run(ApplicationArguments args)  {
      /*  CurrentExample currentExample=new CurrentExample();
        currentExample.createCriteria().andIdNotEqualTo("1");
        currentMapper.deleteByExample(currentExample);

        final File tmpLogFile = new File("/home/souche/salt_events/saltlog.txt");
        try {
            currnetFile.realtimeShowLog(tmpLogFile);
        } catch (IOException e) {

            e.printStackTrace();

           *//* try {
                final File tmpLogFile2 = new File("/tmp/saltlog.txt");
                currnetFile.realtimeShowLog(tmpLogFile2);
            } catch (IOException e1) {

                e.printStackTrace();
            }*//*
        }*/
    }
}
