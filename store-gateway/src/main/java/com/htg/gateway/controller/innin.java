package com.htg.gateway.controller;

import java.io.File;
import java.util.stream.Stream;

public class innin {
    public static void main(String[] args) {
        File file = new File("/home/htg/baidunetdiskdownload/图解Java数据结构和算法/视频");
        File[] files = file.listFiles();
        Stream.of(files).filter(v -> {
            //  System.out.println(v.getName());
            return v.getName().contains("老韩图解Java数据结构和算法");
        }).forEach(v -> {
            String name = v.getName();
            String substring = name.substring(0, 4);
            int length = "老韩图解Java数据结构和算法".length();
            String substring1 = name.substring(5 + length);
            String st = substring + substring1;
            System.out.println(st);
            v.renameTo(new File("/home/htg/baidunetdiskdownload/图解Java数据结构和算法/视频/" + st));
        });
    }
}
