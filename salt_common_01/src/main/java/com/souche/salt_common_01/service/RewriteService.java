package com.souche.salt_common_01.service;

import com.souche.salt_common_01.entity.Rewrite;

public interface RewriteService {
    String doWriter(String applicationName, String path, String content, String szoneName);

    String doStableWriter(Rewrite rewrite);
}
