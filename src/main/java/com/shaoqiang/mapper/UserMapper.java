package com.shaoqiang.mapper;

import com.shaoqiang.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author renshaoqiang
 * @since 2023-06-19
 */
@Component
public interface UserMapper extends BaseMapper<User> {

    List<User> test1();
}
