package com.connect.combine.utils;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.youth.banner.loader.ImageLoader;

public class GlideImageLoader extends ImageLoader {

    public Context appContext;

    public GlideImageLoader(Context context) {
        super();
        appContext = context;
    }

    public GlideImageLoader() {
        super();
    }
    @Override
    public void displayImage(Context context, Object path, ImageView imageView) {

        //RequestOptions transform = new RequestOptions().transform(new CenterCrop(), new RoundedCorners(30));//处理CenterCrop的情况
        Glide.with(appContext).load(path).into(imageView);
    }

    @Override
    public ImageView createImageView(Context context) {
        return super.createImageView(context);
    }
}