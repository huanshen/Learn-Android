package com.example.shenjiaqi.myapplication.MySQL;

import android.app.Application;
import android.content.Context;

/**
 * 框的运行时，提供 ApplicationContext 等全局数据。
 */
public final class AppRuntime {

    /** package */ static Application sApplication;

    /**
     * 获取ApplicationContext对象
     * @return
     */
    public static Context getAppContext() {
        return sApplication;
    }

    /**
     * 获取Application对象
     * @return
     */
    public static Application getApplication() {
        return  sApplication;
    }
}
