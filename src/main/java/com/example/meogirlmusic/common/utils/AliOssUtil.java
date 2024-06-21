package com.example.meogirlmusic.common.utils;


import com.aliyun.oss.ClientException;
import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.OSSException;
import com.aliyun.oss.model.PutObjectRequest;
import com.aliyun.oss.model.PutObjectResult;


import java.io.InputStream;

public class AliOssUtil {

    private static final String accessKeyId = "LTAI5tJQpybZ6MQhGJ6E66Ls";
    private static final String accessKeySecret = "EHykbAkKM5D2caqYptXl8mCV6EuGD9";
    private static final String bucketName = "supengcs";
    private static final String ENDPOINT = "https://oss-cn-chengdu.aliyuncs.com";


    public static String uploadFile(String objectName, InputStream in) {
        String url = "https://supengcs.oss-cn-chengdu.aliyuncs.com/";

        // 创建OSSClient实例
        OSS ossClient = new OSSClientBuilder().build(ENDPOINT, accessKeyId, accessKeySecret);
        try {
            // 创建PutObjectRequest对象
            PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName, objectName, in);
            // 上传文件
            PutObjectResult putObjectResult = ossClient.putObject(putObjectRequest);
        } catch (OSSException oe) {
            System.out.println("Caught an OSSException, which means your request made it to OSS, "
                    + "but was rejected with an error response for some reason.");
            System.out.println("Error Message:" + oe.getErrorMessage());
            System.out.println("Error Code:" + oe.getErrorCode());
            System.out.println("Request ID:" + oe.getRequestId());
            System.out.println("Host ID:" + oe.getHostId());
        } catch (ClientException ce) {
            System.out.println("Caught an ClientException, which means the client encountered "
                    + "a serious internal problem while trying to communicate with OSS, "
                    + "such as not being able to access the network.");
            System.out.println("Error Message:" + ce.getMessage());
        } finally {
            if (ossClient != null) {
                ossClient.shutdown();
            }
        }

        return url + objectName;
    }
}
