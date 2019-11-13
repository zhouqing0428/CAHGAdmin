package io.renren.entity;

import java.io.Serializable;
import java.util.Date;



/**
 * 
 * 
 * @author 
 * @email 
 * @date 
 */
public class SysTestEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//
	private Long testId;
	//
	private String name;
	//
	private Date startDate;

	/**
	 * 设置：
	 */
	public void setTestId(Long testId) {
		this.testId = testId;
	}
	/**
	 * 获取：
	 */
	public Long getTestId() {
		return testId;
	}
	/**
	 * 设置：
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * 获取：
	 */
	public String getName() {
		return name;
	}
	
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
}
