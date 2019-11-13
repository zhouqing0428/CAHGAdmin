package io.renren.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import io.renren.dao.CahgLeaderSpeechDao;
import io.renren.entity.CahgLeaderSpeechEntity;
import io.renren.service.CahgLeaderSpeechService;



@Service("cahgLeaderSpeechService")
public class CahgLeaderSpeechServiceImpl implements CahgLeaderSpeechService {
	@Autowired
	private CahgLeaderSpeechDao cahgLeaderSpeechDao;
	
	@Override
	public CahgLeaderSpeechEntity queryObject(Integer leaderSpeechId){
		return cahgLeaderSpeechDao.queryObject(leaderSpeechId);
	}
	
	@Override
	public List<CahgLeaderSpeechEntity> queryList(Map<String, Object> map){
		return cahgLeaderSpeechDao.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return cahgLeaderSpeechDao.queryTotal(map);
	}
	
	@Override
	public void save(CahgLeaderSpeechEntity cahgLeaderSpeech){
		cahgLeaderSpeechDao.save(cahgLeaderSpeech);
	}
	
	@Override
	public void update(CahgLeaderSpeechEntity cahgLeaderSpeech){
		cahgLeaderSpeechDao.update(cahgLeaderSpeech);
	}
	
	@Override
	public void delete(Integer leaderSpeechId){
		cahgLeaderSpeechDao.delete(leaderSpeechId);
	}
	
	@Override
	public void deleteBatch(Integer[] leaderSpeechIds){
		cahgLeaderSpeechDao.deleteBatch(leaderSpeechIds);
	}
	
}
