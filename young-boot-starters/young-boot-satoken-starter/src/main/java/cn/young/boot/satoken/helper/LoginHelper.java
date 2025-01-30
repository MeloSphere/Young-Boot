package cn.young.boot.satoken.helper;

import cn.dev33.satoken.context.SaHolder;
import cn.dev33.satoken.context.model.SaStorage;
import cn.dev33.satoken.session.SaSession;
import cn.dev33.satoken.stp.SaLoginModel;
import cn.dev33.satoken.stp.StpUtil;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.Map;
import java.util.UUID;

/**
 * 登录鉴权助手
 * <p>
 * user_type 为 用户类型 同一个用户表 可以有多种用户类型 例如 pc,app
 * deivce 为 设备类型 同一个用户类型 可以有 多种设备类型 例如 web,ios
 * 可以组成 用户类型与设备类型多对多的 权限灵活控制
 * <p>
 * 多用户体系 针对 多种用户类型 但权限控制不一致
 * 可以组成 多用户类型表与多设备类型 分别控制权限
 *
 * @author Lion Li
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class LoginHelper {

    public static final String LOGIN_USER_KEY = "loginUser";
    public static final String USER_KEY = "userId";

    /**
     * 登录系统
     *
     * @param loginUser 登录用户信息
     */
    public static void login(Map<String, Object> loginUser) {
        loginByDevice(loginUser);
    }

    /**
     * 登录系统 基于 设备类型
     * 针对相同用户体系不同设备
     *
     * @param loginUser 登录用户信息
     */
    public static void loginByDevice(Map<String, Object> loginUser) {
        SaStorage storage = SaHolder.getStorage();
        storage.set(LOGIN_USER_KEY, loginUser);
        storage.set(USER_KEY, loginUser.get("userId"));
        SaLoginModel model = new SaLoginModel();
        String loginId = loginUser.get("userId") + "_" + UUID.randomUUID().toString().replace("-", "");
        StpUtil.login(loginId, model.setExtra(USER_KEY, loginUser.get("userId")));
        StpUtil.getTokenSession().set(LOGIN_USER_KEY, loginUser);
    }

    /**
     * 获取用户(多级缓存)
     */
    public static Map<String, Object> getLoginUser() {
        Map<String, Object> loginUser = (Map<String, Object>) SaHolder.getStorage().get(LOGIN_USER_KEY);
        if (loginUser != null) {
            return loginUser;
        }
        SaSession session = StpUtil.getTokenSession();
        if (null == session) {
            return null;
        }
        loginUser = (Map<String, Object>) session.get(LOGIN_USER_KEY);
        SaHolder.getStorage().set(LOGIN_USER_KEY, loginUser);
        return loginUser;
    }


    /**
     * 获取用户id
     */
    public static Long getUserId() {
        Long userId;
        try {
            userId = Long.parseLong(SaHolder.getStorage().get(USER_KEY).toString());
            if (null == userId) {
                userId = Long.parseLong(StpUtil.getExtra(USER_KEY).toString());
                SaHolder.getStorage().set(USER_KEY, userId);
            }
        } catch (Exception e) {
            return null;
        }
        return userId;
    }


}
