package io.renren.entity;

import java.io.Serializable;
import java.util.Date;



/**
 * 
 * 
 * @author 
 * @email 
 * @date 2017-11-24 10:35:30
 */
public class CahgLetterAttentiveEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//
	private Integer letterAttentiveId;
	//
	private String content;

	/**
	 * 设置：
	 */
	public void setLetterAttentiveId(Integer letterAttentiveId) {
		this.letterAttentiveId = letterAttentiveId;
	}
	/**
	 * 获取：
	 */
	public Integer getLetterAttentiveId() {
		return letterAttentiveId;
	}
	/**
	 * 设置：
	 */
	public void setContent(String content) {
		this.content = content;
	}
	/**
	 * 获取：
	 */
	public String getContent() {
		return content;
	}
}
