package io.renren.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import io.renren.dao.CahgHyperlinkDao;
import io.renren.entity.CahgHyperlinkEntity;
import io.renren.service.CahgHyperlinkService;



@Service("cahgHyperlinkService")
public class CahgHyperlinkServiceImpl implements CahgHyperlinkService {
	@Autowired
	private CahgHyperlinkDao cahgHyperlinkDao;
	
	@Override
	public CahgHyperlinkEntity queryObject(Integer linkId){
		return cahgHyperlinkDao.queryObject(linkId);
	}
	
	@Override
	public List<CahgHyperlinkEntity> queryList(Map<String, Object> map){
		return cahgHyperlinkDao.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return cahgHyperlinkDao.queryTotal(map);
	}
	
	@Override
	public void save(CahgHyperlinkEntity cahgHyperlink){
		cahgHyperlinkDao.save(cahgHyperlink);
	}
	
	@Override
	public void update(CahgHyperlinkEntity cahgHyperlink){
		cahgHyperlinkDao.update(cahgHyperlink);
	}
	
	@Override
	public void delete(Integer linkId){
		cahgHyperlinkDao.delete(linkId);
	}
	
	@Override
	public void deleteBatch(Integer[] linkIds){
		cahgHyperlinkDao.deleteBatch(linkIds);
	}

	@Override
	public List<CahgHyperlinkEntity> headList(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return cahgHyperlinkDao.headList(map);
	}

	@Override
	public List<CahgHyperlinkEntity> rootList(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return cahgHyperlinkDao.rootList(map);
	}
	
}
