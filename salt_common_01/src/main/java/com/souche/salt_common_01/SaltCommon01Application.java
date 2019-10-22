package com.souche.salt_common_01;


import com.ctrip.framework.apollo.spring.annotation.EnableApolloConfig;
import com.souche.salt_common_01.service.impl.CurrnetFile;
import com.souche.salt_common_01.swagger.Jsontest;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableScheduling;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.io.File;
import java.io.IOException;
@EnableCaching
@EnableScheduling
@EnableSwagger2
@EnableApolloConfig
@SpringBootApplication
@MapperScan("com.souche.salt_common_01.mapper")
public class SaltCommon01Application extends SpringBootServletInitializer {

    public static void main(String[] args) {

        SpringApplication.run(SaltCommon01Application.class, args);


     /*   System.err.println("监控文件开始. . .");
        CurrnetFile view = new CurrnetFile();
        final File tmpLogFile = new File("/Users/dasouche/tem/num.txt");
        try {
            view.realtimeShowLog(tmpLogFile);
        } catch (IOException e) {
            e.printStackTrace();
        }*/

    }
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        // 设置启动类,用于独立tomcat运行的入口
        return builder.sources(SaltCommon01Application.class); }
    }






