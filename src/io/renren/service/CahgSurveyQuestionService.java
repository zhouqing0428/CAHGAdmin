package io.renren.service;

import io.renren.entity.CahgSurveyQuestionEntity;

import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author 
 * @email 
 * @date 2017-08-03 15:41:00
 */
public interface CahgSurveyQuestionService {
	
	CahgSurveyQuestionEntity queryObject(Integer surveyQuestionId);
	
	List<CahgSurveyQuestionEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(CahgSurveyQuestionEntity cahgSurveyQuestion);
	
	void update(CahgSurveyQuestionEntity cahgSurveyQuestion);
	
	void delete(Integer surveyQuestionId);
	
	void deleteBatch(Integer[] surveyQuestionIds);

	void unIndexShow();

	void indexShow(Integer surveyQuestionId);

	int queryPollSum(int questionId);
}
