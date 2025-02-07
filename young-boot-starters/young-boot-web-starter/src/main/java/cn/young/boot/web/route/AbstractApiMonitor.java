package cn.young.boot.web.route;


import cn.young.boot.web.model.MonitorData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Mole. meiko_ooo@163.com
 * @since 2025/2/7 10:37
 */
@Slf4j
public abstract class AbstractApiMonitor implements HandlerInterceptor {

    private static final String START_TIME = "requestStartTime";

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        try {
            // 1. 前置处理
            beforeHandle(request, response, handler);

            // 2. 记录开始时间
            request.setAttribute(START_TIME, System.currentTimeMillis());

            // 3. 准备监控上下文
            prepareMonitorContext(request, response, handler);

            return true;
        } catch (Exception e) {
            log.error("API监控前置处理异常", e);
            return true; // 即使监控失败也不影响正常请求
        }
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response,
                                Object handler, Exception ex) {
        try {
            // 1. 计算耗时
            long duration = calculateDuration(request);

            // 2. 收集监控数据
            MonitorData monitorData = collectMonitorData(request, response, handler, duration, ex);

            // 3. 处理监控数据
            processMonitorData(monitorData);

            // 4. 后置处理
            afterHandle(request, response, handler, ex);

        } catch (Exception e) {
            log.error("API监控后置处理异常", e);
        } finally {
            // 5. 清理监控上下文
            clearMonitorContext();
        }
    }

    // 可选择性重写的钩子方法
    protected void beforeHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
    }

    protected void prepareMonitorContext(HttpServletRequest request, HttpServletResponse response, Object handler) {
    }

    protected void afterHandle(HttpServletRequest request, HttpServletResponse response,
                               Object handler, Exception ex) {
    }

    protected void clearMonitorContext() {
    }

    //抽象收集监控数据信息
    protected abstract MonitorData collectMonitorData(HttpServletRequest request, HttpServletResponse response,
                                                      Object handler, long duration, Exception ex);

    // 处理监控数据信息
    protected abstract void processMonitorData(MonitorData monitorData);

    // 计算耗时
    private long calculateDuration(HttpServletRequest request) {
        Long startTime = (Long) request.getAttribute(START_TIME);
        return startTime != null ? System.currentTimeMillis() - startTime : 0;
    }
}
