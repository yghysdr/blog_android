package com.github.yghysdr.http;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import io.reactivex.Observable;

public class FileDownUtils {

    /**
     * 下载文件
     *
     * @param url      下载地址
     * @param filePath 下载文件绝对目录
     * @return
     */
    public static Observable<String> downFile(String url, String filePath) {
        return ApiRetrofit
                .getInstance()
                .getRetrofit()
                .create(IDownService.class)
                .download(url)
                .map(responseBody -> {
                    writeFile(responseBody.byteStream(), filePath);
                    return filePath;
                })
                .onErrorReturn(throwable -> throwable.getMessage());
    }

    /**
     * 保存文件
     *
     * @param inputString
     * @param filePath
     */
    private static void writeFile(InputStream inputString, String filePath) {
        File file = new File(filePath);
        if (file.exists()) {
            file.delete();
        }
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(file);

            byte[] b = new byte[1024];

            int len;
            while ((len = inputString.read(b)) != -1) {
                fos.write(b, 0, len);
            }
            inputString.close();
            fos.close();

        } catch (FileNotFoundException e) {
            throw new UserRuntimeException(e.getMessage());
        } catch (IOException e) {
            throw new UserRuntimeException(e.getMessage());
        }
    }

}
