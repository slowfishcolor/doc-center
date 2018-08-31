package com.sfc.doc.center.service;

import com.aliyun.oss.OSSClient;
import com.aliyun.oss.common.auth.DefaultCredentialProvider;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.ByteArrayInputStream;
import java.io.File;

@Service
public class OssService {

    @Value("${alioss.endpoint}")
    private String endpoint;

    @Value("${alioss.accessKeyId}")
    private String accessKeyId;

    @Value("${alioss.accessKeySecret}")
    private String accessKeySecret;

    @Value("${alioss.bucketName}")
    private String bucketName;

    @Value("${alioss.firstkey}")
    private String firstKey;

    private OSSClient ossClient = null;

    @PostConstruct
    void init()
    {
        DefaultCredentialProvider provider = new DefaultCredentialProvider(accessKeyId, accessKeySecret);
        ossClient = new OSSClient(endpoint, provider, null);
    }

    public void uploadFile(String filePath, File file) {
        filePath = filePath.replace("\\", "/");
        String fileDir = filePath.substring(0, filePath.lastIndexOf("/") + 1);
        if(!fileIsExist(fileDir)) {
            ossClient.putObject(bucketName, firstKey + fileDir, new ByteArrayInputStream(new byte[]{}));
        }
        ossClient.putObject(bucketName, firstKey + filePath, file);
    }

    public boolean fileIsExist(String filePath){
        return ossClient.doesObjectExist(bucketName, firstKey + filePath);
    }


}
