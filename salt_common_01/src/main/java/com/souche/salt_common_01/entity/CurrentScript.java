package com.souche.salt_common_01.entity;

import lombok.Data;

import java.util.Date;

@Data
public class CurrentScript {
    private String id;
    private Integer num;
    private String  scriptName;
    private Date    startTime;


}
