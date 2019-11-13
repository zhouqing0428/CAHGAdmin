package io.renren.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import io.renren.dao.CahgSurveyQuestionDao;
import io.renren.entity.CahgSurveyQuestionEntity;
import io.renren.service.CahgSurveyQuestionService;



@Service("cahgSurveyQuestionService")
public class CahgSurveyQuestionServiceImpl implements CahgSurveyQuestionService {
	@Autowired
	private CahgSurveyQuestionDao cahgSurveyQuestionDao;
	
	@Override
	public CahgSurveyQuestionEntity queryObject(Integer surveyQuestionId){
		return cahgSurveyQuestionDao.queryObject(surveyQuestionId);
	}
	
	@Override
	public List<CahgSurveyQuestionEntity> queryList(Map<String, Object> map){
		return cahgSurveyQuestionDao.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return cahgSurveyQuestionDao.queryTotal(map);
	}
	
	@Override
	public void save(CahgSurveyQuestionEntity cahgSurveyQuestion){
		cahgSurveyQuestionDao.save(cahgSurveyQuestion);
	}
	
	@Override
	public void update(CahgSurveyQuestionEntity cahgSurveyQuestion){
		cahgSurveyQuestionDao.update(cahgSurveyQuestion);
	}
	
	@Override
	public void delete(Integer surveyQuestionId){
		cahgSurveyQuestionDao.delete(surveyQuestionId);
	}
	
	@Override
	public void deleteBatch(Integer[] surveyQuestionIds){
		cahgSurveyQuestionDao.deleteBatch(surveyQuestionIds);
	}

	@Override
	public void unIndexShow() {
		// TODO Auto-generated method stub
		cahgSurveyQuestionDao.unIndexShow();
	}

	@Override
	public void indexShow(Integer surveyQuestionId) {
		// TODO Auto-generated method stub
		cahgSurveyQuestionDao.indexShow(surveyQuestionId);
	}

	@Override
	public int queryPollSum(int questionId) {
		// TODO Auto-generated method stub
		return cahgSurveyQuestionDao.queryPollSum(questionId);
	}
	
}
