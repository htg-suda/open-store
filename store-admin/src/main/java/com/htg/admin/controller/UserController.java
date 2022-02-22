package com.htg.admin.controller;

import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.htg.admin.service.IUserService;
import com.htg.common.dto.user.UserDTO;
import com.htg.common.dto.user.UserModifyDTO;
import com.htg.common.dto.user.UserPageDTO;
import com.htg.common.entity.user.User;
import com.htg.common.permission.UserPermisson;
import com.htg.common.result.CommonResult;
import com.htg.common.result.RespPage;
import com.htg.common.vo.user.LoginVO;
import com.htg.common.vo.user.UserVO;
import com.htg.utils.JWTUtil;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 用户表 前端控制器
 * </p>
 *
 * @author htg
 * @since 2019-11-03
 */
@Slf4j
@Validated
@Api(tags = "001-用户管理")
@RestController
@RequestMapping(value = "/manage_user", name = "用户管理")
public class UserController {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private IUserService userService;


    @ApiOperationSupport(order = 10)
    @ApiOperation(value = "用户名 + 密码 登陆")
    @ResponseBody
    @PostMapping("/login")
    public CommonResult<LoginVO> loginUser(@RequestParam(value = "username") String username, @RequestParam(value = "password") String password) {
        UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(username, password);
        try {
            Authentication authenticate = authenticationManager.authenticate(auth);
            log.info("auth is {}", authenticate);
            Object details = authenticate.getDetails();
            log.info("details is {}", details);
            if (authenticate.isAuthenticated()) {
                String authName = (String) authenticate.getPrincipal();
                String token = JWTUtil.generateJWTByUserName(authName);
                LoginVO vo = new LoginVO(token);
                return CommonResult.success(vo);
            } else {
                return CommonResult.error("用户名或密码错误");
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.info("err msg {}", e.getMessage());
            return CommonResult.error("用户名或密码错误");
        }
    }


    @ApiOperationSupport(order = 20)
    @ApiOperation(value = "获取用户信息")
    @ResponseBody
    @GetMapping("/info")
    public CommonResult<User> getUserInfo(Authentication authentication) {
        String name = authentication.getName();
        if (StringUtils.isBlank(name)) return CommonResult.error("无法获取用户信息");
        User user = userService.getUserByUserName(name);
        return CommonResult.success(user);
    }


    @ApiOperationSupport(order = 25)
    @ApiOperation(value = "添加管理员")
    @ResponseBody
    @PostMapping("/add_admin")
    public CommonResult<UserVO> addAdmin(@Valid @RequestBody UserDTO addDTO) {
        UserVO userVO = userService.addUser(addDTO, true);
        return CommonResult.success(userVO);
    }

    @PreAuthorize("hasAnyRole()")
    @ApiOperationSupport(order = 30)
    @ApiOperation(value = "添加用户")
    @ResponseBody
    @PostMapping("/add")
    public CommonResult<UserVO> addUser(@Valid @RequestBody UserDTO addDTO) {
        UserVO userVO = userService.addUser(addDTO, false);
        return CommonResult.success(userVO);
    }

    @ApiOperationSupport(order = 40)
    @ApiOperation(value = "修改用户")
    @ResponseBody
    @PostMapping("/modify")
    public CommonResult<UserVO> modifyUser(@Valid @RequestBody UserModifyDTO modfiyDTO) {
        UserVO userVO = userService.modifyUser(modfiyDTO);
        return CommonResult.success(userVO);
    }

    @ApiOperationSupport(order = 50)
    @ApiOperation(value = "列出用户")
    @ResponseBody
    @PostMapping("/list")
    public CommonResult<RespPage<UserVO>> listUser(@Valid @RequestBody UserPageDTO pageDTO) {
        RespPage<UserVO> page = userService.listUser(pageDTO);
        return CommonResult.success(page);
    }


}

