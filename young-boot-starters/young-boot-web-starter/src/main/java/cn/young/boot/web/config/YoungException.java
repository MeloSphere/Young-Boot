package cn.young.boot.web.config;

import cn.young.boot.common.ResponseData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author Mole. meiko_ooo@163.com
 * @since 2025/1/26 14:24
 */
@RestControllerAdvice
@Slf4j
public class YoungException {

    /**
     * @param e 运行时异常
     * @return 异常错误
     */
    @ExceptionHandler(value = RuntimeException.class)
    public ResponseData<String> bizExceptionHandler(RuntimeException e) {
        log.error("bizExceptionHandler:{}", e.getMessage(), e);
        return ResponseData.fail(e.getMessage());
    }
}
