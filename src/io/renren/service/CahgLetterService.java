package io.renren.service;

import io.renren.entity.CahgLetterEntity;
import io.renren.entity.CahgReplyLetterEntity;

import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author 
 * @email 
 * @date 2017-08-04 15:31:44
 */
public interface CahgLetterService {
	
	CahgLetterEntity queryObject(Integer letterId);
	
	List<CahgLetterEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(CahgLetterEntity cahgLetter);
	
	void update(CahgLetterEntity cahgLetter);
	
	void delete(Integer letterId);
	
	void deleteBatch(Integer[] letterIds);

	void saveReplyLetter(CahgReplyLetterEntity cahgReplyLetter);

	void hadRead(Integer[] letterIds);

	void updateReplyStatus(Map<String, Object> map);

	int queryUnReadTotal(Map<String, Object> map);

	void hide(Integer[] letterIds);

	void show(Integer[] letterIds);

	void updateIsDelete(Integer[] letterIds);

	void deleteReplyBatch(Integer[] letterIds);
}
