package com.zhang.main;

import android.view.View;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.zhang.commolib.base.BaseActivity;
import com.zhang.commolib.base.BasePresenter;
import com.zhang.commolib.constant.ConstantActivity;
import com.zhang.commolib.utils.RouterUtils;

@Route(path = ConstantActivity.Splash_Activity)
public class SplashActivity extends BaseActivity {


    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    @Override
    protected int getResourceID() {
        return R.layout.activity_splash;
    }

    @Override
    protected void initData() {
//
    }


    public void onClick(View view) {
        RouterUtils.skip(ConstantActivity.Main_Activity);
    }
}
