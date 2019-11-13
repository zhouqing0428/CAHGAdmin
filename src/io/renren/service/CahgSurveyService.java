package io.renren.service;

import io.renren.entity.CahgSurveyEntity;

import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author 
 * @email 
 * @date 2017-08-03 11:38:38
 */
public interface CahgSurveyService {
	
	CahgSurveyEntity queryObject(Integer surveyId);
	
	List<CahgSurveyEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(CahgSurveyEntity cahgSurvey);
	
	void update(CahgSurveyEntity cahgSurvey);
	
	void delete(Integer surveyId);
	
	void deleteBatch(Integer[] surveyIds);
}
