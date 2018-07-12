package io.yghysdr.article;

/**
 * Created by yghysdr on 2017/7/27.
 */

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.net.Uri;
import android.text.TextUtils;
import android.widget.Toast;

import java.io.File;

public class ShareUtils {

    private static Context mContext;

    public static void init(Context context) {
        mContext = context;
    }

    public static final String WEIXIN_PACKAGE_NAME = "";
    public static final String QQ_PACKAGE_NAME = "";

    /**
     * 分享文字
     *
     * @param packageName
     * @param content
     * @param title
     * @param subject
     */
    public static void shareText(String packageName, String className, String content, String title, String subject) {
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_SEND);
        intent.setType("text/plain");
        if (stringCheck(className) && stringCheck(packageName)) {
            ComponentName componentName = new ComponentName(packageName, className);
            intent.setComponent(componentName);
        } else if (stringCheck(packageName)) {
            intent.setPackage(packageName);
        }

        intent.putExtra(Intent.EXTRA_TEXT, content);
        if (null != title && !TextUtils.isEmpty(title)) {
            intent.putExtra(Intent.EXTRA_TITLE, title);
        }
        if (null != subject && !TextUtils.isEmpty(subject)) {
            intent.putExtra(Intent.EXTRA_SUBJECT, subject);
        }
        intent.putExtra(Intent.EXTRA_TITLE, title);
        Intent chooserIntent = Intent.createChooser(intent, "分享到：");
        mContext.startActivity(chooserIntent);
    }

    /**
     * 分享网页
     */
    public static void shareUrl(String packageName, String className, String content, String title, String subject) {
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_SEND);
        intent.setType("text/plain");
        if (stringCheck(className) && stringCheck(packageName)) {
            ComponentName componentName = new ComponentName(packageName, className);
            intent.setComponent(componentName);
        } else if (stringCheck(packageName)) {
            intent.setPackage(packageName);
        }

        intent.putExtra(Intent.EXTRA_TEXT, content);
        if (null != title && !TextUtils.isEmpty(title)) {
            intent.putExtra(Intent.EXTRA_TITLE, title);
        }
        if (null != subject && !TextUtils.isEmpty(subject)) {
            intent.putExtra(Intent.EXTRA_SUBJECT, subject);
        }
        intent.putExtra(Intent.EXTRA_TITLE, title);
        Intent chooserIntent = Intent.createChooser(intent, "分享到：");
        mContext.startActivity(chooserIntent);
    }

    /**
     * 分享图片
     */
    public static void shareImg(String packageName, String className, File file) {
        if (file.exists()) {
            Uri uri = Uri.fromFile(file);
            Intent intent = new Intent();
            intent.setAction(Intent.ACTION_SEND);
            intent.setType("image/*");
            if (stringCheck(packageName) && stringCheck(className)) {
                intent.setComponent(new ComponentName(packageName, className));
            } else if (stringCheck(packageName)) {
                intent.setPackage(packageName);
            }
            intent.putExtra(Intent.EXTRA_STREAM, uri);
            Intent chooserIntent = Intent.createChooser(intent, "分享到:");
            mContext.startActivity(chooserIntent);
        } else {
            Toast.makeText(mContext, "文件不存在", Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * 分享音乐
     */
    public static void shareAudio(String packageName, String className, File file) {
        if (file.exists()) {
            Uri uri = Uri.fromFile(file);
            Intent intent = new Intent();
            intent.setAction(Intent.ACTION_SEND);
            intent.setType("audio/*");
            if (stringCheck(packageName) && stringCheck(className)) {
                intent.setComponent(new ComponentName(packageName, className));
            } else if (stringCheck(packageName)) {
                intent.setPackage(packageName);
            }
            intent.putExtra(Intent.EXTRA_STREAM, uri);
            Intent chooserIntent = Intent.createChooser(intent, "分享到:");
            mContext.startActivity(chooserIntent);
        } else {
            Toast.makeText(mContext, "文件不存在", Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * 分享视频
     */
    public static void shareVideo(String packageName, String className, File file) {
        setIntent("video/*", packageName, className, file);
    }

    public static void setIntent(String type, String packageName, String className, File file) {
        if (file.exists()) {
            Uri uri = Uri.fromFile(file);
            Intent intent = new Intent();
            intent.setAction(Intent.ACTION_SEND);
            intent.setType(type);
            if (stringCheck(packageName) && stringCheck(className)) {
                intent.setComponent(new ComponentName(packageName, className));
            } else if (stringCheck(packageName)) {
                intent.setPackage(packageName);
            }
            intent.putExtra(Intent.EXTRA_STREAM, uri);
            Intent chooserIntent = Intent.createChooser(intent, "分享到:");
            mContext.startActivity(chooserIntent);
        } else {
            Toast.makeText(mContext, "文件不存在", Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * 分享多张图片和文字至朋友圈
     *
     * @param title
     * @param packageName
     * @param className
     * @param file        图片文件
     */
    public static void shareImgToWXCircle(String title, String packageName, String className, File file) {
        if (file.exists()) {
            Uri uri = Uri.fromFile(file);
            Intent intent = new Intent();
            ComponentName comp = new ComponentName(packageName, className);
            intent.setComponent(comp);
            intent.setAction(Intent.ACTION_SEND);
            intent.setType("image/*");
            intent.putExtra(Intent.EXTRA_STREAM, uri);
            intent.putExtra("Kdescription", title);
            mContext.startActivity(intent);
        } else {
            Toast.makeText(mContext, "文件不存在", Toast.LENGTH_SHORT).show();
        }


    }

    /**
     * 是否安装分享app
     *
     * @param packageName
     */
    public static boolean checkInstall(String packageName) {
        try {
            mContext.getPackageManager().getPackageInfo(packageName, PackageManager.GET_ACTIVITIES);
            return true;
        } catch (NameNotFoundException e) {
            e.printStackTrace();
            Toast.makeText(mContext, "请先安装应用app", Toast.LENGTH_SHORT).show();
            return false;
        }
    }

    /**
     * 跳转官方安装网址
     */
    public static void toInstallWebView(String url) {
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_VIEW);
        intent.setData(Uri.parse(url));
        mContext.startActivity(intent);
    }

    public static boolean stringCheck(String str) {
        if (null != str && !TextUtils.isEmpty(str)) {
            return true;
        } else {
            return false;
        }
    }
}