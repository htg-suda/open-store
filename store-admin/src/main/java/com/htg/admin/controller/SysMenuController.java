package com.htg.admin.controller;
import com.htg.admin.service.ISysMenuService;
import com.htg.common.dto.MenuModifyDTO;
import com.htg.common.dto.MenuPageDTO;
import com.htg.common.dto.SysMenuDTO;
import com.htg.common.entity.user.SysMenu;
import com.htg.common.result.CommonResult;
import com.htg.common.result.RespPage;
import com.htg.common.vo.MenuItemVO;
import com.htg.common.vo.SysMenuVO;
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
 * 菜单表 前端控制器
 * </p>
 *
 * @author htg
 * @since 2019-11-03
 */
@Slf4j
@Validated
@Api(tags = "002-菜单管理")
@RestController
@RequestMapping(value = "/admin/menu")
public class SysMenuController {
    @Autowired
    private ISysMenuService sysMenuService;

    @ApiOperation(value = "添加菜单")
    @ResponseBody
    @PostMapping("/add")
    public CommonResult<SysMenuVO> addMenu(@Valid @RequestBody SysMenuDTO addDTO) {
        SysMenuVO vo = sysMenuService.addSysMenu(addDTO);
        return CommonResult.success(vo);
    }

    @ApiOperation(value = "修改菜单")
    @ResponseBody
    @PostMapping("/modify")
    public CommonResult<SysMenuVO> modifyMenu(@Valid @RequestBody MenuModifyDTO modifyDTO) {
        SysMenuVO vo = sysMenuService.modifySysMenu(modifyDTO);
        return CommonResult.success(vo);
    }

    @ApiOperation(value = "删除菜单")
    @ResponseBody
    @PostMapping("/delete/{id}")
    public CommonResult<String> addMenu(Integer id) {
        sysMenuService.deleteMenu(id);
        return CommonResult.success("删除成功");
    }

    @ApiOperation(value = "列出菜单")
    @ResponseBody
    @PostMapping("/list")
    public CommonResult<RespPage<MenuItemVO>> listMenu(@Valid @RequestBody MenuPageDTO pageDTO) {
        return CommonResult.success(sysMenuService.listSysMenu(pageDTO));
    }


    @ApiOperation(value = "为用户查找并生成菜单")
    @ResponseBody
    @GetMapping("/menu_for_user/{userId}")
    public CommonResult<List<SysMenu>> getMenusForUser(@PathVariable(value = "userId") Long userId) {
        return CommonResult.success(sysMenuService.getMenusForUser(userId));
    }
}

