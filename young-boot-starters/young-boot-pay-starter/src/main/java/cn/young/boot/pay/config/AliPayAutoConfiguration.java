package cn.young.boot.pay.config;

import cn.young.boot.pay.properties.AlipayConfigProperties;
import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.AlipayConfig;
import com.alipay.api.DefaultAlipayClient;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Mole. meiko_ooo@163.com
 * @since 2025/2/2 15:29
 */
@Configuration
@ConditionalOnProperty(name = "young.pay.ali.enable", havingValue = "true")
@EnableConfigurationProperties(AlipayConfigProperties.class)
public class AliPayAutoConfiguration {

    @Bean
    @ConditionalOnMissingBean
    public AlipayClient alipayClient(AlipayConfigProperties properties) throws AlipayApiException {
        AlipayConfig alipayConfig = new AlipayConfig();
        alipayConfig.setServerUrl(properties.getServerUrl());
        alipayConfig.setAppId(properties.getAppId());
        alipayConfig.setPrivateKey(properties.getPrivateKey());
        alipayConfig.setFormat(properties.getFormat());
        alipayConfig.setAlipayPublicKey(properties.getAlipayPublicKey());
        alipayConfig.setCharset(properties.getCharset());
        alipayConfig.setSignType(properties.getSignType());
        return new DefaultAlipayClient(alipayConfig);
    }
}
