package com.github.yghysdr.base.utils;

import android.text.TextUtils;

import java.io.File;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Random;


public class FileUtil {

    /**
     * 获取随机的文件名
     *
     * @return
     */
    public static String getRandomFileName() {
        return DateUtils.getCurrentTime() + getRandomStr();
    }


    /**
     * 重命名文件
     *
     * @param oldPath 旧文件名，绝对目录
     * @param newPath 新文件名，绝对目录
     * @return
     */
    public static boolean rename(String oldPath, String newPath) {
        //1.判断参数阈值
        if (TextUtils.isEmpty(oldPath) || TextUtils.isEmpty(newPath)) {
            return false;
        }
        //2.比较是否变更了名称
        if (oldPath.endsWith(newPath)) { //和原来名称一样，不需要改变
            return true;
        }
        try {
            //3.根据新路径得到File类型数据
            File newFile = new File(newPath);
            //4.判断是否已经存在同样名称的文件（即出现重名）
            if (newFile.exists()) {
                return false;
            }
            //5.得到原文件File类型数据
            File file = new File(oldPath);
            //6.调用固有方法去重命名
            return file.renameTo(newFile);
        } catch (SecurityException e) {
            return false;
        }
    }


    /**
     * @param filepath 文件全路径名称，like mnt/sda/XX.xx
     * @return 根路径，like mnt/sda
     * @Description 得到文件所在路径（即全路径去掉完整文件名）
     */
    public static String getPathFromFilePath(final String filepath) {
        int pos = filepath.lastIndexOf('/');
        if (pos != -1) {
            return filepath.substring(0, pos);
        }
        return filepath;
    }

    public static String getNameFromFilePath(final String filepath) {
        int pos = filepath.lastIndexOf('/');
        if (pos != -1) {
            return filepath.substring(pos + 1);
        }
        return filepath;
    }

    /**
     * @param path1 文件路径
     * @param path2 文件名
     * @return 新路径
     * @Description 重新整合路径，将路径一和路径二通过'/'连接起来得到新路径
     */
    public static String makePath(final String path1, final String path2) {
        if (path1.endsWith(File.separator)) {
            return path1 + path2;
        }
        return path1 + File.separator + path2;
    }

    /**
     * 递归删除文件和文件夹
     *
     * @param file
     */
    public static void deleteDir(File file) {
        if (file.isFile()) {
            file.delete();
            return;
        }
        if (file.isDirectory()) {
            File[] childFile = file.listFiles();
            if (childFile == null || childFile.length == 0) {
                file.delete();
                return;
            }
            for (File f : childFile) {
                deleteDir(f);
            }
            file.delete();
        }
    }

    /**
     * 对files根据名字排序
     *
     * @param files
     * @return
     */
    public static File[] orderByName(File[] files) {
        List fileList = Arrays.asList(files);
        Arrays.sort(files);
        Collections.sort(fileList, (Comparator<File>) (o1, o2) -> {
            if (o1.isDirectory() && o2.isFile())
                return -1;
            if (o1.isFile() && o2.isDirectory())
                return 1;
            if (o1.getName().length() > o2.getName().length()) {
                return 1;
            } else if (o1.getName().length() < o2.getName().length()) {
                return -1;
            }
            return o1.getName().compareTo(o2.getName());
        });
        return files;
    }

    /**
     * @param strFile
     * @return
     */
    public static boolean fileIsExists(String strFile) {
        try {
            File f = new File(strFile);
            if (!f.exists()) {
                return false;
            }
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    public static File createDir(String file) {
        File appDir = new File(file);
        if (!appDir.exists()) {
            appDir.mkdir();
        }
        return appDir;
    }

    public static String getPath(File parent, String fileName) {
        return new File(parent, fileName).getAbsolutePath();
    }

    /**
     * 获取指定文件下大小
     *
     * @param file
     * @return
     * @throws Exception
     */
    public static long getFolderSize(File file) throws Exception {
        long size = 0;
        try {
            File[] fileList = file.listFiles();
            int size2 = 0;
            if (fileList != null) {
                size2 = fileList.length;
                for (int i = 0; i < size2; i++) {
                    // 如果下面还有文件
                    if (fileList[i].isDirectory()) {
                        size = size + getFolderSize(fileList[i]);
                    } else {
                        size = size + fileList[i].length();
                    }
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return size;
    }

    /**
     * 格式化单位
     * 计算缓存的大小
     *
     * @param size
     * @return
     */
    public static String getFormatSize(double size) {
        double kiloByte = size / 1024;
        if (kiloByte < 1) {
            // return size + "Byte";
            return "0 KB";
        }

        double megaByte = kiloByte / 1024;
        if (megaByte < 1) {
            BigDecimal result1 = new BigDecimal(Double.toString(kiloByte));
            return result1.setScale(2, BigDecimal.ROUND_HALF_UP)
                    .toPlainString() + " KB";
        }

        double gigaByte = megaByte / 1024;
        if (gigaByte < 1) {
            BigDecimal result2 = new BigDecimal(Double.toString(megaByte));
            return result2.setScale(2, BigDecimal.ROUND_HALF_UP)
                    .toPlainString() + " MB";
        }

        double teraBytes = gigaByte / 1024;
        if (teraBytes < 1) {
            BigDecimal result3 = new BigDecimal(Double.toString(gigaByte));
            return result3.setScale(2, BigDecimal.ROUND_HALF_UP)
                    .toPlainString() + " GB";
        }
        BigDecimal result4 = new BigDecimal(teraBytes);
        return result4.setScale(2, BigDecimal.ROUND_HALF_UP).toPlainString()
                + " TB";
    }


    private static String getRandomStr() {
        String a = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
        Random r = new Random();
        String result = "";
        for (int i = 0; i < 6; i++) {
            int n = r.nextInt(62);
            result += a.substring(n, n + 1);
        }
        return result;
    }

}
