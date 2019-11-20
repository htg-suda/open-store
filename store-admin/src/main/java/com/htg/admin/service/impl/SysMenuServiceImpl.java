package com.htg.admin.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.htg.admin.mapper.SysMenuMapper;
import com.htg.admin.service.ISysMenuService;
import com.htg.common.constants.MenuConst;
import com.htg.common.dto.MenuModifyDTO;
import com.htg.common.dto.MenuPageDTO;
import com.htg.common.dto.SysMenuDTO;
import com.htg.common.entity.user.SysMenu;
import com.htg.common.exception.BizExcp;
import com.htg.common.exception.DaoExcp;
import com.htg.common.result.CodeEnum;
import com.htg.common.result.RespPage;
import com.htg.common.vo.MenuItemVO;
import com.htg.common.vo.SysMenuVO;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 菜单表 服务实现类
 * </p>
 *
 * @author htg
 * @since 2019-11-03
 */
@Service
public class SysMenuServiceImpl extends ServiceImpl<SysMenuMapper, SysMenu> implements ISysMenuService {
    @Override
    public SysMenuVO addSysMenu(SysMenuDTO menuDTO) {
        SysMenu sysMenu = new SysMenu();
        Integer parentId = menuDTO.getParentId();
        if (parentId != 0) {
            SysMenu parentMenu = getById(parentId);
            if (parentMenu == null) throw new BizExcp(CodeEnum.PARENT_MENU_NOT_EXIST);
            Integer type = parentMenu.getType();
            if (type.equals(MenuConst.TYPE_BUTTON)) throw new BizExcp(CodeEnum.MENU_PARENT_NOT_BUTTON);
        }
        BeanUtils.copyProperties(menuDTO, sysMenu);
        if (!save(sysMenu)) throw new DaoExcp();
        SysMenuVO vo = new SysMenuVO();
        BeanUtils.copyProperties(sysMenu, vo);
        return vo;
    }

    @Override
    public void deleteMenu(Integer id) {
        if (!removeById(id)) throw new DaoExcp();
    }

    @Override
    public SysMenuVO modifySysMenu(MenuModifyDTO modifyDTO) {
        SysMenu menu = getById(modifyDTO.getId());
        if (menu == null) throw new BizExcp(CodeEnum.MENU_NOT_EXIST);
        Integer parentId = modifyDTO.getParentId();
        if (!parentId.equals(menu.getParentId()) && parentId != 0) {
            SysMenu parentMenu = getById(parentId);
            if (parentMenu == null) throw new BizExcp(CodeEnum.PARENT_MENU_NOT_EXIST);
            Integer type = parentMenu.getType();
            if (type.equals(MenuConst.TYPE_BUTTON)) throw new BizExcp(CodeEnum.MENU_PARENT_NOT_BUTTON);
        }
        BeanUtils.copyProperties(modifyDTO, menu);
        if (!save(menu)) throw new DaoExcp();
        SysMenuVO vo = new SysMenuVO();
        BeanUtils.copyProperties(menu, vo);
        return vo;
    }

    /* 列出菜单*/
    @Override
    public RespPage<MenuItemVO> listSysMenu(MenuPageDTO pageDTO) {
        Page<SysMenu> page = new Page<>(pageDTO.getPageNum(), pageDTO.getPageSize());
        List<MenuItemVO> menuList = baseMapper.listMenu(pageDTO, page);
        return new RespPage<>(menuList, page.getTotal());
    }

    /* 为用户 查找菜单 */
    @Override
    public List<SysMenu> getMenusForUser(Long userId) {
        return baseMapper.findMenuForUser(userId);
    }

}
