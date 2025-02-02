package cn.young.boot.pay.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author Mole. meiko_ooo@163.com
 * @since 2025/1/7 00:05
 */
@Data
@ConfigurationProperties(prefix = "young.pay.ali", ignoreInvalidFields = true)
public class AlipayConfigProperties {
    /**
     * 默认关闭
     */
    private Boolean enable = false;

    private String privateKey;

    private String alipayPublicKey;

    private String serverUrl;

    private String appId;

    private String format;

    private String charset;

    private String signType;
}
