package io.renren.entity;

import java.io.Serializable;
import java.util.Date;



/**
 * 
 * 
 * @author 
 * @email 
 * @date 2017-07-11 21:19:36
 */
public class CahgJobEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//
	private Integer jobId;
	//工作标题
	private String title;
	//工作内容
	private String content;
	//状态(对工作): 0 待办,1在办，2已办
	private Integer status;
	//紧急程度： 0 低 1 中 2 高
	private Integer urgentStatus;
	//发起人
	private Long createUserId;
	//发起时间
	private Date createUserDate;
	//最后处理人
	private Long lastUpdateUserId;
	//最后处理时间
	private Date lastUpdateUserDate;
	//办结时间
	private Date finishTime;
	
	//附件名称
	private String fileName;
	//附件原名
	private String fileOldName;
	//计划完成时间
	private Date endTime;
	//
	private Integer deptId;
	//流转人id
	private Long flowUserId;
	//对于用户的工作状态
	private String userJobStatus;
	//发起人
	private String createUser;
	//最后处理人
	private String lastUpdateName;
	
	private String createUserName;
	
	public String getCreateUserName() {
		return createUserName;
	}
	public void setCreateUserName(String createUserName) {
		this.createUserName = createUserName;
	}
	//job_file_path上传文件时间加后缀名字
	private String jobFilePath;
	public String getJobFilePath() {
		return jobFilePath;
	}
	public void setJobFilePath(String jobFilePath) {
		this.jobFilePath = jobFilePath;
	}
	public String getJobFileName() {
		return jobFileName;
	}
	public void setJobFileName(String jobFileName) {
		this.jobFileName = jobFileName;
	}
	//job_file_name上传文件名称
	private String jobFileName;
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
	 * 设置：工作标题
	 */
	public void setTitle(String title) {
		this.title = title;
	}
	/**
	 * 获取：工作标题
	 */
	public String getTitle() {
		return title;
	}
	/**
	 * 设置：工作内容
	 */
	public void setContent(String content) {
		this.content = content;
	}
	/**
	 * 获取：工作内容
	 */
	public String getContent() {
		return content;
	}
	/**
	 * 设置：状态: 0 待办,1在办，2已办
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}
	/**
	 * 获取：状态: 0 待办,1在办，2已办
	 */
	public Integer getStatus() {
		return status;
	}
	/**
	 * 设置：紧急程度： 0 低 1 中 2 高
	 */
	public void setUrgentStatus(Integer urgentStatus) {
		this.urgentStatus = urgentStatus;
	}
	/**
	 * 获取：紧急程度： 0 低 1 中 2 高
	 */
	public Integer getUrgentStatus() {
		return urgentStatus;
	}
	/**
	 * 设置：发起人
	 */
	public void setCreateUserId(Long createUserId) {
		this.createUserId = createUserId;
	}
	/**
	 * 获取：发起人
	 */
	public Long getCreateUserId() {
		return createUserId;
	}
	/**
	 * 设置：发起时间
	 */
	public void setCreateUserDate(Date createUserDate) {
		this.createUserDate = createUserDate;
	}
	/**
	 * 获取：发起时间
	 */
	public Date getCreateUserDate() {
		return createUserDate;
	}
	/**
	 * 设置：最后处理人
	 */
	public void setLastUpdateUserId(Long lastUpdateUserId) {
		this.lastUpdateUserId = lastUpdateUserId;
	}
	/**
	 * 获取：最后处理人
	 */
	public Long getLastUpdateUserId() {
		return lastUpdateUserId;
	}
	/**
	 * 设置：最后处理时间
	 */
	public void setLastUpdateUserDate(Date lastUpdateUserDate) {
		this.lastUpdateUserDate = lastUpdateUserDate;
	}
	/**
	 * 获取：最后处理时间
	 */
	public Date getLastUpdateUserDate() {
		return lastUpdateUserDate;
	}
	/**
	 * 设置：附件名称
	 */
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	/**
	 * 获取：附件名称
	 */
	public String getFileName() {
		return fileName;
	}
	/**
	 * 设置：附件原名
	 */
	public void setFileOldName(String fileOldName) {
		this.fileOldName = fileOldName;
	}
	/**
	 * 获取：附件原名
	 */
	public String getFileOldName() {
		return fileOldName;
	}
	/**
	 * 设置：计划完成时间
	 */
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	/**
	 * 获取：计划完成时间
	 */
	public Date getEndTime() {
		return endTime;
	}
	 
	public Integer getDeptId() {
		return deptId;
	}
	public void setDeptId(Integer deptId) {
		this.deptId = deptId;
	}
	public Long getFlowUserId() {
		return flowUserId;
	}
	public void setFlowUserId(Long flowUserId) {
		this.flowUserId = flowUserId;
	}
	public String getUserJobStatus() {
		return userJobStatus;
	}
	public void setUserJobStatus(String userJobStatus) {
		this.userJobStatus = userJobStatus;
	}
	public String getCreateUser() {
		return createUser;
	}
	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}
	public String getLastUpdateName() {
		return lastUpdateName;
	}
	public void setLastUpdateName(String lastUpdateName) {
		this.lastUpdateName = lastUpdateName;
	}
	public Date getFinishTime() {
		return finishTime;
	}
	public void setFinishTime(Date finishTime) {
		this.finishTime = finishTime;
	}
}
