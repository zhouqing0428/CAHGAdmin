package io.renren.entity;

public class JobFlow {
	private int flowId;
	private int jobId;
	private Long userId;
	private int status;
	private int firstFlow; //第一流转人 : 0 否，1 是
	
	public int getFlowId() {
		return flowId;
	}
	public void setFlowId(int flowId) {
		this.flowId = flowId;
	}
	public int getJobId() {
		return jobId;
	}
	public void setJobId(int jobId) {
		this.jobId = jobId;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public int getFirstFlow() {
		return firstFlow;
	}
	public void setFirstFlow(int firstFlow) {
		this.firstFlow = firstFlow;
	}
}
