package io.renren.dao;

import java.util.HashMap;
import java.util.List;

import io.renren.entity.BsUserEntity;

/**
 * 
 * 
 * @author 
 * @email 
 * @date 2017-03-24 14:04:39
 */
public interface BsUserDao extends BaseDao<BsUserEntity> {

	void approved(String[] bsUserIds);

	void auditFailure(String[] bsUserIds);

	List<BsUserEntity> queryAllList(HashMap map);

	void removeAudit(String[] bsUserIds);

	List<BsUserEntity> queryMobileList(String[] bsUserIds);

	BsUserEntity queryUserById(String bs_user_id);

	
	
}
