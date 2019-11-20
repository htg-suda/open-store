package com.htg.admin.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.htg.admin.constants.UserConst;
import com.htg.admin.mapper.UserMapper;
import com.htg.admin.service.IUserService;
import com.htg.common.constants.ComConst;
import com.htg.common.dto.UserDTO;
import com.htg.common.dto.UserModifyDTO;
import com.htg.common.dto.UserPageDTO;
import com.htg.common.entity.user.User;
import com.htg.common.exception.BizExcp;
import com.htg.common.result.CodeEnum;
import com.htg.common.result.RespPage;
import com.htg.common.vo.UserVO;
import org.springframework.beans.BeanUtils;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author htg
 * @since 2019-11-03
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {
    /* 通过用户名获取用户 */
    @Override
    public User getUserByUserName(String username) {
        User user = baseMapper.getUserByName(username);
        return user;
    }


    public UserVO addUser(UserDTO addDTO) {
        String username = addDTO.getUsername();
        String tel = addDTO.getTel();
        String email = addDTO.getEmail();
        User userExt = null;

        userExt = baseMapper.getUserByName(username);
        if (userExt != null) throw new BizExcp(CodeEnum.USERNAMRE_HAS_EXIST);
        userExt = baseMapper.getUserByTel(tel);
        if (userExt != null) throw new BizExcp(CodeEnum.TEL_HAS_EXIST);
        baseMapper.getUserByEmail(email);
        if (userExt != null) throw new BizExcp(CodeEnum.EMAIL_HAS_EXIST);

        User user = new User();
        BeanUtils.copyProperties(addDTO, user);
        user.setDeleted(ComConst.UNDELETED);
        user.setStaus(UserConst.ACTIVE);
        PasswordEncoder encoder = new BCryptPasswordEncoder();
        user.setPassword(encoder.encode(user.getPassword()));
        save(user);

        UserVO vo = new UserVO();
        BeanUtils.copyProperties(user, vo);
        return vo;
    }

    @Override
    public UserVO modifyUser(UserModifyDTO modfiyDTO) {

        return null;
    }

    @Override
    public RespPage<UserVO> listUser(UserPageDTO pageDTO) {
        Integer pageNum = pageDTO.getPageNum();
        Integer pageSize = pageDTO.getPageSize();
        Page<User> page = new Page<>(pageNum, pageSize);
        List<User> users = baseMapper.listUser(pageDTO, page);
        List<UserVO> voList = users.stream().map(v -> {
            UserVO vo = new UserVO();
            BeanUtils.copyProperties(v, vo);
            return vo;
        }).collect(Collectors.toList());
        long total = page.getTotal();
        return new RespPage<>(voList, total);
    }
}
