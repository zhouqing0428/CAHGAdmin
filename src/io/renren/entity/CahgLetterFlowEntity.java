package io.renren.entity;

import java.io.Serializable;
import java.util.Date;



/**
 * 
 * 
 * @author 
 * @email 
 * @date 2017-08-18 13:41:20
 */
public class CahgLetterFlowEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//
	private Integer letterFlowId;
	//处理时间
	private String disposeTime;
	//要求
	private String require;
	//状态：0 未处理，1 已处理
	private Integer status;
	//
	private Long createUserId;
	//转交时间
	private Date createTime;
	//转交人ID
	private Long userId;
	//信封标题
	private String letterTitle;
	//信封id
	private Integer letterId;
	//主任姓名
	private String userName;

	/**
	 * 设置：
	 */
	public void setLetterFlowId(Integer letterFlowId) {
		this.letterFlowId = letterFlowId;
	}
	/**
	 * 获取：
	 */
	public Integer getLetterFlowId() {
		return letterFlowId;
	}
	/**
	 * 设置：处理时间
	 */
	public void setDisposeTime(String disposeTime) {
		this.disposeTime = disposeTime;
	}
	/**
	 * 获取：处理时间
	 */
	public String getDisposeTime() {
		return disposeTime;
	}
	/**
	 * 设置：要求
	 */
	public void setRequire(String require) {
		this.require = require;
	}
	/**
	 * 获取：要求
	 */
	public String getRequire() {
		return require;
	}
	/**
	 * 设置：状态：0 未处理，1 已处理
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}
	/**
	 * 获取：状态：0 未处理，1 已处理
	 */
	public Integer getStatus() {
		return status;
	}
	/**
	 * 设置：
	 */
	public void setCreateUserId(Long createUserId) {
		this.createUserId = createUserId;
	}
	/**
	 * 获取：
	 */
	public Long getCreateUserId() {
		return createUserId;
	}
	/**
	 * 设置：转交时间
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	/**
	 * 获取：转交时间
	 */
	public Date getCreateTime() {
		return createTime;
	}
	/**
	 * 设置：转交人ID
	 */
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	/**
	 * 获取：转交人ID
	 */
	public Long getUserId() {
		return userId;
	}
	/**
	 * 设置：信封标题
	 */
	public void setLetterTitle(String letterTitle) {
		this.letterTitle = letterTitle;
	}
	/**
	 * 获取：信封标题
	 */
	public String getLetterTitle() {
		return letterTitle;
	}
	/**
	 * 设置：信封id
	 */
	public void setLetterId(Integer letterId) {
		this.letterId = letterId;
	}
	/**
	 * 获取：信封id
	 */
	public Integer getLetterId() {
		return letterId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
}
