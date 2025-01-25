package cn.young.boot.security.holder;

import cn.young.boot.security.model.RequestContent;


/**
 * @author Mole. meiko_ooo@163.com
 * @since 2025/1/25 12:20
 */
public class RequestContentHolder {
    /**
     * 获取当前Request请求域数据
     *
     * @return 请求域数据
     */
    public static RequestContent getContext() {
        //TODO临时处理
        return new RequestContent(1L);
    }
}
