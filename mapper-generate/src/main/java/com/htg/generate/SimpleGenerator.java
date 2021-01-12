package com.htg.generate;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.converts.MySqlTypeConvert;
import com.baomidou.mybatisplus.generator.config.rules.DbColumnType;
import com.baomidou.mybatisplus.generator.config.rules.IColumnType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;

public class SimpleGenerator {
    public static void main(String[] args) {
        String path = System.getProperty("user.dir") + "/src/main/java/";

        /* 表名的前缀 */
       // String tablePrefix[] = {"tb"};
        String includeTable = null;

        AutoGenerator mpg = new AutoGenerator();
        // 全局配置
        GlobalConfig gc = new GlobalConfig();
        // 文件输入路径
        gc.setOutputDir(path);
        gc.setAuthor("htg");
        gc.setFileOverride(true); //是否覆盖
        gc.setActiveRecord(false);// 不需要ActiveRecord特性的请改为false
        gc.setEnableCache(false);// XML 二级缓存
        gc.setBaseResultMap(true);// XML ResultMap
        gc.setBaseColumnList(true);// XML columList

        gc.setMapperName("%sMapper");
        gc.setXmlName("%sMapper");
        gc.setServiceName("I%sService");
        gc.setServiceImplName("%sServiceImpl");
        gc.setControllerName("%sController");
        mpg.setGlobalConfig(gc);

        // 数据源配置
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setDbType(DbType.MYSQL);
        dsc.setDriverName("com.mysql.cj.jdbc.Driver");
        dsc.setUsername("root");
        dsc.setPassword("123456");
        dsc.setUrl("jdbc:mysql://10.0.0.11:1106/db_system?characterEncoding=utf8");
        mpg.setDataSource(dsc);

        // 策略配置
        StrategyConfig strategy = new StrategyConfig();
        strategy.setCapitalMode(false);

      //  strategy.setTablePrefix(tablePrefix);// 此处可以修改为您的表前缀

        strategy.setNaming(NamingStrategy.underline_to_camel);// 表名生成策略,下划线变驼峰

        /* 需要生成 的表 */
        strategy.setInclude(new String[]{"t_menu"});

        /* 基础的 base 类,所有生成的 entity 都继承自 这个类 */
       // strategy.setSuperEntityClass("com.htg.common.base.BaseEntity");
        /* entity  base类中的 字段,这里的字段不会出现在 entity 类中 */
        strategy.setSuperEntityColumns(new String[]{"create_time", "update_time", "create_user", "update_user"});
        //strategy.setSuperControllerClass("com.adshow.core.common.com.htg.test.controller.BaseController");
        /* 生成的 Controller 是Rest 风格的*/
        strategy.setRestControllerStyle(true);
        /* 乐观锁字段名*/
        strategy.setVersionFieldName("version");
        /* entity 的 lombok 的类 */
        strategy.setEntityLombokModel(true);
        /* 是否生成 字段的注释 */
        strategy.setEntityTableFieldAnnotationEnable(true);
        /*Boolean类型字段是否移除is前缀*/
        strategy.setEntityBooleanColumnRemoveIsPrefix(true);

        strategy.setEntityBuilderModel(true);
        mpg.setStrategy(strategy);

        // 包配置
        PackageConfig pc = new PackageConfig();

        /* com.htg.mybatis_plus_study.com.htg.test.controller*/
        pc.setParent("com");
        pc.setModuleName("htg");


        pc.setController("generate.controller");
        pc.setService("generate.service");
        pc.setServiceImpl("generate.service.impl");
        pc.setMapper("generate.mapper");
        pc.setXml("generate.mapper.mapping");
        pc.setEntity("generate.entity");
        mpg.setPackageInfo(pc);
        // 执行生成
        mpg.execute();
    }

}
