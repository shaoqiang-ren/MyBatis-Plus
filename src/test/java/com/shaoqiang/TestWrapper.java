package com.shaoqiang;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.shaoqiang.entity.User;
import com.shaoqiang.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;
import java.util.List;

/**
 * @author: renshaoqiang
 * @date: 2023/6/19
 * @description: 测试条件构造器
 */
@SpringBootTest
public class TestWrapper {

    @Autowired
    private UserMapper userMapper;

    @Test
    void test1() {
        // 查询name不为空，且email不为空，且年龄大于20岁的用户
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper
                .isNotNull("name")
                .isNotNull("email")
                .ge("age", 20);
        List<User> users = userMapper.selectList(wrapper);
        users.forEach(System.out::println);
    }

    @Test
    void test2() {
        // 查询name为tom且age为28的用户,并且email包含3
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        HashMap<String, Object> map = new HashMap<>();
        map.put("name", "tom");
        map.put("age", 28);
        map.put("create_time", null);
        // false代表可以忽略值为null的那个条件, 不填默认为true
        wrapper.allEq(map, false).like("email", "3");
        User user = userMapper.selectOne(wrapper);
        System.out.println(user);
    }

    @Test
    void test3() {
        // 查询名字是tom的，且email是test3@baomidou.com的，且年龄大于等于28的用户
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper
                .eq("name", "tom")
                .eq("email", "test3@baomidou.com")
                .gt("age", 27);
        userMapper.selectList(wrapper).forEach(System.out::println);
    }

    @Test
    void test4() {
        // 查询年龄在24和28之间的用户
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper
                .between("age", 24, 28);
        userMapper.selectList(wrapper).forEach(System.out::println);
    }

    @Test
    void test5() {
        // 查询id小于4的用户id
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.inSql("id", "select id from user where id < 4");
        userMapper.selectList(wrapper).forEach(System.out::println);
    }

    @Test
    void test6() {
        // 根据name分组查询数据
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.select("max(id) as maxId", "name", "min(age) as minAge");
        wrapper.groupBy("name");
        userMapper.selectMaps(wrapper).forEach(System.out::println);
    }

    @Test
    void test7() {
        // 查询出Tom或者Jack中总年龄大于35的用户, 按照最大id降序排序
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.select("sum(age) as sumAge", "name", "max(id) as maxId")
                .eq("name", "Tom")
                .or()
                .eq("name", "Jack")
                .orderByDesc("maxId")
                .groupBy("name")
                .having("sum(age) > 35");
                //.orderByDesc("id");
        userMapper.selectMaps(wrapper).forEach(System.out::println);
    }

    @Test
    void test8() {
        // 查询出name为Jack，且age不是24的用户
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.nested(i -> i.eq("name", "Jack").ne("age", 24));
        userMapper.selectList(wrapper).forEach(System.out::println);
    }

    @Test
    void test9() {
        // 更新数据库中name为Jone的用户名称改为李四
        UpdateWrapper<User> updateWrapper = new UpdateWrapper<>();
        updateWrapper.set("name", "李四");
        updateWrapper.eq("name", "Jone");
        User user = new User();
        user.setId(1L);
        userMapper.update(user, updateWrapper);
    }
}
