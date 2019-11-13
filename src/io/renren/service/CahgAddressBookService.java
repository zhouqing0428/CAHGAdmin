package io.renren.service;

import io.renren.entity.CahgAddressBookEntity;

import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author 
 * @email 
 * @date 2017-07-14 17:51:32
 */
public interface CahgAddressBookService {
	
	CahgAddressBookEntity queryObject(Integer addressLookId);
	
	List<CahgAddressBookEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(CahgAddressBookEntity cahgAddressBook);
	
	void update(CahgAddressBookEntity cahgAddressBook);
	
	void delete(Integer addressLookId);
	
	void deleteBatch(Integer[] addressLookIds);
}
