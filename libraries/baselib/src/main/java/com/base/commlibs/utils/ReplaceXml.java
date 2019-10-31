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

    private final static String[] MODULE_RES_LAYOUTS = {
            "/libraries/baselib", "/libraries/dhcc_res"
            , "/libraries/dhcc_health", "/libraries/dhcc_infusion"
    };

    public static void main(String[] args) {
        for (String moduleResLayout : MODULE_RES_LAYOUTS) {
            String resLayout = System.getProperty("user.dir") + moduleResLayout + "/src/main/res/layout/";
            //读取layout目录下文件
            replaceAll(resLayout);
        }
        //读取指定文件
//        new FileThread(replace_xml).start();
    }

    private static void replaceAll(String resLayout) {
        List<File> files = FileUtils.listFilesInDir(resLayout);
        for (File f : files) {
            new FileThread(f.getName(), resLayout).start();
        }
    }

    static class FileThread extends Thread {
        private final String path;
        private final String name;

        FileThread(String name, String resLayout) {
            this.name = name;
            this.path = resLayout + name;
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
