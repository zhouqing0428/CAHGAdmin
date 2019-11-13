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
public class SysSmsEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//
	private Long smsId;
	//
	private String message;
	//
	private String note;

	/**
	 * 设置：
	 */
	public void setSmsId(Long smsId) {
		this.smsId = smsId;
	}
	/**
	 * 获取：
	 */
	public Long getSmsId() {
		return smsId;
	}
	/**
	 * 设置：
	 */
	public void setMessage(String message) {
		this.message = message;
	}
	/**
	 * 获取：
	 */
	public String getMessage() {
		return message;
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
}
