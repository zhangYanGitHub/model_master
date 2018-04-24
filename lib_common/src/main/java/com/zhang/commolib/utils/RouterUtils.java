package com.zhang.commolib.utils;

import com.alibaba.android.arouter.launcher.ARouter;

/**
 * Created by ZhangYan on 2018/4/24.
 */

public class RouterUtils {

    public static void skip(String path){
        ARouter.getInstance().build(path).navigation();
    }
}
