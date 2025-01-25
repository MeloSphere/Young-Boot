package cn.young.boot.security.model;

import sun.rmi.runtime.Log;

import java.io.Serializable;

/**
 * @author Mole. meiko_ooo@163.com
 * @since 2025/1/25 12:22
 */
public class RequestContent implements Serializable {

    private Long userId;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public RequestContent() {
    }

    public RequestContent(Long userId) {
        this.userId = userId;
    }
}
