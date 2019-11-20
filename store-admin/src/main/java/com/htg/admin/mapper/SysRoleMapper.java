package com.htg.admin.mapper;

import com.htg.common.entity.user.SysRole;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 * 角色表 Mapper 接口
 * </p>
 *
 * @author htg
 * @since 2019-11-03
 */
public interface SysRoleMapper extends BaseMapper<SysRole> {

    SysRole getRoleByName(String name);

    List<SysRole> findRoleByUserId(Long userId);
}
