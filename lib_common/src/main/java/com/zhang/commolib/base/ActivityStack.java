package com.zhang.commolib.base;

import android.text.TextUtils;

import com.zhang.commolib.utils.AppLog;

import java.lang.ref.WeakReference;
import java.util.Stack;

/**
 * Created by ZhangYan on 2018/4/24.
 */

public class ActivityStack {
    private final static String TAG = ActivityStack.class.getName();
    private static ActivityStack stack = new ActivityStack();

    private Stack<WeakReference<BaseActivity>> activityStack = new Stack<>();

    public static ActivityStack getInstance() {
        return stack;
    }

    public void addActivity(BaseActivity activity) {
        if (checkParam(activity)) return;
        WeakReference<BaseActivity> baseActivityWeakReference = new WeakReference<>(activity);
        boolean add = activityStack.add(baseActivityWeakReference);
        if (add) {
            AppLog.d(TAG, "add activity success :" + activity.TAG);
        } else {
            AppLog.d(TAG, "add activity failed :" + activity.TAG);
        }
    }

    private boolean checkParam(BaseActivity activity) {
        if (activity == null || activity.isFinishing()) {
            AppLog.e(TAG, "method addActivity param activity == NULL || activity is destroy");
            return true;
        }
        return false;
    }

    public void removeActivity(BaseActivity activity) {
        if (checkParam(activity)) return;

        WeakReference<BaseActivity> baseActivityWeakReference = new WeakReference<>(activity);
        boolean remove = activityStack.remove(baseActivityWeakReference);
        if (remove) {
            AppLog.d(TAG, "remove activity success :" + activity.TAG);
        } else {
            AppLog.d(TAG, "remove activity failed :" + activity.TAG);
        }
    }

    public void finishAllActivities() {
        int size = activityStack.size();
        for (int i = 0; i < size; i++) {
            WeakReference<BaseActivity> weakReference = activityStack.get(i);
            if (weakReference != null && weakReference.get() != null) {
                AppLog.d(TAG, "finish activity success :" + weakReference.get().TAG);
                weakReference.get().finish();
            }
        }
    }

    /**
     * 结束指定的Activity
     *
     * @param activity
     */
    public void finishSpecifiedActivity(BaseActivity activity) {
        if (checkParam(activity)) return;
        int size = activityStack.size();
        for (int i = 0; i < size; i++) {
            WeakReference<BaseActivity> weakReference = activityStack.get(i);
            if (weakReference != null && weakReference.get() != null) {
                BaseActivity baseActivity = weakReference.get();
                if (TextUtils.equals(activity.TAG, baseActivity.TAG)) {
                    AppLog.d(TAG, "finish activity success :" + weakReference.get().TAG);
                    baseActivity.finish();
                }

            }
        }
    }

}
