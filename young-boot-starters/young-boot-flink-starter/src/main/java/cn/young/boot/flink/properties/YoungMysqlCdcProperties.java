package cn.young.boot.flink.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author Mole. meiko_ooo@163.com
 * @since 2025/1/27 12:04
 */
@Data
@Configuration
@ConfigurationProperties(prefix = "young.flink.config", ignoreInvalidFields = true)
public class YoungMysqlCdcProperties {
    /**
     * 默认关闭
     */
    private Boolean enable = false;

    /**
     * host地址
     */
    private String hostname;

    /**
     * 端口
     */
    private Integer port;

    /**
     * DB 用户名
     */
    private String username;

    /**
     * DB 密码
     */
    private String password;

    /**
     * 数据库列表
     */
    private String databaseList;

    /**
     * 表列表
     */
    private String tableList;
}
