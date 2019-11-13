package io.renren.entity;

import java.io.Serializable;
import java.util.Date;



/**
 * 
 * 
 * @author 
 * @email 
 * @date 2017-08-04 15:31:44
 */
public class CahgLetterEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//
	private Integer letterId;
	//标题
	private String title;
	//内容
	private String content;
	//写信人
	private String author;
	//状态：0 未读，1 已读
	private Integer status;
	//
	private Date createDate;
	//
	private Long createUserId;
	//给用户id
	private Long toUserId;
	//
	private Integer isReply;
	//
	private String deptName;
	//
	private String dutyName;
	//显示/隐藏
	private Integer hidden;
	/**
	 * 设置：
	 */
	public void setLetterId(Integer letterId) {
		this.letterId = letterId;
	}
	/**
	 * 获取：
	 */
	public Integer getLetterId() {
		return letterId;
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
	 * 设置：内容
	 */
	public void setContent(String content) {
		this.content = content;
	}
	/**
	 * 获取：内容
	 */
	public String getContent() {
		return content;
	}
	/**
	 * 设置：写信人
	 */
	public void setAuthor(String author) {
		this.author = author;
	}
	/**
	 * 获取：写信人
	 */
	public String getAuthor() {
		return author;
	}
	/**
	 * 设置：状态：0 未读，1 已读
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}
	/**
	 * 获取：状态：0 未读，1 已读
	 */
	public Integer getStatus() {
		return status;
	}
	/**
	 * 设置：
	 */
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	/**
	 * 获取：
	 */
	public Date getCreateDate() {
		return createDate;
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
	 * 设置：给用户id
	 */
	public void setToUserId(Long toUserId) {
		this.toUserId = toUserId;
	}
	/**
	 * 获取：给用户id
	 */
	public Long getToUserId() {
		return toUserId;
	}
	public Integer getIsReply() {
		return isReply;
	}
	public void setIsReply(Integer isReply) {
		this.isReply = isReply;
	}
	public String getDeptName() {
		return deptName;
	}
	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}
	public String getDutyName() {
		return dutyName;
	}
	public void setDutyName(String dutyName) {
		this.dutyName = dutyName;
	}

	public Integer getHidden() {
		return hidden;
	}
	public void setHidden(Integer hidden) {
		this.hidden = hidden;
	}
}
