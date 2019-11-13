package io.renren.service;

import io.renren.entity.CahgLeaderSpeechEntity;

import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author 
 * @email 
 * @date 2017-06-29 14:58:49
 */
public interface CahgLeaderSpeechService {
	
	CahgLeaderSpeechEntity queryObject(Integer leaderSpeechId);
	
	List<CahgLeaderSpeechEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(CahgLeaderSpeechEntity cahgLeaderSpeech);
	
	void update(CahgLeaderSpeechEntity cahgLeaderSpeech);
	
	void delete(Integer leaderSpeechId);
	
	void deleteBatch(Integer[] leaderSpeechIds);
}
