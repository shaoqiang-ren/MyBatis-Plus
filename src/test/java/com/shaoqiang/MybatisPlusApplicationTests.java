package com.shaoqiang;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.shaoqiang.entity.User;
import com.shaoqiang.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

@SpringBootTest
class MybatisPlusApplicationTests {

    @Autowired
    private UserMapper userMapper;

    @Test
    void contextLoads() {
        List<User> users = userMapper.selectList(null);
        users.forEach(System.out::println);
    }

    @Test
    void testInsert() {
        User user = new User();
        user.setName("张三");
        user.setAge(18);
        user.setEmail("1623176432@qq.com");
        int result = userMapper.insert(user);
        System.out.println(result);
        // User(id=1670316873553649666, name=张三, age=18, email=1623176432@qq.com)
        System.out.println(user);
    }

    @Test
    void testUpdate() {
        User user = new User();
        user.setId(4L);
        user.setName("李四");
        int row = userMapper.updateById(user);
        System.out.println(row);
    }

    @Test
    void testLocalDateTime() {
        System.out.println(LocalDateTime.now());
        LocalDateTime now = LocalDateTime.now();
        System.out.println(now);
    }

    /**
     * @author: renshaoqiang
     * @description: 测试更新成功的乐观锁
     */
    @Test
    void testOptimisticLocker1() {
        User user = userMapper.selectById(5L);
        user.setName("李四");
        int row = userMapper.updateById(user);
        System.out.println(row);
    }

    /**
     * @author: renshaoqiang
     * @description: 测试更新失败的乐观锁
     */
    @Test
    void testOptimisticLocker2() {
        User user1 = userMapper.selectById(3L);
        user1.setName("李四");

        User user2 = userMapper.selectById(3L);
        user2.setName("王五");
        userMapper.updateById(user2);

        userMapper.updateById(user1);
    }

    @Test
    void testSelectById() {
        // 通过Id查询
        User user = userMapper.selectById(2L);
        System.out.println(user);

        System.out.println("-------------------------");

        // 通过多个Id查询
        List<User> users = userMapper.selectBatchIds(Arrays.asList(1L, 2L, 3L));
        users.forEach(System.out::println);

        System.out.println("-------------------------");

        // 条件查询，通过Map进行封装条件
        HashMap<String, Object> map = new HashMap<>();
        map.put("name", "李四");
        map.put("age", 21);

        List<User> users1 = userMapper.selectByMap(map);
        users1.forEach(System.out::println);
    }

    @Test
    void testPage() {
        Page<User> page = new Page<>(1, 5);
        // 设置每页的最大数量
        page.setMaxLimit(4L);
        Page<User> userPage = userMapper.selectPage(page, null);
        userPage.getRecords().forEach(System.out::println);
        // 获取总条目数
        userPage.getTotal();
    }

    @Test
    void testDelete() {
        userMapper.deleteById(5L);
    }

    @Test
    void testLogicDelete() {
        int row = userMapper.deleteById(2L);
        System.out.println(row);

        // 查询时也会自带条件：deleted = 0
        List<User> users = userMapper.selectList(null);
        users.forEach(System.out::println);
    }

    @Test
    void testMapper() {
        userMapper.test1().forEach(System.out::println);
    }


}
