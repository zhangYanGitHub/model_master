package com.zhang.commolib.activity;

import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.alibaba.android.arouter.launcher.ARouter;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.target.SimpleTarget;

/**
 * Created by 张俨 on 2017/12/12.
 */
// 新建一个Activity用于监听Schame事件,之后直接把url传递给ARouter即可
public class SchameFilterActivity extends AppCompatActivity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Uri uri = getIntent().getData();
        ARouter.getInstance().build(uri).navigation();
        finish();
    }

//    public static void loadImage2(ImageView imageView, Object url, int resId) {
//
//        if (Build.VERSION.SDK_INT >= 23) {
//            imageView.setImageResource(resId);
//            Glide.with(SchameFilterActivity.this).load(url).diskCacheStrategy(DiskCacheStrategy.RESOURCE).centerCrop().error(com.zhang.commolib.R.drawable.ic_check).into(new SimpleTarget<GlideDrawable>() {
//                @Override
//                public void onResourceReady(GlideDrawable resource, GlideAnimation<? super GlideDrawable> glideAnimation) {
//                    imageView.setImageDrawable(resource);
//                }
//            });
//        } else {
//
////            Glide.with(context).load(url).diskCacheStrategy(DiskCacheStrategy.SOURCE).placeholder(pre_drawable).error(error_drawable).into(imageView);
//            Glide.with(this)
//                    .load(url)
//                    .placeholder(resId)
////                .diskCacheStrategy(DiskCacheStrategy.ALL)
//                    .centerCrop()
//                    .error(com.zhang.commolib.R.drawable.ic_camera)
//                    .into(imageView);
//        }
//    }
}
