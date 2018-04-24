package com.zhang.commolib.base;

import android.support.v4.app.Fragment;

import java.lang.ref.WeakReference;

/**
 * Created by ZhangYan on 2018/3/27.
 */

public abstract class BasePresenter<view extends BaseView> {

    protected WeakReference<BaseView> baseViewWeakReference;

    public void attachedView(BaseView view){
        baseViewWeakReference = new WeakReference<BaseView>(view);
    }

    public void detachView(){
        if(baseViewWeakReference != null){
            baseViewWeakReference.clear();
        }
    }
}
