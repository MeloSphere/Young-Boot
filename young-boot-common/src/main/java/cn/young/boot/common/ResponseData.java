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

    private Integer code;

    private String message;

    private T data;

    public static ResponseData<Void> success() {
        ResponseData<Void> response = new ResponseData<>();
        response.setCode(200);
        response.setMessage("success");
        return response;
    }


    public static <T> ResponseData<T> success(T data) {
        ResponseData<T> response = new ResponseData<>();
        response.setCode(200);
        response.setMessage("success");
        response.setData(data);
        return response;
    }


}
