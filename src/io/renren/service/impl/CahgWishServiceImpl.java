package io.renren.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import io.renren.dao.CahgWishDao;
import io.renren.entity.CahgWishEntity;
import io.renren.service.CahgWishService;



@Service("cahgWishService")
public class CahgWishServiceImpl implements CahgWishService {
	@Autowired
	private CahgWishDao cahgWishDao;
	
	@Override
	public CahgWishEntity queryObject(Integer wishId){
		return cahgWishDao.queryObject(wishId);
	}
	
	@Override
	public List<CahgWishEntity> queryList(Map<String, Object> map){
		return cahgWishDao.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return cahgWishDao.queryTotal(map);
	}
	
	@Override
	public void save(CahgWishEntity cahgWish){
		cahgWishDao.save(cahgWish);
	}
	
	@Override
	public void update(CahgWishEntity cahgWish){
		cahgWishDao.update(cahgWish);
	}
	
	@Override
	public void delete(Integer wishId){
		cahgWishDao.delete(wishId);
	}
	
	@Override
	public void deleteBatch(Integer[] wishIds){
		cahgWishDao.deleteBatch(wishIds);
	}
	
}
