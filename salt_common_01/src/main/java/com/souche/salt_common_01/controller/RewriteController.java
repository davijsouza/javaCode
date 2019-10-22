package com.souche.salt_common_01.controller;

import com.souche.salt_common_01.entity.Rewrite;
import com.souche.salt_common_01.service.RewriteService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api(tags = {"文件重写"})
public class RewriteController {

    @Autowired
    private RewriteService rewriteService;
    @PostMapping("/lantu/rewrite")
   public String doWhrite(Rewrite rewrite){
        return rewriteService.doWriter(rewrite.getApplicationName(),rewrite.getPath(),rewrite.getContent(),rewrite.getSzoneName());
    }


    @PostMapping("/lantu/stableRewrite")
    public String stableWrite(Rewrite rewrite){
        System.err.println(rewrite);
        return rewriteService.doStableWriter(rewrite);
    }






}
