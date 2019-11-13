package io.renren.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import io.renren.dao.CahgSurveyDao;
import io.renren.entity.CahgSurveyEntity;
import io.renren.service.CahgSurveyService;



@Service("cahgSurveyService")
public class CahgSurveyServiceImpl implements CahgSurveyService {
	@Autowired
	private CahgSurveyDao cahgSurveyDao;
	
	@Override
	public CahgSurveyEntity queryObject(Integer surveyId){
		return cahgSurveyDao.queryObject(surveyId);
	}
	
	@Override
	public List<CahgSurveyEntity> queryList(Map<String, Object> map){
		return cahgSurveyDao.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return cahgSurveyDao.queryTotal(map);
	}
	
	@Override
	public void save(CahgSurveyEntity cahgSurvey){
		cahgSurveyDao.save(cahgSurvey);
	}
	
	@Override
	public void update(CahgSurveyEntity cahgSurvey){
		cahgSurveyDao.update(cahgSurvey);
	}
	
	@Override
	public void delete(Integer surveyId){
		cahgSurveyDao.delete(surveyId);
	}
	
	@Override
	public void deleteBatch(Integer[] surveyIds){
		cahgSurveyDao.deleteBatch(surveyIds);
	}
	
}
