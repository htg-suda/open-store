package com.htg.admin.service;

import com.htg.common.dto.user.UserDTO;
import com.htg.common.dto.user.UserModifyDTO;
import com.htg.common.dto.user.UserPageDTO;
import com.htg.common.entity.user.User;
import com.baomidou.mybatisplus.extension.service.IService;
import com.htg.common.result.RespPage;
import com.htg.common.vo.user.UserVO;

/**
 * <p>
 * 用户表 服务类
 * </p>
 *
 * @author htg
 * @since 2019-11-03
 */
public interface IUserService extends IService<User> {
    /* 通过用户名获取用户 */
    User getUserByUserName(String username);

    UserVO addUser(UserDTO addDTO, boolean isAdmin);

    UserVO modifyUser(UserModifyDTO modfiyDTO);

    RespPage<UserVO> listUser(UserPageDTO pageDTO);
}
