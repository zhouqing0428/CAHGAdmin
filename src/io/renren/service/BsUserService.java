package io.renren.service;

import io.renren.entity.BsUserEntity;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author 
 * @email 
 * @date 
 */
public interface BsUserService {
	
	BsUserEntity queryObject(String bsUserId);
	
	List<BsUserEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(BsUserEntity bsUser);
	
	void update(BsUserEntity bsUser);
	
	void delete(String bsUserId);
	
	void deleteBatch(String[] bsUserIds);

	//void updateState(String[] bsUserIds);

	void auditFailure(String[] bsUserIds);

	void approved(String[] bsUserIds);

	List<BsUserEntity> queryAllList(HashMap map);

	void removeAudit(String[] bsUserIds);

	List<BsUserEntity> queryMobileList(String[] bsUserIds);

	BsUserEntity queryUserById(String bs_user_id);

}
