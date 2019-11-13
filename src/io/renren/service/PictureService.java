package io.renren.service;

import io.renren.entity.PictureEntity;

import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author 
 * @email 
 * @date 
 */
public interface PictureService {
	//列表查询
	int queryTotalUser(Map<String, Object> map);
	
	List<PictureEntity> listUser(Map<String, Object> map);
	
	PictureEntity listUserId(Integer id);
	
	void update(PictureEntity pictureEntity);

}
