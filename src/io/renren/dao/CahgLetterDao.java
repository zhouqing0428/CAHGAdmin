package io.renren.dao;

import java.util.Map;

import io.renren.entity.CahgLetterEntity;
import io.renren.entity.CahgReplyLetterEntity;

/**
 * 
 * 
 * @author 
 * @email 
 * @date 2017-08-04 15:31:44
 */
public interface CahgLetterDao extends BaseDao<CahgLetterEntity> {

	void saveReplyLetter(CahgReplyLetterEntity cahgReplyLetter);

	void hadRead(Integer[] letterIds);

	void updateReplyStatus(Map<String, Object> map);

	int queryUnReadTotal(Map<String, Object> map);

	void hide(Integer[] letterIds);

	void show(Integer[] letterIds);

	void updateIsDelete(Integer[] letterIds);

	void deleteReplyBatch(Integer[] letterIds);
	
}
