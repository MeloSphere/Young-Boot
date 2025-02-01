package cn.young.boot.s3.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author Mole. meiko_ooo@163.com
 * @since 2025/2/1 19:17
 */
@Data
@ConfigurationProperties(prefix = "young.s3.config", ignoreInvalidFields = true)
public class S3ConfigProperties {
    /**
     * 默认关闭
     */
    private Boolean enable = false;

    /**
     * 默认采用minio (后续扩展使用)
     */
    private String type = "minio";

    /**
     * 桶名称
     */
    private String buckName;

    /**
     * endpoint
     */
    private String endpoint;

    /**
     * accessKey
     */
    private String accessKey;

    /**
     * secretKey
     */
    private String secretKey;

}
