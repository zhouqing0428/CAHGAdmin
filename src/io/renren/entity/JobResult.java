package io.renren.entity;

import java.util.Date;

/**
 * 工作督办完成情况表
 * @author qing_zhou
 * @date 2019-11-23
 */
public class JobResult {
	private int resultId; //完成情况ID
	private int jobId; //工作督办ID
	private String userName; // 经办人
	private int deptId; // 经办科室
	private String content; // 办理情况
	private Date createTime; // 办理时间
	private String deptName;// 经办科室名称
	
	public int getResultId() {
		return resultId;
	}
	public void setResultId(int resultId) {
		this.resultId = resultId;
	}
	public int getJobId() {
		return jobId;
	}
	public void setJobId(int jobId) {
		this.jobId = jobId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public int getDeptId() {
		return deptId;
	}
	public void setDeptId(int deptId) {
		this.deptId = deptId;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public String getDeptName() {
		return deptName;
	}
	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}
}
