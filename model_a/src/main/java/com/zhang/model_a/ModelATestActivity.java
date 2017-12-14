package com.zhang.model_a;

import android.os.Bundle;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.zhang.commolib.base.BaseActivity;

@Route(path = "/model_a/ModelATestActivity")
public class ModelATestActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    protected int getResourceID() {
        return R.layout.activity_model_atest;
    }

    @Override
    protected void initData() {

    }


}
