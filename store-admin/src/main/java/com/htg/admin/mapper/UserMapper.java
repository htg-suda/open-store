package com.htg.admin.mapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.htg.common.dto.user.UserPageDTO;
import com.htg.common.entity.user.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 * 用户表 Mapper 接口
 * </p>
 *
 * @author htg
 * @since 2019-11-03
 */

public interface UserMapper extends BaseMapper<User> {

    User getUserByName(String username);

    User getUserByTel(String tel);

    User getUserByEmail(String email);

    List<User> listUser(UserPageDTO pageDTO, Page<User> page);

}
