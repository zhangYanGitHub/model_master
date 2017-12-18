package com.zhang.main;

import android.view.View;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.zhang.commolib.base.BaseActivity;

@Route(path = "/main_model/SplashActivity")
public class SplashActivity extends BaseActivity {


    @Override
    protected int getResourceID() {
        return R.layout.activity_splash;
    }

    @Override
    protected void initData() {
//
    }


    public void onClick(View view) {
        ARouter.getInstance().build("/main_model/mainActivity").navigation();
    }
}
