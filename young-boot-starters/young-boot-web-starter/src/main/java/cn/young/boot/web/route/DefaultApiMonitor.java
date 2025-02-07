package cn.young.boot.web.route;

import cn.young.boot.web.model.MonitorData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.util.ContentCachingResponseWrapper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

/**
 * @author Mole. meiko_ooo@163.com
 * @since 2025/2/7 10:41
 */
@Slf4j
@Component
public class DefaultApiMonitor extends AbstractApiMonitor {

    private static final String RESPONSE_WRAPPER = "responseWrapper";
    private final ThreadLocal<String> requestIdHolder = new ThreadLocal<>();

    @Override
    protected void beforeHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        // 包装response以获取响应体
        ContentCachingResponseWrapper responseWrapper = new ContentCachingResponseWrapper(response);
        request.setAttribute(RESPONSE_WRAPPER, responseWrapper);
    }

    @Override
    protected void prepareMonitorContext(HttpServletRequest request, HttpServletResponse response, Object handler) {
        // 生成请求ID并保存
        String requestId = UUID.randomUUID().toString();
        requestIdHolder.set(requestId);
    }

    @Override
    protected MonitorData collectMonitorData(HttpServletRequest request, HttpServletResponse response,
                                             Object handler, long duration, Exception ex) {
        // 获取响应体
        return MonitorData.builder()
                .requestId(requestIdHolder.get())
                .uri(request.getRequestURI())
                .method(request.getMethod())
                .duration(duration)
                .status(response.getStatus())
                .exception(ex != null ? ex.getMessage() : null)
                .build();
    }

    @Override
    protected void processMonitorData(MonitorData data) {
        // 记录监控日志
        if (null == data.getException()) {
            log.info("API访问 - URI:{} 方法:{} 耗时:{}ms 状态码:{} ",
                    data.getUri(),
                    data.getMethod(),
                    data.getDuration(),
                    data.getStatus());
        } else {
            log.error("API访问 - URI:{} 方法:{} 耗时:{}ms 状态码:{} 异常:{} ",
                    data.getUri(),
                    data.getMethod(),
                    data.getDuration(),
                    data.getStatus(),
                    data.getException());
        }
    }

    @Override
    protected void clearMonitorContext() {
        // 清理线程变量
        requestIdHolder.remove();
    }
}
