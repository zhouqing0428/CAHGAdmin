package io.renren.entity;

import java.io.Serializable;
import java.util.Date;



/**
 * 
 * 
 * @author 
 * @email 
 * @date 2017-08-04 22:07:40
 */
public class CahgReplyLetterEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//
	private Integer replyLetter;
	//标题
	private String title;
	//内容
	private String content;
	//
	private Date createDate;
	//
	private Long createUserId;
	//
	private Integer letterId;
	//
	private Integer isReply;
    //显示/隐藏
	private Integer hidden;

	/**
	 * 设置：
	 */
	public void setReplyLetter(Integer replyLetter) {
		this.replyLetter = replyLetter;
	}
	/**
	 * 获取：
	 */
	public Integer getReplyLetter() {
		return replyLetter;
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
	public Integer getIsReply() {
		return isReply;
	}
	public void setIsReply(Integer isReply) {
		this.isReply = isReply;
	}
	public Integer getHidden() {
		return hidden;
	}
	public void setHidden(Integer hidden) {
		this.hidden = hidden;
	}
}
