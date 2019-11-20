package com.htg.admin.controller;


import com.htg.admin.service.ISysRoleService;
import com.htg.common.dto.SysRoleDTO;
import com.htg.common.entity.user.SysRole;
import com.htg.common.result.CommonResult;
import com.htg.common.vo.SysRoleVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * <p>
 * 角色表 前端控制器
 * </p>
 *
 * @author htg
 * @since 2019-11-03
 */
@Slf4j
@Validated
@Api(tags = "003-角色管理")
@RestController
@RequestMapping("/admin/role")
public class SysRoleController {
    /*  */
    @Autowired
    private ISysRoleService roleService;

    @ApiOperation(value = "添加角色")
    @ResponseBody
    @PostMapping("/add")
    public CommonResult<SysRoleVO> addRole(@Valid @RequestBody SysRoleDTO roleDTO) {
        SysRoleVO vo = roleService.addRole(roleDTO);
        return CommonResult.success(vo);
    }

    /* 删除角色*/

    @ApiOperation(value = "删除角色")
    @ResponseBody
    @PostMapping("/delete/{id}")
    public CommonResult<SysRoleVO> deleteRole(Integer id) {
        roleService.deleteRole(id);
        return CommonResult.success("删除成功");
    }

    @ApiOperation(value = "为用户查找其所有的角色")
    @ResponseBody
    @GetMapping("/role_for_user/{userId}")
    public CommonResult<List<SysRole>> getRoleByUserId(@PathVariable(value = "userId") Long userId) {
        return CommonResult.success(roleService.getRoleByUserId(userId));
    }
}

