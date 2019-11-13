package io.renren.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * 
 * @author
 * @email
 * @date 2017-07-06 13:51:19
 */
public class CahgMeetingNoticeEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	//
	private Integer meetingNoticeId;
	// 标题
	private String title;
	// 会议内容
	private String content;
	// 开始时间
	private Date startTime;
	//
	private Date endTime;
	// 状态：0 未开始，1 进行中，2 已结束
	private Integer status;
	// 排序号
	private Integer rank;
	// 置顶: 0 置顶
	private Integer stick;
	//
	private Long createUserId;
	// 添加时间
	private Date createDate;
	// 最后更改人
	private Long lastUpdateUserId;
	//
	private Date lastUpdateDate;
	//
	private Integer meetingRootId;
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




	// 发文人

	private String author;
	//
	public String getMeetingFilePath() {
		return meetingFilePath;
	}

	public void setMeetingFilePath(String meetingFilePath) {
		this.meetingFilePath = meetingFilePath;
	}

	

	//
	private Integer deptId;
	//



	public String getMeetingFileName() {
		return meetingFileName;
	}

	public void setMeetingFileName(String meetingFileName) {
		this.meetingFileName = meetingFileName;
	}



	//
	private String deptName;
	//meeting_file_path上传文件时间加后缀名字
	private String meetingFilePath;
	//meeting_file_name上传文件名称
	private String meetingFileName;
	/**
	 * 设置：
	 */
	public void setMeetingNoticeId(Integer meetingNoticeId) {
		this.meetingNoticeId = meetingNoticeId;
	}

	/**
	 * 获取：
	 */
	public Integer getMeetingNoticeId() {
		return meetingNoticeId;
	}

	/**
	 * 设置：标题
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * 获取：标题
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * 设置：会议内容
	 */
	public void setContent(String content) {
		this.content = content;
	}

	/**
	 * 获取：会议内容
	 */
	public String getContent() {
		return content;
	}

	/**
	 * 设置：开始时间
	 */
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	/**
	 * 获取：开始时间
	 */
	public Date getStartTime() {
		return startTime;
	}

	/**
	 * 设置：
	 */
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	/**
	 * 获取：
	 */
	public Date getEndTime() {
		return endTime;
	}

	/**
	 * 设置：状态：0 未开始，1 进行中，2 已结束
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}

	/**
	 * 获取：状态：0 未开始，1 进行中，2 已结束
	 */
	public Integer getStatus() {
		return status;
	}

	/**
	 * 设置：排序号
	 */
	public void setRank(Integer rank) {
		this.rank = rank;
	}

	/**
	 * 获取：排序号
	 */
	public Integer getRank() {
		return rank;
	}

	/**
	 * 设置：置顶: 0 置顶
	 */
	public void setStick(Integer stick) {
		this.stick = stick;
	}

	/**
	 * 获取：置顶: 0 置顶
	 */
	public Integer getStick() {
		return stick;
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
	 * 设置：添加时间
	 */
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	/**
	 * 获取：添加时间
	 */
	public Date getCreateDate() {
		return createDate;
	}

	/**
	 * 设置：最后更改人
	 */
	public void setLastUpdateUserId(Long lastUpdateUserId) {
		this.lastUpdateUserId = lastUpdateUserId;
	}

	/**
	 * 获取：最后更改人
	 */
	public Long getLastUpdateUserId() {
		return lastUpdateUserId;
	}

	/**
	 * 设置：
	 */
	public void setLastUpdateDate(Date lastUpdateDate) {
		this.lastUpdateDate = lastUpdateDate;
	}

	/**
	 * 获取：
	 */
	public Date getLastUpdateDate() {
		return lastUpdateDate;
	}

	/**
	 * 设置：
	 */
	public void setMeetingRootId(Integer meetingRootId) {
		this.meetingRootId = meetingRootId;
	}

	/**
	 * 获取：
	 */
	public Integer getMeetingRootId() {
		return meetingRootId;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public Integer getDeptId() {
		return deptId;
	}

	public void setDeptId(Integer deptId) {
		this.deptId = deptId;
	}

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

}
