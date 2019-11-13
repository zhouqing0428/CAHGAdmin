package io.renren.dao;

import io.renren.entity.SysUserEntity;

import java.util.List;
import java.util.Map;

/**
 * 系统用户
 * 
 * @author 
 * @email 
 * @date 2016年9月18日 上午9:34:11
 */
public interface SysUserDao extends BaseDao<SysUserEntity> {
	
	/**
	 * 查询用户的所有权限
	 * @param userId  用户ID
	 */
	List<String> queryAllPerms(Long userId);
	
	/**
	 * 查询用户的所有菜单ID
	 */
	List<Long> queryAllMenuId(Long userId);
	
	/**
	 * 根据用户名，查询系统用户
	 */
	SysUserEntity queryByUserName(String username);
	
	/**
	 * 修改密码
	 */
	int updatePassword(Map<String, Object> map);
   /**
    * 根据条件查询用户
    * @param map
    */
	List<SysUserEntity> selectList(Map<String, Object> map);
    /**
     * 删除该用户角色
     * @param userIds
     */
    void deleteUserRole(Long[] userIds);

	int queryLetterLeaderCount();
	/**
	 * 修改信箱主任状态
	 * @param isLetterLeader
	 */
	void updateLetterLeader(Map<String, Object> map);
    /**
     * 取消主任信箱
     * @param userIds
     */
	void canselLetterLeader(Long[] userIds);
}
