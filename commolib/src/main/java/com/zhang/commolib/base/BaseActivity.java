package com.zhang.commolib.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.zhang.commolib.utils.AppLog;

/**
 * Created by 张俨 on 2017/12/7.
 */

public abstract class BaseActivity extends AppCompatActivity {
    protected static String TAG;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final int resourceID = getResourceID();
        TAG = this.getClass().getName();
        if (resourceID != 0) {
            setContentView(resourceID);
        } else {
            AppLog.e(TAG, " getResourceID() == 0");
        }
        initData();
    }

    protected abstract int getResourceID();

    protected abstract void initData();
}
