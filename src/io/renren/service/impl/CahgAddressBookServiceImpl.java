package io.renren.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import io.renren.dao.CahgAddressBookDao;
import io.renren.entity.CahgAddressBookEntity;
import io.renren.service.CahgAddressBookService;



@Service("cahgAddressBookService")
public class CahgAddressBookServiceImpl implements CahgAddressBookService {
	@Autowired
	private CahgAddressBookDao cahgAddressBookDao;
	
	@Override
	public CahgAddressBookEntity queryObject(Integer addressLookId){
		return cahgAddressBookDao.queryObject(addressLookId);
	}
	
	@Override
	public List<CahgAddressBookEntity> queryList(Map<String, Object> map){
		return cahgAddressBookDao.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return cahgAddressBookDao.queryTotal(map);
	}
	
	@Override
	public void save(CahgAddressBookEntity cahgAddressBook){
		cahgAddressBookDao.save(cahgAddressBook);
	}
	
	@Override
	public void update(CahgAddressBookEntity cahgAddressBook){
		cahgAddressBookDao.update(cahgAddressBook);
	}
	
	@Override
	public void delete(Integer addressLookId){
		cahgAddressBookDao.delete(addressLookId);
	}
	
	@Override
	public void deleteBatch(Integer[] addressLookIds){
		cahgAddressBookDao.deleteBatch(addressLookIds);
	}

	@Override
	public void batchSave(List<CahgAddressBookEntity> list) {
		cahgAddressBookDao.saveBatch(list);
	}
	
}
