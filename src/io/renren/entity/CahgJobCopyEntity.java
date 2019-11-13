package io.renren.entity;

import java.io.Serializable;
import java.util.Date;



/**
 * 
 * 
 * @author 
 * @email 
 * @date 2018-03-21 15:34:27
 */
public class CahgJobCopyEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//
	private Integer jobCopyId;
	//备注
	private String remark;
	//状态：
	private Integer status;
	//
	private Long lastUpdateUserId;
	//
	private Date lastUpdateUserDate;
	public Long getLastUpdateUserId() {
		return lastUpdateUserId;
	}
	public void setLastUpdateUserId(Long lastUpdateUserId) {
		this.lastUpdateUserId = lastUpdateUserId;
	}
	public Date getLastUpdateUserDate() {
		return lastUpdateUserDate;
	}
	public void setLastUpdateUserDate(Date lastUpdateUserDate) {
		this.lastUpdateUserDate = lastUpdateUserDate;
	}
	//添加人
	private Long createUserId;

	private String createUserName;
    private String lastUpdateName;

	public String getCreateUserName() {
			return createUserName;
		}
		public void setCreateUserName(String createUserName) {
			this.createUserName = createUserName;
		}
		public String getLastUpdateName() {
			return lastUpdateName;
		}
		public void setLastUpdateName(String lastUpdateName) {
			this.lastUpdateName = lastUpdateName;
		}
	//
	private Long toUserId;
	//
	private Integer jobId;
	//
	private Date createTime;
	//
	private String userName;
	//
	private String title;

	/**
	 * 设置：
	 */
	public void setJobCopyId(Integer jobCopyId) {
		this.jobCopyId = jobCopyId;
	}
	/**
	 * 获取：
	 */
	public Integer getJobCopyId() {
		return jobCopyId;
	}
	/**
	 * 设置：备注
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}
	/**
	 * 获取：备注
	 */
	public String getRemark() {
		return remark;
	}
	/**
	 * 设置：状态：
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}
	/**
	 * 获取：状态：
	 */
	public Integer getStatus() {
		return status;
	}
	/**
	 * 设置：添加人
	 */
	public void setCreateUserId(Long createUserId) {
		this.createUserId = createUserId;
	}
	/**
	 * 获取：添加人
	 */
	public Long getCreateUserId() {
		return createUserId;
	}
	
	/**
	 * 设置：
	 */
	public void setToUserId(Long toUserId) {
		this.toUserId = toUserId;
	}
	/**
	 * 获取：
	 */
	public Long getToUserId() {
		return toUserId;
	}
	/**
	 * 设置：
	 */
	public void setJobId(Integer jobId) {
		this.jobId = jobId;
	}
	/**
	 * 获取：
	 */
	public Integer getJobId() {
		return jobId;
	}
	/**
	 * 设置：
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	/**
	 * 获取：
	 */
	public Date getCreateTime() {
		return createTime;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
}
