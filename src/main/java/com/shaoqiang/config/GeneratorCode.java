package com.shaoqiang.config;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;

import java.util.Collections;

/**
 * @author: renshaoqiang
 * @date: 2023/6/19
 * @description: MyBatis-Plus自动代码生成器
 */
public class GeneratorCode {
    public static void main(String[] args) {
        System.out.println("111");
        generation("mybatis-plus", "user");
    }

    /**
     * @author: renshaoqiang
     * @description: 根据数据库名称和表名生成相应代码
     * @date: 2023/6/19 17:42
     */
    public static void generation(String databaseName, String... tableName) {
        String url = "jdbc:mysql://localhost:3306/" + databaseName + "?useUnicode=true&characterEncoding=utf-8&serveTimeZone=Asia/Shanghai";
        FastAutoGenerator.create(url, "root", "rsq200106")
                // 全局配置
                .globalConfig(builder -> {
                    builder
                            // 设置作者
                            .author("renshaoqiang")
                            // 开启Swagger
                            .enableSwagger()
                            // 输出文件目录
                            .outputDir(System.getProperty("user.dir") + "/src/main/java");
                })
                // 包名配置
                .packageConfig(builder -> {
                    builder
                            // 设置父包名
                            .parent("com.shaoqiang")
                            // 设置实体类包名
                            .entity("entity")
                            // 控制层包名
                            .controller("controller")
                            // service包名
                            .service("service")
                            // service实现的包名
                            .serviceImpl("service.impl")
                            // 设置mapper层包名
                            .mapper("mapper")
                            .pathInfo(Collections.singletonMap(OutputFile.xml, System.getProperty("user.dir") + "/src/main/resources/dao"));
                })
                // 策略配置
                .strategyConfig(builder -> {
                    builder
                            // 要生成代码的表名
                            .addInclude(tableName)
                            // 过滤的表前缀
                            .addTablePrefix("ums_")

                            // 开启实体策略配置
                            .entityBuilder()
                            // 开启实体类lombok
                            .enableLombok()
                            // 开启链式模式
                            .enableChainModel()
                            // 数据库表映射到实体类的命名策略，默认下划线转驼峰命名:NamingStrategy.underline_to_camel
                            .naming(NamingStrategy.underline_to_camel)
                            // 数据库表字段映射到实体的命名策略,默认为 null，未指定按照 naming 执行
                            .columnNaming(NamingStrategy.underline_to_camel)
                            // 全局主键类型，自增类型
                            .idType(IdType.AUTO)
                            // 格式化实体名称，%s取消首字母I
                            .formatFileName("%s")

                            // 开启mapper配置
                            .mapperBuilder()
                            // 启用 BaseResultMap 生成
                            .enableBaseResultMap()
                            // 启用xml文件中的BaseColumnList
                            .enableBaseColumnList()
                            // 格式化xml文件名称
                            .formatXmlFileName("%sDao")
                            // 格式化mapper文件名称
                            .formatMapperFileName("%sMapper")

                            // 开启service配置
                            .serviceBuilder()
                            // 格式化service接口文件名称
                            .formatServiceFileName("%sService")
                            // 格式化service接口实现类文件名称
                            .formatServiceImplFileName("%sServiceImpl")

                            // 开启controller层配置
                            .controllerBuilder()
                            // 开启生成@RestController 控制器
                            .enableRestStyle();
                            // 格式化controller层文件名称
                            // .formatFileName("%sController");
                })
                .execute();
    }
}
