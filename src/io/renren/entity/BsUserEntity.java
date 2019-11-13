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
public class BsUserEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//
	private String bsUserId;
	//
	private String userName;
	//
	private String password;
	//
	private String name;
	//
	private String mobile;
	//
	private String note;
	//
	private Date applDate;
	//
	private String bsUserState;
	//
	private String sex;

	/**
	 * 设置：
	 */
	public void setBsUserId(String bsUserId) {
		this.bsUserId = bsUserId;
	}
	/**
	 * 获取：
	 */
	public String getBsUserId() {
		return bsUserId;
	}
	/**
	 * 设置：
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}
	/**
	 * 获取：
	 */
	public String getUserName() {
		return userName;
	}
	/**
	 * 设置：
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	/**
	 * 获取：
	 */
	public String getPassword() {
		return password;
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
	/**
	 * 设置：
	 */
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	/**
	 * 获取：
	 */
	public String getMobile() {
		return mobile;
	}
	/**
	 * 设置：
	 */
	public void setNote(String note) {
		this.note = note;
	}
	/**
	 * 获取：
	 */
	public String getNote() {
		return note;
	}
	/**
	 * 设置：
	 */
	public void setApplDate(Date applDate) {
		this.applDate = applDate;
	}
	/**
	 * 获取：
	 */
	public Date getApplDate() {
		return applDate;
	}
	/**
	 * 设置：
	 */
	public void setBsUserState(String bsUserState) {
		this.bsUserState = bsUserState;
	}
	/**
	 * 获取：
	 */
	public String getBsUserState() {
		return bsUserState;
	}
	/**
	 * 设置：
	 */
	public void setSex(String sex) {
		this.sex = sex;
	}
	/**
	 * 获取：
	 */
	public String getSex() {
		return sex;
	}
}
