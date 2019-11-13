package io.renren.dao;

import io.renren.entity.CahgSurveyQuestionEntity;

/**
 * 
 * 
 * @author 
 * @email 
 * @date 2017-08-03 15:41:00
 */
public interface CahgSurveyQuestionDao extends BaseDao<CahgSurveyQuestionEntity> {

	void unIndexShow();

	void indexShow(Integer surveyQuestionId);

	int queryPollSum(int questionId);
	
}
