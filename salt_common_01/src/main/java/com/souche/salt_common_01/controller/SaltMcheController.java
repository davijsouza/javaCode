package com.souche.salt_common_01.controller;

import com.souche.salt_common_01.service.SaltMcheService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(description = "需要执行的执行",tags = "runJob接口")
@RestController
public class SaltMcheController {
    @Autowired
    private SaltMcheService saltMcheService;
@RequestMapping("/lantu/salt/initSaltMche")
    public void initSaltMche(){
        saltMcheService.initSaltMche();
    }

}
