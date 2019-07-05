package com.base.commlibs.utils;

import com.blankj.utilcode.util.FileIOUtils;
import com.blankj.utilcode.util.FileUtils;

import java.io.File;
import java.util.List;

/**
 * 替换xml中的dp和sp
 * 将RES_LAYOUT修改指定module位置
 * @author:gaoruishan
 * @date:202019-07-03/16:01
 * @email:grs0515@163.com
 */
public class ReplaceXml {
    // 最大替换dp值
    private static final int MAX_DP = 420;
    // 最大替换sp值
    private static final int MAX_SP = 40;
    //TODO 指定module位置
//    private static String MODULE_RES_LAYOUT = "/libraries/baselib";
    private static String MODULE_RES_LAYOUT = "/libraries/dhcc_res";

    public static void main(String[] args) {
        MODULE_RES_LAYOUT = System.getProperty("user.dir") + MODULE_RES_LAYOUT + "/src/main/res/layout/";
        //读取指定文件
//        new FileThread(replace_xml).start();
        //读取layout目录下文件
        replaceAll();

    }

    private static void replaceAll() {
        List<File> files = FileUtils.listFilesInDir(MODULE_RES_LAYOUT);
        for (File f : files) {
            new FileThread(f.getName()).start();
        }
    }

    static class FileThread extends Thread {
        private final String path;
        private final String name;

        FileThread(String name) {
            this.name = name;
            this.path = MODULE_RES_LAYOUT + name;
        }

        @Override
        public void run() {
            super.run();
            String s = FileIOUtils.readFile2String(path);
            //判断是否已经处理了
            if (s.contains("dp\"") || s.contains("sp\"")) {
                s = replaceDp(s);
                s = replaceSp(s);
                boolean fileByDeleteOldFile = FileUtils.createFileByDeleteOldFile(path);
                if (fileByDeleteOldFile) {
                    System.out.println(name + "---执行中");
                    FileIOUtils.writeFileFromString(path, s);
                }
            }
            System.out.println(name + "---完成");
        }

        /**
         * 替换dp
         * @param s
         * @return
         */
        private String replaceDp(String s) {
            String str = s;
            for (int i = 0; i < MAX_DP; i++) {
                //="260dp"
                String olds = "=\"" + i + "dp\"";
                //="@dimen/dp_260"
                String news = "=\"@dimen/dp_" + i + "\"";
                str = str.replaceAll(olds, news);
            }
            return str;
        }

        private String replaceSp(String s) {
            String str = s;
            for (int i = 0; i < MAX_SP; i++) {
                //="16sp"
                String olds = "=\"" + i + "sp\"";
                //="@dimen/sp_260"
                String news = "=\"@dimen/sp_" + i + "\"";
                str = str.replaceAll(olds, news);
            }
            return str;
        }
    }
}
