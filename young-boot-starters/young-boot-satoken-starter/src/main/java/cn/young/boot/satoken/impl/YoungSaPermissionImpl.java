package cn.young.boot.satoken.impl;

import cn.dev33.satoken.stp.StpInterface;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Mole. meiko_ooo@163.com
 * @since 2025/1/30 15:43
 */
@Slf4j
public class YoungSaPermissionImpl implements StpInterface {
    @Override
    public List<String> getPermissionList(Object loginId, String loginType) {
        return new ArrayList<String>() {
            {
                add("*:*:*");
            }
        };

    }

    @Override
    public List<String> getRoleList(Object loginId, String loginType) {
        return new ArrayList<String>() {
            {
                add("admin");
            }
        };
    }
}
