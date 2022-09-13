package com.xxxx.crm.service;

import com.xxxx.crm.base.BaseService;
import com.xxxx.crm.dao.ModuleMapper;
import com.xxxx.crm.dao.PermissionMapper;
import com.xxxx.crm.dao.RoleMapper;
import com.xxxx.crm.model.TreeModel;
import com.xxxx.crm.utils.AssertUtil;
import com.xxxx.crm.vo.Module;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ModuleService extends BaseService<Module,Integer> {

    @Resource
    private ModuleMapper moduleMapper;

    @Resource
    private RoleMapper roleMapper;

    @Resource
    private PermissionMapper permissionMapper;

    //查询所有资源
    public List<TreeModel> queryAllModules(Integer rId){
        //角色非空且存在
        AssertUtil.isTrue(rId == null || roleMapper.selectByPrimaryKey(rId) == null,"角色不存在");
        //查询当前角色拥有的权限
        List<Integer> mIds = permissionMapper.selectPermissionByRid(rId);
        //查询所有的模块
        List<TreeModel> treeModels = moduleMapper.queryAllModules();
        //遍历需要返回到前台的所有资源
        for(TreeModel treeModel:treeModels){
            //获取当前遍历对象的模块id
            Integer id = treeModel.getId();
            //判断当前角色拥有的权限中是否包含了 遍历对象的模块id
            if(mIds.contains(id)){  //当前方法判断某个数据是否存在于这个集合中
                treeModel.setChecked(true);
                treeModel.setOpen(true);
            }
        }
        return treeModels;
    }
}
