package io.renren.dao;

import io.renren.entity.SysConfigEntity;

import org.apache.ibatis.annotations.Param;

/**
 * 系统配置信息
 * 
 * @author 
 * @email 
 * @date 2016年12月4日 下午6:46:16
 */
public interface SysConfigDao extends BaseDao<SysConfigEntity> {
	
	/**
	 * 根据key，查询value
	 */
	String queryByKey(String paramKey);
	
	/**
	 * 根据key，更新value
	 */
	int updateValueByKey(@Param("key") String key, @Param("value") String value);
	
}
