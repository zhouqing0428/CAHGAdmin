package io.renren.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import io.renren.dao.CahgSurveyAnswerDao;
import io.renren.entity.CahgSurveyAnswerEntity;
import io.renren.service.CahgSurveyAnswerService;



@Service("cahgSurveyAnswerService")
public class CahgSurveyAnswerServiceImpl implements CahgSurveyAnswerService {
	@Autowired
	private CahgSurveyAnswerDao cahgSurveyAnswerDao;
	
	@Override
	public CahgSurveyAnswerEntity queryObject(Integer surveyAnswerId){
		return cahgSurveyAnswerDao.queryObject(surveyAnswerId);
	}
	
	@Override
	public List<CahgSurveyAnswerEntity> queryList(Map<String, Object> map){
		return cahgSurveyAnswerDao.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return cahgSurveyAnswerDao.queryTotal(map);
	}
	
	@Override
	public void save(CahgSurveyAnswerEntity cahgSurveyAnswer){
		cahgSurveyAnswerDao.save(cahgSurveyAnswer);
	}
	
	@Override
	public void update(CahgSurveyAnswerEntity cahgSurveyAnswer){
		cahgSurveyAnswerDao.update(cahgSurveyAnswer);
	}
	
	@Override
	public void delete(Integer surveyAnswerId){
		cahgSurveyAnswerDao.delete(surveyAnswerId);
	}
	
	@Override
	public void deleteBatch(Integer[] surveyAnswerIds){
		cahgSurveyAnswerDao.deleteBatch(surveyAnswerIds);
	}
	
}
