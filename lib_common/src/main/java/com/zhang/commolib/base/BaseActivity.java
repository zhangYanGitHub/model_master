package com.zhang.commolib.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.zhang.commolib.utils.AppLog;

import java.net.UnknownHostException;

/**
 * Created by 张俨 on 2017/12/7.
 */

public abstract class BaseActivity<view extends BaseView, Presenter extends BasePresenter<view>> extends AppCompatActivity {
    protected String TAG;
    protected Presenter prsenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        final int resourceID = getResourceID();
        TAG = this.getClass().getName();
        beforeContentView();
        ActivityStack.getInstance().addActivity(this);
        if (resourceID != 0) {
            setContentView(resourceID);
        } else {
            AppLog.e(TAG, " getResourceID() == 0");
            throw new RuntimeException(TAG + " :: getResourceID() == 0");
        }
        prsenter = createPresenter();
        if (prsenter == null) {
//            throw new RuntimeException("presenter has not init presenter == null");
        } else {
            prsenter.attachedView((view) this);
        }
        initData();
    }

    protected void beforeContentView() {

    }

    protected  Presenter createPresenter(){
        return null;
    }

    protected abstract int getResourceID();

    protected abstract void initData();

    @Override
    protected void onDestroy() {
        if (prsenter != null) {
            prsenter.detachView();
        }
        ActivityStack.getInstance().removeActivity(this);
        super.onDestroy();
    }
}
