package cn.young.boot.satoken.interceptor;

import cn.dev33.satoken.context.SaHolder;
import cn.dev33.satoken.context.model.SaStorage;
import cn.dev33.satoken.stp.StpUtil;
import cn.young.boot.satoken.helper.LoginHelper;
import cn.young.boot.satoken.model.LoginUser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static cn.young.boot.satoken.helper.LoginHelper.LOGIN_USER_KEY;
import static cn.young.boot.satoken.helper.LoginHelper.USER_KEY;

/**
 * @author Mole. meiko_ooo@163.com
 * @since 2025/1/30 14:57
 */
@Component
@Slf4j
public class TokenInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String tokenValue = request.getHeader(StpUtil.getTokenName());
        if (StringUtils.hasText(tokenValue)) {
            LoginUser loginUser = LoginHelper.getLoginUser(tokenValue);
            if (null != loginUser) {
                SaStorage storage = SaHolder.getStorage();
                storage.set(LOGIN_USER_KEY, loginUser);
                storage.set(USER_KEY, loginUser.getUserId());
                StpUtil.setTokenValue(tokenValue);
            }
        }
        return HandlerInterceptor.super.preHandle(request, response, handler);
    }


    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
    }
}
