package io.renren.entity;

import java.io.Serializable;
import java.util.Date;



/**
 * 
 * 
 * @author 
 * @email 
 * @date 2017-07-26 14:48:27
 */
public class CahgDutyInfoEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//
	private Integer dutyInfoId;
	//标题
	private String title;
	//内容
	private String content;
	//备注
	private String remark;
	//上传文件时间加后缀名字
	private String dutyFilePath;
	//上传文件名称
	private String dutyFileName;

	public String getDutyFilePath() {
		return dutyFilePath;
	}
	public void setDutyFilePath(String dutyFilePath) {
		this.dutyFilePath = dutyFilePath;
	}
	public String getDutyFileName() {
		return dutyFileName;
	}
	public void setDutyFileName(String dutyFileName) {
		this.dutyFileName = dutyFileName;
	}
	/**
	 * 设置：
	 */
	public void setDutyInfoId(Integer dutyInfoId) {
		this.dutyInfoId = dutyInfoId;
	}
	/**
	 * 获取：
	 */
	public Integer getDutyInfoId() {
		return dutyInfoId;
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
}
