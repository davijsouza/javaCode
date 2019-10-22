package com.souche.salt_common_01.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface JstackService {
    String getInfo(String instanceId);

    void downloadJstack(HttpServletRequest request, HttpServletResponse response,String instanceId, String pid);

    void downloadFile(HttpServletRequest request, HttpServletResponse response, String instanceId, String path);
}
