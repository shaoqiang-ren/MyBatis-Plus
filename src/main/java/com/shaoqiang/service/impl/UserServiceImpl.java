package com.shaoqiang.service.impl;

import com.shaoqiang.entity.User;
import com.shaoqiang.mapper.UserMapper;
import com.shaoqiang.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author renshaoqiang
 * @since 2023-06-19
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

}
