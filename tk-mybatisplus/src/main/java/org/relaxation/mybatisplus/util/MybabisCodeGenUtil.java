package org.relaxation.mybatisplus.util;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;

/**
 * @program MybabisCodeGenUtil
 * @description: Mybatisplus 代码生成工具
 * @author: jjsunw
 * @create: 2020/1/8
 **/
public class MybabisCodeGenUtil {
    private MybabisCodeGenUtil() {
    }

    private static final String AUTHOR = "jjsunw";
    private static final String DB_DOMAIN = "rm-cn-x0r3h12hm000hyzo.rwlb.rds.aliyuncs.com/tmp_scctp";
    private static final String DB_USERNAME = "u_jjsunw";
    private static final String DB_PASSWORD = "u_jjsunw@123";
    private static final String PROJECT_NAME = "tk-dynamicdatasource";
    private static final String PACKAGE_NAME = "org.relaxation.dynamicdatasource";
    private static final String TABLE_PRIFIX = "t_";


    public static void main(String[] args) {
        genCodes("scctp_group");
    }

    public static void genCodes(String tableNames) {
        String projectPath = System.getProperty("user.dir");// projectPath
        //1、全局配置
        GlobalConfig config = new GlobalConfig();
        config.setActiveRecord(true)//开启AR模式
                .setAuthor(AUTHOR)//设置作者
                //生成路径(一般都是生成在此项目的src/main/java下面)
                .setOutputDir(projectPath + "/" + PROJECT_NAME + "/src/main/java")
                .setFileOverride(true)//第二次生成会把第一次生成的覆盖掉
                .setIdType(IdType.AUTO)//主键策略
                .setServiceName("%sService")//生成的service接口名字首字母是否为I，这样设置就没有I
                .setBaseResultMap(true)//生成resultMap
                .setBaseColumnList(true);//在xml中生成基础列
        //2、数据源配置
        DataSourceConfig dataSourceConfig = new DataSourceConfig();
        dataSourceConfig.setDbType(DbType.MYSQL)//数据库类型
                .setDriverName("com.mysql.cj.jdbc.Driver")
                .setUrl("jdbc:mysql://" + DB_DOMAIN + "?useUnicode=true&characterEncoding=UTF-8&zeroDateTimeBehavior=convertToNull&serverTimezone=Asia/Shanghai&useSSL=true")
                .setUsername(DB_USERNAME)
                .setPassword(DB_PASSWORD);
        //3、策略配置
        StrategyConfig strategyConfig = new StrategyConfig();
        strategyConfig.setCapitalMode(true)//开启全局大写命名
                //.setDbColumnUnderline(true)//表名字段名使用下划线
                .setNaming(NamingStrategy.underline_to_camel)//下划线到驼峰的命名方式
                .setTablePrefix(TABLE_PRIFIX)//表名前缀
                .setRestControllerStyle(true)
                .setEntityLombokModel(true)//使用lombok
                .setInclude(tableNames);//逆向工程使用的表
        //4、包名策略配置
        PackageConfig packageConfig = new PackageConfig();
        packageConfig.setParent(PACKAGE_NAME)//设置包名的parent
                //.setMapper("dao.agent")
                .setMapper("dao")
                .setService("service")
                .setController("controller")
                .setEntity("entity")
                .setXml("dao/mapper");//设置xml文件的目录
                //.setXml("dao/agent/dao");//设置xml文件的目录
        //5、整合配置
        AutoGenerator autoGenerator = new AutoGenerator();
        autoGenerator.setGlobalConfig(config)
                .setDataSource(dataSourceConfig)
                .setStrategy(strategyConfig)
                .setPackageInfo(packageConfig);
        //6、执行
        autoGenerator.execute();
    }

}
