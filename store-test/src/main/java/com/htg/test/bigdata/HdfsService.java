package com.htg.test.bigdata;


import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

// https://blog.csdn.net/zhouzhiwengang/article/details/103224394
import java.io.IOException;

@Slf4j
@Service
public class HdfsService {

    @Autowired
    private FileSystem fs;


    public boolean createFolder(String path) {
        Path src = new Path(path);
        try {
            return fs.mkdirs(src);
        } catch (IOException e) {
            log.error(e.getMessage());
        }

        return false;
    }


    public void createFile(String path, MultipartFile file) {
        if (StringUtils.isEmpty(path)) {
            return;
        }
        String fileName = file.getName();
        Path newPath = new Path(path + "/" + fileName);
        // 打开一个输出流
        FSDataOutputStream outputStream;
        try {
            outputStream = fs.create(newPath);
            outputStream.write(file.getBytes());
            outputStream.close();
        } catch (IOException e) {
            log.error(e.getMessage());
        }
    }
}