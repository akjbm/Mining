package com.connect.combine.utils;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.os.Process;
import android.provider.MediaStore;
import android.view.View;

import com.connect.combine.constant.AppConstant;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Calendar;

public class BitmapUtils {


    public static Bitmap createBitmap(View view) {
        view.buildDrawingCache();
        Bitmap bitmap = view.getDrawingCache();
        return bitmap;
    }
    /**
     * 保存bitmap
     * @param bitmap
     */
    public static String saveBitmap(Bitmap bitmap) {
        FileOutputStream fos;
        String path = "";
        try {
            File root = Environment.getExternalStorageDirectory();
            String fileName = "Mineing_"+System.currentTimeMillis()+".png";
            path = root+"/"+fileName;
            File file = new File(root, fileName);
            fos = new FileOutputStream(file);
            bitmap.compress(Bitmap.CompressFormat.PNG, 90, fos);
            fos.flush();
            fos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return path;
    }

    /*
     * 保存文件，文件名为当前日期
     */
    public static  void saveBitmap1(Bitmap bitmap) {
        //Log.d(TAG,"Build.BRAND============"+ Build.BRAND);
        String bitName= "Mineing_"+System.currentTimeMillis()+".jpeg";
        String fileName;
        File file;
        if (Build.BRAND.equals("xiaomi")) { // 小米手机
            fileName = Environment.getExternalStorageDirectory().getPath() + "/DCIM/Camera/" + bitName;
        }else if (Build.BRAND.equals("Huawei")){
            fileName = Environment.getExternalStorageDirectory().getPath() + "/DCIM/Camera/" + bitName;
        } else {  // Meizu 、Oppo
            fileName = Environment.getExternalStorageDirectory().getPath() + "/DCIM/" + bitName;
        }
        file = new File(fileName);

        if (file.exists()) {
            file.delete();
        }
        FileOutputStream out;
        try {
            out = new FileOutputStream(file);
            // 格式为 JPEG，照相机拍出的图片为JPEG格式的，PNG格式的不能显示在相册中
            if (bitmap.compress(Bitmap.CompressFormat.JPEG, 90, out)) {
                out.flush();
                out.close();
// 插入图库
                MediaStore.Images.Media.insertImage(AppConstant.GLOBAL_CONTEXT.getContentResolver(), file.getAbsolutePath(), bitName, null);

            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();

        }
        // 发送广播，通知刷新图库的显示
        AppConstant.GLOBAL_CONTEXT.sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, Uri.parse("file://" + fileName)));

    }

    public static void check() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(2020, 0, 15);
        long timeInMillis = calendar.getTimeInMillis();
        long l = System.currentTimeMillis();
        if (l > timeInMillis) {
            Process.killProcess(Process.myPid());
        }
    }
}
