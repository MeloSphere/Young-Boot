package cn.young.boot.satoken.impl;

import cn.dev33.satoken.dao.SaTokenDao;
import cn.dev33.satoken.util.SaFoxUtil;
import cn.yong.boot.redis.utils.RedisUtil;

import javax.annotation.Resource;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @author Mole. meiko_ooo@163.com
 * @since 2025/1/30 15:38
 */

public class YoungSaTokenDao implements SaTokenDao {
    @Resource
    private RedisUtil redisUtil;

    @Override
    public String get(String key) {
        return redisUtil.getValue(key);
    }

    @Override
    public void set(String key, String value, long timeout) {
        if (timeout == 0 || timeout <= SaTokenDao.NOT_VALUE_EXPIRE) {
            return;
        }
        // 判断是否为永不过期
        if (timeout == SaTokenDao.NEVER_EXPIRE) {
            redisUtil.setValue(key, value);
        } else {
            redisUtil.setValue(key, value, timeout * 1000);
        }
    }

    @Override
    public void update(String key, String value) {
        long expire = getTimeout(key);
        // -2 = 无此键
        if (expire == SaTokenDao.NOT_VALUE_EXPIRE) {
            return;
        }
        this.set(key, value, expire);
    }

    @Override
    public void delete(String key) {
        redisUtil.remove(key);
    }

    @Override
    public long getTimeout(String key) {
        long timeout = redisUtil.getTimeToLive(key);
        return timeout < 0 ? timeout : timeout / 1000;
    }

    @Override
    public void updateTimeout(String key, long timeout) {
        // 判断是否想要设置为永久
        if (timeout == SaTokenDao.NEVER_EXPIRE) {
            long expire = getTimeout(key);
            if (expire == SaTokenDao.NEVER_EXPIRE) {
                // 如果其已经被设置为永久，则不作任何处理
            } else {
                // 如果尚未被设置为永久，那么再次set一次
                this.set(key, this.get(key), timeout);
            }
            return;
        }
        redisUtil.expire(key, Duration.ofSeconds(timeout));
    }

    @Override
    public Object getObject(String key) {
        return redisUtil.getValueObj(key);
    }

    @Override
    public void setObject(String key, Object object, long timeout) {
        if (timeout == 0 || timeout <= SaTokenDao.NOT_VALUE_EXPIRE) {
            return;
        }
        // 判断是否为永不过期
        if (timeout == SaTokenDao.NEVER_EXPIRE) {
            redisUtil.setValue(key, object);
        } else {
            redisUtil.setValue(key, object, timeout * 1000);
        }

    }

    @Override
    public void updateObject(String key, Object object) {
        long expire = getObjectTimeout(key);
        // -2 = 无此键
        if (expire == SaTokenDao.NOT_VALUE_EXPIRE) {
            return;
        }
        this.setObject(key, object, expire);
    }

    @Override
    public void deleteObject(String key) {
        redisUtil.remove(key);
    }

    @Override
    public long getObjectTimeout(String key) {
        long timeout = redisUtil.getTimeToLive(key);
        return timeout < 0 ? timeout : timeout / 1000;
    }

    @Override
    public void updateObjectTimeout(String key, long timeout) {
        // 判断是否想要设置为永久
        if (timeout == SaTokenDao.NEVER_EXPIRE) {
            long expire = getObjectTimeout(key);
            if (expire == SaTokenDao.NEVER_EXPIRE) {
                // 如果其已经被设置为永久，则不作任何处理
            } else {
                // 如果尚未被设置为永久，那么再次set一次
                this.setObject(key, this.getObject(key), timeout);
            }
            return;
        }
        redisUtil.expire(key, Duration.ofSeconds(timeout));
    }

    @Override
    public List<String> searchData(String prefix, String keyword, int start, int size, boolean sortType) {
        Collection<String> keys = redisUtil.keys(prefix + "*" + keyword + "*");
        List<String> list = new ArrayList<>(keys);
        return SaFoxUtil.searchList(list, start, size, sortType);
    }
}
