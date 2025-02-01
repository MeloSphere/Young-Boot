package cn.young.boot.satoken.config;

import cn.dev33.satoken.dao.SaTokenDao;
import cn.dev33.satoken.jwt.StpLogicJwtForSimple;
import cn.dev33.satoken.stp.StpInterface;
import cn.dev33.satoken.stp.StpLogic;
import cn.young.boot.satoken.impl.YoungSaPermissionImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Mole. meiko_ooo@163.com
 * @since 2025/1/30 15:44
 */
@Configuration
public class SaTokenConfig {

    /**
     * 权限接口实现(使用bean注入方便用户替换)
     */

    @Bean
    public StpInterface stpInterface() {
        return new YoungSaPermissionImpl();
    }

    @Bean
    public StpLogic getStpLogicJwt() {
        return new StpLogicJwtForSimple();
    }
}
