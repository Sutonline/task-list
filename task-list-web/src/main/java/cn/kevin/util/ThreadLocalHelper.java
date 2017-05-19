package cn.kevin.util;

import cn.kevin.domain.User;

/**
 * Created by yongkang.zhang on 2017/5/19.
 */
public class ThreadLocalHelper {

    private static ThreadLocal<String> threadLocal = new ThreadLocal<>();

    public static final void set(String name) {
        threadLocal.set(name);
    }

    public static final String get() {
        return threadLocal.get();
    }


}
