package io.renren.dao;

import java.util.List;

import io.renren.entity.CahgWorkTableCategoryEntity;

/**
 * 
 * 
 * @author 
 * @email 
 * @date 2017-08-15 11:46:04
 */
public interface CahgWorkTableCategoryDao extends BaseDao<CahgWorkTableCategoryEntity> {

	List<CahgWorkTableCategoryEntity> queryAllList();
	
}
