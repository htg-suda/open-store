package com.htg.admin.service;

import com.htg.common.dto.SysRoleDTO;
import com.htg.common.entity.user.SysRole;
import com.baomidou.mybatisplus.extension.service.IService;
import com.htg.common.vo.SysRoleVO;

import java.util.List;

/**
 * <p>
 * 角色表 服务类
 * </p>
 *
 * @author htg
 * @since 2019-11-03
 */
public interface ISysRoleService extends IService<SysRole> {

    SysRoleVO addRole(SysRoleDTO addDTO);

    void deleteRole(Integer id);

    List<SysRole> getRoleByUserId(Long userId);
}
