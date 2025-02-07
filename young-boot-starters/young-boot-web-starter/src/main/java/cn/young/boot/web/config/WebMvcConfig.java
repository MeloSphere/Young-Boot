package cn.young.boot.web.config;

import cn.young.boot.web.route.DefaultApiMonitor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.Resource;

/**
 * @author Mole. meiko_ooo@163.com
 * @since 2025/2/7 10:21
 */
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Resource
    private DefaultApiMonitor defaultApiMonitor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(defaultApiMonitor)
                .addPathPatterns("/**")
                .excludePathPatterns("/**/favicon.ico",
                        "/**/v3/api-docs/swagger-config",
                        "/**/v3/api-docs");
    }
}
