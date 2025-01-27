package cn.young.boot.search.config;

import cn.young.boot.search.properties.YoungElasticsearchProperties;
import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Mole. meiko_ooo@163.com
 * @since 2025/1/27 13:10
 */
@Configuration
public class YoungElasticsearchAutoConfiguration {

    @Bean(destroyMethod = "close")
    @ConditionalOnMissingBean
    public RestHighLevelClient restHighLevelClient(YoungElasticsearchProperties properties) {
        return new RestHighLevelClient(
                RestClient.builder(new HttpHost(properties.getHost(), properties.getPort()))
                        .setRequestConfigCallback(requestConfigBuilder ->
                                requestConfigBuilder
                                        .setConnectTimeout(properties.getConnectionTimeout())
                                        .setSocketTimeout(properties.getSocketTimeout())
                        )
        );
    }
}
