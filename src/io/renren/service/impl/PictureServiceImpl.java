package io.renren.service.impl;

import java.util.List;
import java.util.Map;

import io.renren.dao.PictureDao;
import io.renren.entity.PictureEntity;
import io.renren.service.PictureService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("PictureService")
public class PictureServiceImpl implements PictureService {
	@Autowired
	private PictureDao pcictureDao;

	@Override
	public int queryTotalUser(Map<String, Object> map) {
		return pcictureDao.queryTotalUser(map);
	}

	@Override
	public List<PictureEntity> listUser(Map<String, Object> map) {
		return pcictureDao.listUser(map);
	}

	@Override
	public PictureEntity listUserId(Integer id) {
		return pcictureDao.listUserId(id);
	}

	@Override
	public void update(PictureEntity pictureEntity) {
		pcictureDao.updatePictureEntity(pictureEntity);
		
	}

	

}
