package com.example.ttlock.utils;

import android.content.Context;
import android.support.annotation.DrawableRes;
import android.text.TextUtils;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

/**
 * File: org.rubik.commonlib.util.GlideUtils.java
 * Description: xxx
 *
 * @author Done
 * @date 2019/1/30
 */

public class GlideUtils {

    public static void setCircleImage(Context context, ImageView imageView, String url,int drawable) {
        if (!TextUtils.isEmpty(url)
                && imageView != null
                && context != null) {
            Glide.with(context).load(url).placeholder(drawable).diskCacheStrategy(DiskCacheStrategy.NONE).into(imageView);
        }
    }

    public static void setCircleImage(Context context, ImageView imageView, @DrawableRes int resId) {
        if (imageView != null
                && context != null) {
            Glide.with(context).load(resId).into(imageView);
        }
    }

    public static void loadUrlImage(Context context, String url, ImageView imageView) {
        if (imageView == null) {
            return;
        }

        Glide.with(context)
                .load(url)
                .into(imageView);
    }
}
