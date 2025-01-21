package cn.young.boot.common.model;

import lombok.Data;

import java.io.Serializable;

/**
 * 传输对象基类
 *
 * @author Mole. meiko_ooo@163.com
 * @since 2025/1/21 19:39
 */
@Data
public class DTO implements Serializable {

    private Long timeStamp = System.currentTimeMillis();
}
