package com.xxxx.crm.dao;

import com.xxxx.crm.base.BaseMapper;
import com.xxxx.crm.vo.Permission;

import java.util.List;

public interface PermissionMapper extends BaseMapper<Permission,Integer> {

    //判断当前角色原来是否有资源
    Integer countPermission(Integer roleId);

    //将原有的资源全部删除
    Integer deletePermissionByRoleId(Integer roleId);

    // 查询当前角色拥有的权限
    List<Integer> selectPermissionByRid(Integer rId);
}