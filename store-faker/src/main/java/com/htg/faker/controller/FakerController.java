package com.htg.faker.controller;

import com.apifan.common.random.source.*;
import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.htg.faker.entity.UserEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping(value = "fake")
public class FakerController {
    @Autowired
    private RestTemplate restTemplate;

    private int count = 0;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @PostMapping(value = "/test01")
    public String getTest02(String param) {
        log.info("param is {}", param);
        //  String s = OtherSource.getInstance().randomChinese(10);
        String sentence = OtherSource.getInstance().randomChineseSentence();
        String s1 = PersonInfoSource.getInstance().randomChineseName();
        //  log.info(s);
        log.info(s1);
        log.info(sentence);
        String uuid = IdWorker.get32UUID();
        long id = IdWorker.getId();
        log.info(uuid);
        log.info(id + "");
        String l = PersonInfoSource.getInstance().randomEnglishName();
        log.info(l + "");
        return "===> " + param + " " + count++;
    }


    @PostMapping(value = "/addUser")
    public String addUser(Integer cnt) {
        long start = System.currentTimeMillis();
        String sql001 = "select count(*) from tb_user where username=?";
        //随机获取省份
        for (Integer i = 0; i < cnt; i++) {
            String nickname = PersonInfoSource.getInstance().randomChineseNickName(5);
            String prv = AreaSource.getInstance().randomProvince();
            String companyName = OtherSource.getInstance().randomCompanyName("上海");
            String phoneType = OtherSource.getInstance().randomMobileModel();

            String username = PersonInfoSource.getInstance().randomEnglishName().replace(" ", "_");
            Long count = jdbcTemplate.queryForObject(sql001, Long.class, username);
            while (count > 0) {
                log.info("exists username : {}", username);
                username = PersonInfoSource.getInstance().randomEnglishName().replace(" ", "_");
                count = jdbcTemplate.queryForObject(sql001, Long.class, username);
            }
            String sql = "insert into tb_user (uid,sid,username,nickname,prv,commpany,phone_type,create_time,update_time) value (?,?,?,?,?,?,?,now(),now())";
            try {
                int update = jdbcTemplate.update(sql, IdWorker.get32UUID(), IdWorker.getId(), username, nickname, prv, companyName, phoneType);
            } catch (Exception e) {
                log.error(e.getMessage(), e);
            }
        }
        long end = System.currentTimeMillis();
        long d = end - start;
        //   log.info("insert {} ; time is {}s", cnt, d / 1000);
        return "time is " + d;
    }

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private ObjectMapper objectMapper;

    @PostMapping(value = "/addUserToRedis")
    public String addUserToRedis(Integer cnt) throws JsonProcessingException {
        long start = System.currentTimeMillis();
        //随机获取省份
        for (Integer i = 0; i < cnt; i++) {
            String nickname = PersonInfoSource.getInstance().randomChineseNickName(5);
            String prv = AreaSource.getInstance().randomProvince();
            String companyName = OtherSource.getInstance().randomCompanyName("上海");
            String phoneType = OtherSource.getInstance().randomMobileModel();
            String username = PersonInfoSource.getInstance().randomEnglishName().replace(" ", "_");
            Map<String, Object> map = new HashMap<>();
            map.put("uid", IdWorker.get32UUID());
            map.put("sid", IdWorker.getId());
            map.put("username", username);
            map.put("nickname", nickname);
            map.put("prv", prv);
            map.put("commpany", companyName);
            map.put("phone_type", phoneType);
            stringRedisTemplate.opsForList().leftPush("tb_user", objectMapper.writeValueAsString(map));
        }
        long end = System.currentTimeMillis();
        long d = end - start;
        //     log.info("insert {} ; time is {}s", cnt, d / 1000);
        return "time is " + d;
    }

    @Autowired
    private MongoTemplate mongoTemplate;

    @PostMapping(value = "/addUserToMongoDB")
    public String addUserToMongoDB(Integer cnt) throws JsonProcessingException {
        long start = System.currentTimeMillis();
        //随机获取省份
        for (Integer i = 0; i < cnt; i++) {
            String nickname = PersonInfoSource.getInstance().randomChineseNickName(5);
            String prv = AreaSource.getInstance().randomProvince();
            String companyName = OtherSource.getInstance().randomCompanyName("上海");
            String phoneType = OtherSource.getInstance().randomMobileModel();
            String username = PersonInfoSource.getInstance().randomEnglishName().replace(" ", "_");
            String pastDate = DateTimeSource.getInstance().randomPastDate("yyyy-MM-dd");
            UserEntity entity=new UserEntity();
            entity.setCommpany(companyName);
            entity.setUid(IdWorker.get32UUID());
            entity.setSid(IdWorker.getId());
            entity.setPrv(prv);
            entity.setNickname(nickname);
            entity.setUsername(username);
            entity.setPhone_type(phoneType);
            entity.setBirth(pastDate);
            mongoTemplate.save(entity);
        }
        long end = System.currentTimeMillis();
        long d = end - start;
        //     log.info("insert {} ; time is {}s", cnt, d / 1000);
        return "time is " + d;
    }


    @PostMapping(value = "/findUser/{name}")
    @ResponseBody
    public UserEntity findUser(@PathVariable String name) throws JsonProcessingException {
        Query query=new Query(Criteria.where("birth").is("2021-01-01"));
        List<UserEntity> userEntities =  mongoTemplate.find(query,UserEntity.class);

        Query query1=new Query(Criteria.where("_id").is("19e4ac3eee06a3280c70ba766a30fb6e"));
        UserEntity one = mongoTemplate.findOne(query1, UserEntity.class);
        return one;
    }



}
