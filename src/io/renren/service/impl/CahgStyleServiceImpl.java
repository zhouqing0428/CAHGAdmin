package io.renren.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import io.renren.dao.CahgStyleDao;
import io.renren.entity.CahgStyleEntity;
import io.renren.service.CahgStyleService;
import io.renren.utils.ShiroUtils;



@Service("cahgStyleService")
public class CahgStyleServiceImpl implements CahgStyleService {
	@Autowired
	private CahgStyleDao cahgStyleDao;
	
	@Override
	public CahgStyleEntity queryObject(Integer styleId){
		return cahgStyleDao.queryObject(styleId);
	}
	
	@Override
	public List<CahgStyleEntity> queryList(Map<String, Object> map){
		return cahgStyleDao.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return cahgStyleDao.queryTotal(map);
	}
	
	@Override
	public void save(CahgStyleEntity cahgStyle){
		cahgStyle.setCreateUserId(ShiroUtils.getUserEntity().getUserId());
		cahgStyleDao.save(cahgStyle);
	}
	
	@Override
	public void update(CahgStyleEntity cahgStyle){
		cahgStyleDao.update(cahgStyle);
	}
	
	@Override
	public void delete(Integer styleId){
		cahgStyleDao.delete(styleId);
	}
	
	@Override
	public void deleteBatch(Integer[] styleIds){
		cahgStyleDao.deleteBatch(styleIds);
	}

	@Override
	public void stick(Integer styleId) {
		// TODO Auto-generated method stub
		cahgStyleDao.stick(styleId);
	}

	@Override
	public void unStick() {
		// TODO Auto-generated method stub
		cahgStyleDao.unStick();
	}

	@Override
	public void updateStatusShow(Integer[] styleIds) {
		// TODO Auto-generated method stub
		cahgStyleDao.updateStatusShow(styleIds);
	}
	
	@Override
	public void updateStatusUnShow(Integer[] styleIds) {
		// TODO Auto-generated method stub
		cahgStyleDao.updateStatusUnShow(styleIds);
	}

	
}
