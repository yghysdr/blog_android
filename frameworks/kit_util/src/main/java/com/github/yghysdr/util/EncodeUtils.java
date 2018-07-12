package com.github.yghysdr.util;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;

public class EncodeUtils {
    /**
     * bitmap转换成Base64格式的字符串
     *
     * @param bit
     * @return
     */
    public static String Bitmap2StrByBase64(Bitmap bit) {
        try {
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            bit.compress(Bitmap.CompressFormat.JPEG, 40, bos);//参数100表示不压缩
            byte[] bytes = bos.toByteArray();
            return Base64.encodeToString(bytes, Base64.DEFAULT);
        } catch (Exception e) {
            return "";
        }
    }

    public static String path2StrByBase64(String path) {
        File file = new File(path);
        try {
            FileInputStream inputFile = new FileInputStream(file);
            byte[] buffer = new byte[(int) file.length()];
            inputFile.read(buffer);
            inputFile.close();
            return Base64.encodeToString(buffer, Base64.DEFAULT);
        } catch (Exception e) {
            return "";
        }

    }

    public static Bitmap stringToBitmap(String string) {
        Bitmap bitmap = null;
        try {
            byte[] bitmapArray = Base64.decode(string.split(",")[0], Base64.DEFAULT);
            bitmap = BitmapFactory.decodeByteArray(bitmapArray, 0, bitmapArray.length);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bitmap;
    }

}