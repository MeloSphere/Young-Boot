package cn.young.boot.common;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * @author Mole. meiko_ooo@163.com
 * @since 2025/1/21 20:35
 */
@Setter
@Getter
public class ResponseData<T> implements Serializable {
    /**
     * 成功状态标识
     */
    private Boolean success;
    /**
     * 状态码
     */
    private Integer code;
    /**
     * 消息提示信息
     */
    private String message;
    /**
     * 数据
     */
    private T data;

    public static ResponseData<Void> success() {
        ResponseData<Void> response = new ResponseData<>();
        response.setSuccess(true);
        response.setCode(200);
        response.setMessage("success");
        return response;
    }


    public static <T> ResponseData<T> success(T data) {
        ResponseData<T> response = new ResponseData<>();
        response.setSuccess(true);
        response.setCode(200);
        response.setMessage("success");
        response.setData(data);
        return response;
    }

    public static ResponseData<Void> fail() {
        ResponseData<Void> response = new ResponseData<>();
        response.setSuccess(false);
        response.setCode(500);
        response.setMessage("error");
        return response;
    }

    public static <T> ResponseData<T> fail(T data) {
        ResponseData<T> response = new ResponseData<>();
        response.setSuccess(false);
        response.setCode(500);
        response.setMessage("error");
        response.setData(data);
        return response;
    }

    public Boolean isSuccess() {
        return this.success;
    }


}
