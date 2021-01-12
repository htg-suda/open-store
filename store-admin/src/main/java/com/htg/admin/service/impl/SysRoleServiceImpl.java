package com.htg.admin.service.impl;

import com.htg.common.dto.user.SysRoleDTO;
import com.htg.common.entity.user.SysRole;
import com.htg.admin.mapper.SysRoleMapper;
import com.htg.admin.service.ISysRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.htg.common.exception.BizExcp;
import com.htg.common.exception.DaoExcp;
import com.htg.common.result.CodeEnum;
import com.htg.common.vo.user.SysRoleVO;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 角色表 服务实现类
 * </p>
 *
 * @author htg
 * @since 2019-11-03
 */
@Service
public class SysRoleServiceImpl extends ServiceImpl<SysRoleMapper, SysRole> implements ISysRoleService {

    @Override
    public SysRoleVO addRole(SysRoleDTO addDTO) {
        String name = addDTO.getName();
        SysRole roleExt = baseMapper.getRoleByName(name);
        if (roleExt != null) throw new BizExcp(CodeEnum.ROLE_HAS_EXIST);
        SysRole role = new SysRole();
        BeanUtils.copyProperties(addDTO, role);
        save(role);
        SysRoleVO vo = new SysRoleVO();
        BeanUtils.copyProperties(role, vo);
        return vo;
    }

    @Override
    public void deleteRole(Integer id) {
        SysRole role = getById(id);
        if (role == null) throw new BizExcp(CodeEnum.ROLE_NOT_EXIST);
        if (!removeById(id)) throw new DaoExcp();
    }

    @Override
    public List<SysRole> getRoleByUserId(Long userId) {
        return baseMapper.findRoleByUserId(userId);
    }
}
