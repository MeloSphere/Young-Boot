package cn.young.boot.common;

import lombok.Setter;

/**
 * @author Mole. meiko_ooo@163.com
 * @since 2025/1/21 20:35
 */
@Setter
public class ResponseData<T> {

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
