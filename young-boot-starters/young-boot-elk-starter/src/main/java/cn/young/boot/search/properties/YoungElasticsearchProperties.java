package cn.young.boot.search.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author Mole. meiko_ooo@163.com
 * @since 2025/1/2 15:28
 */
@Data
@ConfigurationProperties(prefix = "young.elasticsearch")
public class YoungElasticsearchProperties {
    /**
     * Elasticsearch host
     */
    private String host = "localhost";


    /**
     * 密码
     */
    private String password;

    /**
     * Elasticsearch port
     */
    private int port = 9200;

    /**
     * Connection timeout in milliseconds
     */
    private int connectionTimeout = 5000;

    /**
     * Socket timeout in milliseconds
     */
    private int socketTimeout = 30000;
}
