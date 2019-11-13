package io.renren.entity;

import java.io.Serializable;
import java.util.Date;



/**
 * 
 * 
 * @author 
 * @email 
 * @date 2017-08-07 09:07:10
 */
public class CahgWishEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//
	private Integer wishId;
	//
	private String wishCenter;
	//
	private Date wishDate;
	//0:开启1:关闭
	private Integer status;
	//添加人
	private Long createUserId;
	//添加时间
	private Date createDate;
	//最后修改人
	private Long lastUpdateUserId;
	//最后修改时间
	private Date lastUpdateDate;

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
	 * 设置：最后修改人
	 */
	public void setLastUpdateUserId(Long lastUpdateUserId) {
		this.lastUpdateUserId = lastUpdateUserId;
	}
	/**
	 * 获取：最后修改人
	 */
	public Long getLastUpdateUserId() {
		return lastUpdateUserId;
	}
	/**
	 * 设置：最后修改时间
	 */
	public void setLastUpdateDate(Date lastUpdateDate) {
		this.lastUpdateDate = lastUpdateDate;
	}
	/**
	 * 获取：最后修改时间
	 */
	public Date getLastUpdateDate() {
		return lastUpdateDate;
	}
	/**
	 * 设置：
	 */
	public void setWishId(Integer wishId) {
		this.wishId = wishId;
	}
	/**
	 * 获取：
	 */
	public Integer getWishId() {
		return wishId;
	}
	/**
	 * 设置：
	 */
	public void setWishCenter(String wishCenter) {
		this.wishCenter = wishCenter;
	}
	/**
	 * 获取：
	 */
	public String getWishCenter() {
		return wishCenter;
	}
	/**
	 * 设置：
	 */
	public void setWishDate(Date wishDate) {
		this.wishDate = wishDate;
	}
	/**
	 * 获取：
	 */
	public Date getWishDate() {
		return wishDate;
	}
	/**
	 * 设置：0:开启1:关闭
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}
	/**
	 * 获取：0:开启1:关闭
	 */
	public Integer getStatus() {
		return status;
	}
}
