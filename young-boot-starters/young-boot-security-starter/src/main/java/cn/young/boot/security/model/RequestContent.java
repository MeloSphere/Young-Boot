package cn.young.boot.security.model;

import sun.rmi.runtime.Log;

import java.io.Serializable;

/**
 * @author Mole. meiko_ooo@163.com
 * @since 2025/1/25 12:22
 */
public class RequestContent implements Serializable {

    private Long userId;


    private String username;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public RequestContent() {
    }

    public RequestContent(Long userId, String username) {
        this.userId = userId;
        this.username = username;
    }


}
