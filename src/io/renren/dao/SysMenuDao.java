package io.renren.dao;

import io.renren.entity.SysMenuEntity;

import java.util.List;

/**
 * 菜单管理
 * 
 * @author 
 * @email 
 * @date 2016年9月18日 上午9:33:01
 */
public interface SysMenuDao extends BaseDao<SysMenuEntity> {
	
	/**
	 * 根据父菜单，查询子菜单
	 * @param parentId 父菜单ID
	 */
	List<SysMenuEntity> queryListParentId(Long parentId);
	
	/**
	 * 获取不包含按钮的菜单列表
	 */
	List<SysMenuEntity> queryNotButtonList();

	void deleteRoleMenu(Long[] menuIds);
}
