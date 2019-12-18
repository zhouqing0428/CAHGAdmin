package io.renren.entity;

import java.io.Serializable;

/**
 * 
 * 
 * @author 
 * @email 
 * @date 
 */
public class CahgStyleCategoryEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//主键ID
	private Integer id;
	//分类名称
	private String cateName;
	public Integer getId() {
		return id;
	}
	//排序
	private Integer orderNum;
		
	public void setId(Integer id) {
		this.id = id;
	}
	public String getCateName() {
		return cateName;
	}
	public void setCateName(String cateName) {
		this.cateName = cateName;
	}
	public Integer getOrderNum() {
		return orderNum;
	}
	public void setOrderNum(Integer orderNum) {
		this.orderNum = orderNum;
	}
}
