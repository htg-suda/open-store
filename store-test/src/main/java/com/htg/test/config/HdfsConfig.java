package com.htg.test.config;

import lombok.extern.slf4j.Slf4j;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.springframework.context.annotation.Bean;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

@Slf4j
@org.springframework.context.annotation.Configuration
public class HdfsConfig {

    public static String hdfsPath = "hdfs://hadoop10:8020";
    public static String hdfsName = "root";

    @Bean
    public static FileSystem getFS() {
        FileSystem fileSystem = null;
        Configuration configuration = new Configuration();
        configuration.set("fs.defaultFS", hdfsPath);
        try {
            fileSystem = FileSystem.get(new URI(hdfsPath), configuration, hdfsName);
            return fileSystem;
        } catch (IOException | URISyntaxException | InterruptedException e) {
            log.error(e.getMessage(), e);
        }
        return fileSystem;
    }
}
