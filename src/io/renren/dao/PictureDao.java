package io.renren.dao;

import io.renren.entity.PictureEntity;

import java.util.List;
import java.util.Map;

public interface PictureDao extends BaseDao<PictureEntity> {
	//列表查询
	int queryTotalUser(Map<String, Object> map);
	
	List<PictureEntity> listUser(Map<String, Object> map);
	
	PictureEntity listUserId(Integer id);
	
	void updatePictureEntity(PictureEntity pictureEntity);

}
