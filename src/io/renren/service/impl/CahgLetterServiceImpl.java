package io.renren.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import io.renren.dao.CahgLetterDao;
import io.renren.entity.CahgLetterEntity;
import io.renren.entity.CahgReplyLetterEntity;
import io.renren.service.CahgLetterService;



@Service("cahgLetterService")
public class CahgLetterServiceImpl implements CahgLetterService {
	@Autowired
	private CahgLetterDao cahgLetterDao;
	
	@Override
	public CahgLetterEntity queryObject(Integer letterId){
		return cahgLetterDao.queryObject(letterId);
	}
	
	@Override
	public List<CahgLetterEntity> queryList(Map<String, Object> map){
		return cahgLetterDao.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return cahgLetterDao.queryTotal(map);
	}
	
	@Override
	public void save(CahgLetterEntity cahgLetter){
		cahgLetterDao.save(cahgLetter);
	}
	
	@Override
	public void update(CahgLetterEntity cahgLetter){
		cahgLetterDao.update(cahgLetter);
	}
	
	@Override
	public void delete(Integer letterId){
		cahgLetterDao.delete(letterId);
	}
	
	@Override
	public void deleteBatch(Integer[] letterIds){
		cahgLetterDao.deleteBatch(letterIds);
	}

	@Override
	public void saveReplyLetter(CahgReplyLetterEntity cahgReplyLetter) {
		// TODO Auto-generated method stub
		cahgLetterDao.saveReplyLetter(cahgReplyLetter);
	}

	@Override
	public void hadRead(Integer[] letterIds) {
		// TODO Auto-generated method stub
		cahgLetterDao.hadRead(letterIds);
	}

	@Override
	public void updateReplyStatus(Map<String, Object> map) {
		// TODO Auto-generated method stub
		cahgLetterDao.updateReplyStatus(map);
	}

	@Override
	public int queryUnReadTotal(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return cahgLetterDao.queryUnReadTotal(map);
	}

	@Override
	public void hide(Integer[] letterIds) {
		// TODO Auto-generated method stub
		cahgLetterDao.hide(letterIds);
	}

	@Override
	public void show(Integer[] letterIds) {
		// TODO Auto-generated method stub
		cahgLetterDao.show(letterIds);
	}

	@Override
	public void updateIsDelete(Integer[] letterIds) {
		// TODO Auto-generated method stub
		cahgLetterDao.updateIsDelete(letterIds);
	}

	@Override
	public void deleteReplyBatch(Integer[] letterIds) {
		// TODO Auto-generated method stub
		cahgLetterDao.deleteReplyBatch(letterIds);
	}
	
}
