package cn.young.boot.s3.channel;

import java.io.File;
import java.io.InputStream;

/**
 * @author Mole. meiko_ooo@163.com
 * @since 2025/2/1 19:26
 */
public interface S3ServiceInstance {
    /**
     * 向S3桶容器中存放文件
     *
     * @param bucketName 桶名称
     * @param file       文件
     * @param path       生成后的文件路径
     * @return URL
     */
    String putFile(String bucketName, File file, String path);

    /**
     * 向S3桶容器中存放文件
     *
     * @param bucketName  桶名称
     * @param inputStream 文件流
     * @param path        生成后的文件路径
     * @return URL
     */
    String putInputStream(String bucketName, InputStream inputStream, String path);
}
