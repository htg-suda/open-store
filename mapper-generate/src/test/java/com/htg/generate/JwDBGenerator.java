package com.htg.generate;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import org.junit.Test;

public class JwDBGenerator {
    @Test
    public void generateCode(){
        String path = System.getProperty("user.dir") + "/src/main/java/";

        /* 表名的前缀 */
        String tablePrefix[] = {"tb"};
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
        gc.setSwagger2(true);
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
        dsc.setUrl("jdbc:mysql://127.0.0.1:1106/vendingmachine?characterEncoding=utf8");
      //  dsc.setUrl("jdbc:mysql://127.0.0.1:1106/jw_css?characterEncoding=utf8");
        mpg.setDataSource(dsc);

        // 策略配置
        StrategyConfig strategy = new StrategyConfig();
        strategy.setCapitalMode(false);

        strategy.setTablePrefix(tablePrefix);// 此处可以修改为您的表前缀

        strategy.setNaming(NamingStrategy.underline_to_camel);// 表名生成策略,下划线变驼峰

        /* 需要生成 的表 */
        strategy.setInclude(new String[]{"tb_robot_power_record"});

        /* 基础的 base 类,所有生成的 entity 都继承自 这个类 */
     //   strategy.setSuperEntityClass("com.htg.common.base.BaseEntity");
        /* entity  base类中的 字段,这里的字段不会出现在 entity 类中 */
      //  strategy.setSuperEntityColumns(new String[]{"create_time", "update_time", "create_user", "update_user"});
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
        pc.setModuleName("jwai");
        pc.setController("controller");
        pc.setService("service");
        pc.setServiceImpl("service.impl");
        pc.setMapper("mapper");
        pc.setXml("mapper.mapping");
        pc.setEntity("entity");
        mpg.setPackageInfo(pc);
        // 执行生成
        mpg.execute();
    }


}
