package io.renren.service;

import io.renren.entity.CahgSurveyAnswerEntity;

import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author 
 * @email 
 * @date 2017-08-03 18:09:15
 */
public interface CahgSurveyAnswerService {
	
	CahgSurveyAnswerEntity queryObject(Integer surveyAnswerId);
	
	List<CahgSurveyAnswerEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(CahgSurveyAnswerEntity cahgSurveyAnswer);
	
	void update(CahgSurveyAnswerEntity cahgSurveyAnswer);
	
	void delete(Integer surveyAnswerId);
	
	void deleteBatch(Integer[] surveyAnswerIds);
}
