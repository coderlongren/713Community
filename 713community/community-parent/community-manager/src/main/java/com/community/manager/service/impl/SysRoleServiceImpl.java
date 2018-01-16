package com.community.manager.service.impl;

import com.community.manager.dao.SysRoleDao;
import com.community.manager.dao.SysRoleMenuDao;
import com.community.manager.entity.SysRole;
import com.community.manager.entity.SysRoleMenu;
import com.community.manager.service.SysRoleService;
import com.community.manager.vo.PageResultVo;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @Author: xian
 * @Description:
 * @Date:create in 2017/10/25 15:37
 */
@Service
public class SysRoleServiceImpl implements SysRoleService {

    @Autowired
    private SysRoleDao sysRoleDao;

    @Autowired
    private SysRoleMenuDao sysRoleMenuDao;

    /**
     * 新增角色数据
     *
     * @param role
     * @param ids
     * @return
     */
    @Override
    public Boolean insertRole(SysRole role, String ids) {
        //先插入角色
        role.setCreateTime(new Date());
        role.setUpdateTime(new Date());
        role.setStatus(1);
        int result = sysRoleDao.insert(role);

        //再往角色-菜单映射表插入数据
        //解析从前台页面传来的id串
        String[] idSeq = ids.split(",");
        // TODO 使用mybatis的批量插入
        SysRoleMenu sysRoleMenu = new SysRoleMenu();
        for (int i = 0; i < idSeq.length; i++) {
            sysRoleMenu.setRoleId(role.getRoleId());
            sysRoleMenu.setMenuId(Long.valueOf(idSeq[i]));
            sysRoleMenuDao.insert(sysRoleMenu);
        }
        return result == 1;
    }

    /**
     * 分页查询角色列表数据
     *
     * @param page : 下一页
     * @param rows : 每页显示的记录数
     * @return
     */
    @Override
    public PageResultVo listRoleByPage(Integer page, Integer rows) {
        //为了提升系统的健壮性，先判断page是否为空，为空则设一个默认值1
        if (page == null || page <= 0) {
            page = 1;
        }
        PageHelper.startPage(page, rows);

        List<SysRole> list = sysRoleDao.listAll();
        PageInfo<SysRole> pageInfo = new PageInfo<>(list);
        return new PageResultVo(pageInfo.getTotal(), pageInfo.getList(), pageInfo.getPages());
    }

    /**
     * 根据id更新角色的状态
     *
     * @param roleId
     * @param status
     * @return
     */
    @Override
    public Integer updateRoleById(Long roleId, Integer status) {
        SysRole role = sysRoleDao.getById(roleId);
        if (null == role) {
            return 0;
        }

        role.setStatus(status);
        role.setUpdateTime(new Date());

        return sysRoleDao.update(role);
    }

    /**
     * 根据id删除角色
     *
     * @param roleId
     * @return
     */
    @Override
    public Boolean removeRoleById(Long roleId) {
        //先删除角色-菜单映射表中的数据
        int r1 = sysRoleMenuDao.deleteByRoleId(roleId);

        //再删除角色表中的数据
        int r2 = sysRoleDao.deleteById(roleId);
        return r2 == 1;
    }

    /**
     * 根据角色id查询角色-附带该角色所拥有的菜单权限
     *
     * @param roleId
     * @return
     */
    @Override
    public SysRole getRoleWithMenuByRoleId(Long roleId) {
        return sysRoleDao.getRoleWithMenus(roleId);
    }

    /**
     * 查询所有菜单角色列表
     *
     * @return
     */
    @Override
    public List<SysRole> listAllRole() {
        return sysRoleDao.listAll();
    }

    /**
     * @Author: xian
     * @Description:
     * @Date:create in 2017/10/25 15:36
     */
    @Service
    public static class SysRoleMenuServiceImpl {
    }
}
