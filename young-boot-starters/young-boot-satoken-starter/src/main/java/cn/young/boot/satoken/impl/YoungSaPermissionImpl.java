package cn.young.boot.satoken.impl;

import cn.dev33.satoken.stp.StpInterface;
import cn.young.boot.satoken.constant.UserTypeConstant;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.jni.User;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * @author Mole. meiko_ooo@163.com
 * @since 2025/1/30 15:43
 */
@Slf4j
public class YoungSaPermissionImpl implements StpInterface {
    @Override
    public List<String> getPermissionList(Object loginId, String loginType) {
        return new ArrayList<>();

    }

    @Override
    public List<String> getRoleList(Object loginId, String loginType) {
        List<String> dataList = new ArrayList<>();
        String loginStr = String.valueOf(loginId);
        String[] split = loginStr.split(":");
        if (split.length == 3) {
            String userType = split[0];
            if (userType.equals(UserTypeConstant.ADMIN)) {
                dataList.add(UserTypeConstant.ADMIN);
            }
        }
        dataList.add(UserTypeConstant.COMMON);
        return dataList;
    }
}
