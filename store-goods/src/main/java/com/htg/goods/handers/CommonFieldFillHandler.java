package com.htg.goods.handers;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.util.Date;

@Slf4j
@Component
public class CommonFieldFillHandler implements MetaObjectHandler {

    @Override
    public void insertFill(MetaObject metaObject) {

        log.info("insert start fill");
        //   if (metaObject.getValue("updateTime") == null || StringUtils.isEmpty(metaObject.getValue("updateTime").toString())) {
        setFieldValByName("updateTime", new Date(), metaObject);
        // }

        //  if (metaObject.getValue("createTime") == null || StringUtils.isEmpty(metaObject.getValue("createTime").toString())) {
        setFieldValByName("createTime", new Date(), metaObject);
        //}

        setFieldValByName("updateUser", 0L, metaObject);
        setFieldValByName("createUser", 0L, metaObject);
    }

    @Override
    public void updateFill(MetaObject metaObject) {

        log.info("update start fill");
        setFieldValByName("updateTime", new Date(), metaObject);

        setFieldValByName("updateUser", 0L, metaObject);
    }


}
