package io.renren.service;

import io.renren.entity.CahgAfficheEntity;

import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author 
 * @email 
 * @date 2017-07-06 17:50:57
 */
public interface CahgAfficheService {
	
	CahgAfficheEntity queryObject(Integer afficheId);
	
	List<CahgAfficheEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(CahgAfficheEntity cahgAffiche);
	
	void update(CahgAfficheEntity cahgAffiche);
	
	void delete(Integer afficheId);
	
	void deleteBatch(Integer[] afficheIds);
	
	void updateFileNull(Integer[] afficheIds);
}
