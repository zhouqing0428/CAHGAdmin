package io.renren.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import io.renren.dao.CahgLetterFlowDao;
import io.renren.entity.CahgLetterFlowEntity;
import io.renren.service.CahgLetterFlowService;



@Service("cahgLetterFlowService")
public class CahgLetterFlowServiceImpl implements CahgLetterFlowService {
	@Autowired
	private CahgLetterFlowDao cahgLetterFlowDao;
	
	@Override
	public CahgLetterFlowEntity queryObject(Integer letterFlowId){
		return cahgLetterFlowDao.queryObject(letterFlowId);
	}
	
	@Override
	public List<CahgLetterFlowEntity> queryList(Map<String, Object> map){
		return cahgLetterFlowDao.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return cahgLetterFlowDao.queryTotal(map);
	}
	
	@Override
	public void save(CahgLetterFlowEntity cahgLetterFlow){
		cahgLetterFlowDao.save(cahgLetterFlow);
	}
	
	@Override
	public void update(CahgLetterFlowEntity cahgLetterFlow){
		cahgLetterFlowDao.update(cahgLetterFlow);
	}
	
	@Override
	public void delete(Integer letterFlowId){
		cahgLetterFlowDao.delete(letterFlowId);
	}
	
	@Override
	public void deleteBatch(Integer[] letterFlowIds){
		cahgLetterFlowDao.deleteBatch(letterFlowIds);
	}

	@Override
	public void hadDeal(Integer[] letterFlowIds) {
		// TODO Auto-generated method stub
		cahgLetterFlowDao.hadDeal(letterFlowIds);
	}

	/*@Override
	public int queryUnReadTotal(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return cahgLetterFlowDao.queryUnReadTotal(map);
	}*/

	@Override
	public int queryUnDealTotal(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return cahgLetterFlowDao.queryUnDealTotal(map);
	}
	
}
