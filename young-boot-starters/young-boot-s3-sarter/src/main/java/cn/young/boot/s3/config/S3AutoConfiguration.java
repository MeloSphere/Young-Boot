package cn.young.boot.s3.config;

import cn.young.boot.s3.properties.S3ConfigProperties;
import io.minio.MinioClient;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Mole. meiko_ooo@163.com
 * @since 2025/2/1 19:21
 */
@Configuration
@EnableConfigurationProperties(S3ConfigProperties.class)
@ConditionalOnProperty(name = "young.s3.config.enable", havingValue = "true")
public class S3AutoConfiguration {

    @Bean
    @ConditionalOnMissingBean
    public MinioClient minioClient(S3ConfigProperties properties) {
        return MinioClient.builder()
                //设置 Minio 服务的端点地址
                .endpoint(properties.getEndpoint())
                // 设置访问 Minio 服务所需的访问密钥和秘密密钥
                .credentials(properties.getAccessKey(), properties.getSecretKey())
                // 构建并返回 MinioClient 实例
                .build();
    }
}
