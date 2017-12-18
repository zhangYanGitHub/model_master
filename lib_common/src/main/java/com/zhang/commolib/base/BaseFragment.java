package com.zhang.commolib.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zhang.commolib.utils.AppLog;

/**
 * Created by 张俨 on 2017/12/7.
 */

public abstract class BaseFragment extends Fragment {

    protected static String TAG;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final int resourceID = getResourceID();
        TAG = this.getClass().getName();
        if (resourceID != 0) {
            inflater.inflate(resourceID, container, false);
        } else {
            AppLog.e(TAG, " getResourceID() == 0");
            getActivity().finish();
        }
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    protected abstract int getResourceID();
}
