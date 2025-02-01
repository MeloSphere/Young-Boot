package cn.young.boot.satoken.config;

import cn.dev33.satoken.interceptor.SaInterceptor;
import cn.dev33.satoken.stp.StpUtil;
import cn.young.boot.satoken.factory.YmlPropertySourceFactory;
import cn.young.boot.satoken.interceptor.TokenInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.Resource;

/**
 * @author Mole. meiko_ooo@163.com
 * @since 2025/1/30 14:26
 */
@Configuration
@PropertySource(value = "classpath:common-satoken.yml", factory = YmlPropertySourceFactory.class)
public class SaTokenBootConfigure implements WebMvcConfigurer {

    @Resource
    private TokenInterceptor tokenInterceptor;

    // 注册拦截器
    @Override
    public void addInterceptors(InterceptorRegistry registry) {

        /**
         * 微服务的下级子服务不能加上StpUtil.checkLogin()，跟单体服务的不一致（借鉴RuoYi-Cloud-Plus）
         * ruoyi-common-security SecurityConfiguration配置
         */
        // 注册路由拦截器，自定义验证规则
        registry.addInterceptor(new SaInterceptor()).addPathPatterns("/**");
        registry.addInterceptor(tokenInterceptor).addPathPatterns("/**");
        // 有坑原始写法==>注册 Sa-Token 拦截器，校验规则为 StpUtil.checkLogin() 登录校验。
    }

    // 注册 Sa-Token 全局过滤器


}
