package com.htg.admin.mapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.htg.common.dto.MenuPageDTO;
import com.htg.common.entity.user.SysMenu;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.htg.common.vo.MenuItemVO;

import java.util.List;

/**
 * <p>
 * 菜单表 Mapper 接口
 * </p>
 *
 * @author htg
 * @since 2019-11-03
 */
public interface SysMenuMapper extends BaseMapper<SysMenu> {

    List<MenuItemVO> listMenu(MenuPageDTO pageDTO, Page<SysMenu> page);

    List<SysMenu> findMenuForUser(Long userId);
}
