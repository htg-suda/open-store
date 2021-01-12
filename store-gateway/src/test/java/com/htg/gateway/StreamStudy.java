package com.htg.gateway;


import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Slf4j
@SpringBootTest
@RunWith(SpringRunner.class)
public class StreamStudy {
    /* Stream 运行机制 */
    @Test
    public void streamStudy(){
        /* 随机产生 500 个6 位的 随机字符串*/
        log.info("====> start ");
        List<String> aList = Stream.generate(() -> RandomStringUtils.randomAlphabetic(6).toLowerCase()).limit(500)
                .peek(log::info).filter(item -> item.startsWith("a")).collect(Collectors.toList());
        log.info("aList is {}",aList);
        log.info("====> end");
    }

}
