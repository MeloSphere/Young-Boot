package cn.young.boot.satoken.model;

import lombok.Data;

import java.io.Serializable;

/**
 * @author Mole. meiko_ooo@163.com
 * @since 2025/1/30 15:00
 */
@Data
public class LoginUser implements Serializable {

    private Long userId;

    private String nickName;
}
