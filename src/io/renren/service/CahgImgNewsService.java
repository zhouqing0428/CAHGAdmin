package io.renren.service;

import io.renren.entity.CahgImgNewsEntity;

import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author 
 * @email 
 * @date 2017-06-26 16:54:27
 */
public interface CahgImgNewsService {
	
	CahgImgNewsEntity queryObject(Integer imgNewId);
	
	List<CahgImgNewsEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(CahgImgNewsEntity cahgImgNews);
	
	void update(CahgImgNewsEntity cahgImgNews);
	
	void delete(Integer imgNewId);
	
	void deleteBatch(Integer[] imgNewIds);

	void stick(Integer imgNewId);

	void unStick();
}
