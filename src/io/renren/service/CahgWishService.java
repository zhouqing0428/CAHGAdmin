package io.renren.service;

import io.renren.entity.CahgWishEntity;

import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author 
 * @email 
 * @date 2017-08-07 09:07:10
 */
public interface CahgWishService {
	
	CahgWishEntity queryObject(Integer wishId);
	
	List<CahgWishEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(CahgWishEntity cahgWish);
	
	void update(CahgWishEntity cahgWish);
	
	void delete(Integer wishId);
	
	void deleteBatch(Integer[] wishIds);
}
