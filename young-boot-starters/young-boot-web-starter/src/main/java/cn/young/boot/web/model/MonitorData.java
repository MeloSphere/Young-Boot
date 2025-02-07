package cn.young.boot.web.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Mole. meiko_ooo@163.com
 * @since 2025/2/7 10:38
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MonitorData {
    /**
     * 请求ID
     */
    private String requestId;
    /**
     * 请求URI地址信息
     */
    private String uri;
    /**
     * 请求方法
     */
    private String method;
    /**
     * 请求耗时时间
     */
    private long duration;
    /**
     * 请求状态
     */
    private int status;
    /**
     * 异常数据信息
     */
    private String exception;
    /**
     * 响应体
     */
    private Object responseBody;

    /**
     * 其他信息
     */
    private Map<String, String> extraInfo;

    public void addExtraInfo(String key, String value) {
        if (extraInfo == null) {
            extraInfo = new HashMap<>();
        }
        extraInfo.put(key, value);
    }
}
