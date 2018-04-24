package com.zhang.model_a;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.zhang.commolib.base.BaseActivity;
import com.zhang.commolib.base.BasePresenter;
import com.zhang.commolib.constant.ConstantActivity;

@Route(path = ConstantActivity.Model_A_Test_Activity)
public class ModelATestActivity extends BaseActivity {


    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    @Override
    protected int getResourceID() {
        return R.layout.activity_model_atest;
    }

    @Override
    protected void initData() {

    }


}
