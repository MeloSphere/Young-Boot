package cn.young.boot.s3.channel;

import cn.hutool.core.io.FileUtil;
import cn.young.boot.s3.properties.S3ConfigProperties;
import io.minio.MinioClient;
import io.minio.PutObjectArgs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.InputStream;

/**
 * @author Mole. meiko_ooo@163.com
 * @since 2025/2/1 19:27
 */
@Component
public class MinioServiceInstance implements S3ServiceInstance {

    @Autowired(required = false)
    private MinioClient minioClient;

    @Resource
    private S3ConfigProperties s3ConfigProperties;

    @Override
    public String putFile(String bucketName, File file, String obj) {
        try {
            BufferedInputStream inputStream = FileUtil.getInputStream(file);
            PutObjectArgs args = PutObjectArgs
                    .builder()
                    //设置桶名
                    .bucket(bucketName)
                    //设置对象名
                    .object(obj)
                    .stream(inputStream, inputStream.available(), -1)
                    .build();
            minioClient.putObject(args);
            return s3ConfigProperties.getEndpoint() + "/" + bucketName + "/" + obj;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("文件上传失败");
        }
    }

    @Override
    public String putInputStream(String bucketName, InputStream inputStream, String path) {
        try {
            PutObjectArgs args = PutObjectArgs
                    .builder()
                    //设置桶名
                    .bucket(bucketName)
                    //设置对象名
                    .object(path)
                    .stream(inputStream, inputStream.available(), -1)
                    .build();
            minioClient.putObject(args);
            return s3ConfigProperties.getEndpoint() + "/" + bucketName + "/" + path;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("文件上传失败");
        }


    }
}
