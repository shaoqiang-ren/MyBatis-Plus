package com.shaoqiang.config;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.OptimisticLockerInnerInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author: renshaoqiang
 * @date: 2023/6/18
 * @description: 配置类
 */
@Configuration
public class MybatisPlusConfig {

    /**
     * @author: renshaoqiang
     * @description:  MyBatis-Plus拦截器配置
     * @date: 2023/6/18 21:15
     */
    @Bean
    public MybatisPlusInterceptor mybatisPlusInterceptor() {
        MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
        // 配置乐观锁组件
        interceptor.addInnerInterceptor(new OptimisticLockerInnerInterceptor());
        // 配置分页查询组件
        interceptor.addInnerInterceptor(new PaginationInnerInterceptor(DbType.MYSQL));
        return interceptor;
    }

}
