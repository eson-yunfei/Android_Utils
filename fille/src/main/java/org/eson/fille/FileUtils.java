package org.eson.fille;

import android.os.Environment;
import android.util.Log;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by xiaoyunfei on 2017/12/28.
 */

public class FileUtils {


    private static String TAG = "FileUtils";

    /**
     * 获取系统的存储路径
     *
     * @return
     */
    public static String getStoragePath() {
        String sdCardPath = "";

        String storageState = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(storageState)) {

            sdCardPath = Environment.getExternalStorageDirectory()
                    .getAbsolutePath();
        } else {

            sdCardPath = Environment.getRootDirectory().getAbsolutePath();
        }
        return sdCardPath;
    }


    /**
     * 创建文件夹 单层
     *
     * @param folderPath folderPath
     * @return 是否成功
     */
    public static boolean createFolder(String folderPath) {
        if (exists(folderPath)) {
            return true;
        }
        File file = new File(folderPath);
        return file.mkdir();
    }

    /**
     * 创建文件夹 多层
     *
     * @param folderPath folderPath
     * @return 是否成功
     */
    public static boolean createFolders(String folderPath) {
        if (exists(folderPath)) {
            return true;
        }
        File file = new File(folderPath);
        return file.mkdirs();
    }

    /**
     * 创建文件
     *
     * @param fileName
     * @return
     */
    public static boolean createFile(String fileName) {
        if (exists(fileName)) {
            return true;
        }

        try {
            File file = new File(fileName);
            return file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 删除本地文件
     *
     * @param filePath
     * @return
     */
    public static boolean delFile(String filePath) {
        if (exists(filePath)) {
            Log.e(TAG, "文件不存在");
            return true;
        }
        File file = new File(filePath);
        return file.delete();
    }

    /**
     * 文件重命名
     *
     * @param path    文件目录
     * @param oldName 原来的文件名
     * @param newName 新文件名
     */
    public static void reNameFile(String path, String oldName, String newName) {
        if (!oldName.equals(newName)) {//新的文件名和以前文件名不同时,才有必要进行重命名
            File oldFile = new File(path + "/" + oldName);
            File newFile = new File(path + "/" + newName);
            if (!oldFile.exists()) {
                return;//重命名文件不存在
            }
            if (newFile.exists())//若在该目录下已经有一个文件和新文件名相同，则不允许重命名
                Log.d(TAG, newName + "已经存在！");
            else {
                oldFile.renameTo(newFile);
            }
        } else {

            Log.d(TAG, "新文件名和旧文件名相同...");
        }
    }


    /**
     * 写数据到文件
     *
     * @param path
     * @param content
     */
    public static void write(String path, String content) {
        try {
            FileOutputStream fos = new FileOutputStream(path);
            fos.write(content.getBytes());
            fos.flush();
            fos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * 判断本地文件
     *
     * @param filePath
     * @return
     */
    public static boolean exists(String filePath) {
        File file = new File(filePath);
        return file.exists();
    }
}
