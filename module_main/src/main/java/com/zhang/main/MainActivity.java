package com.zhang.main;

import android.view.View;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.zhang.commolib.base.BaseActivity;
import com.zhang.commolib.base.BasePresenter;
import com.zhang.commolib.constant.ConstantActivity;
import com.zhang.commolib.utils.RouterUtils;

@Route(path = ConstantActivity.Main_Activity)
public class MainActivity extends BaseActivity {


    @Override
    protected int getResourceID() {
        return R.layout.activity_main;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    public void onClick(View view) {
        RouterUtils.skip(ConstantActivity.Model_A_Test_Activity);
    }
}
