package cn.yong.boot.redis.config;

import cn.yong.boot.redis.properties.RedisClientConfigProperties;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.jsontype.impl.LaissezFaireSubTypeValidator;
import io.micrometer.core.instrument.util.StringUtils;
import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.codec.TypedJsonJacksonCodec;
import org.redisson.config.Config;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;

/**
 * @author Mole. meiko_ooo@163.com
 * @since 2025/1/30 13:13
 */
@Configuration
@EnableConfigurationProperties(RedisClientConfigProperties.class)
public class RedisClientAutoConfiguration {

    @Resource
    private ObjectMapper objectMapper;

    @Bean("redissonClient")
    /*@ConditionalOnMissingBean(value = RedissonClient.class)*/
    public RedissonClient redissonClient(ConfigurableApplicationContext applicationContext, RedisClientConfigProperties properties) {
        ObjectMapper om = objectMapper.copy();
        om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        // 指定序列化输入的类型，类必须是非final修饰的。序列化时将对象全类名一起保存下来
        om.activateDefaultTyping(LaissezFaireSubTypeValidator.instance, ObjectMapper.DefaultTyping.NON_FINAL);
        Config config = new Config();
        // 根据需要可以设定编解码器；https://github.com/redisson/redisson/wiki/4.-%E6%95%B0%E6%8D%AE%E5%BA%8F%E5%88%97%E5%8C%96
        config.setCodec(new TypedJsonJacksonCodec(Object.class, om));
        // 单机版本redis设置
        config.useSingleServer()
                .setAddress("redis://" + properties.getHost() + ":" + properties.getPort())
                .setConnectionPoolSize(properties.getPoolSize())
                .setDatabase(properties.getDatabase())
                .setConnectionMinimumIdleSize(properties.getMinIdleSize())
                .setIdleConnectionTimeout(properties.getIdleTimeout())
                .setConnectTimeout(properties.getConnectTimeout())
                .setRetryAttempts(properties.getRetryAttempts())
                .setRetryInterval(properties.getRetryInterval())
                .setPingConnectionInterval(properties.getPingInterval())
                .setKeepAlive(properties.isKeepAlive());
        if (StringUtils.isNotBlank(properties.getPassword()) && StringUtils.isNotBlank(properties.getUsername())) {
            config.useSingleServer().setUsername(properties.getUsername());
            config.useSingleServer().setPassword(properties.getPassword());
        }
        return Redisson.create(config);
    }

}
