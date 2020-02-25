package com.htg.admin.service.impl;

import com.htg.common.entity.user.User;
import com.htg.admin.mapper.UserMapper;
import com.htg.admin.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author htg
 * @since 2020-02-22
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

}
