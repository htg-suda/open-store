package com.htg.admin.service;
import com.htg.common.dto.user.MenuModifyDTO;
import com.htg.common.dto.user.MenuPageDTO;
import com.htg.common.dto.user.SysMenuDTO;
import com.htg.common.entity.user.SysMenu;
import com.baomidou.mybatisplus.extension.service.IService;
import com.htg.common.result.RespPage;
import com.htg.common.vo.user.MenuItemVO;
import com.htg.common.vo.user.SysMenuVO;

import java.util.List;

/**
 * <p>
 * 菜单表 服务类
 * </p>
 *
 * @author htg
 * @since 2019-11-03
 */

public interface ISysMenuService extends IService<SysMenu> {

    SysMenuVO addSysMenu(SysMenuDTO addDTO);

    void deleteMenu(Integer id);

    SysMenuVO modifySysMenu(MenuModifyDTO modifyDTO);

    /* 列出菜单*/
    RespPage<MenuItemVO> listSysMenu(MenuPageDTO pageDTO);

    /* 为用户 查找菜单 */
    List<SysMenu> getMenusForUser(Long userId);
}
