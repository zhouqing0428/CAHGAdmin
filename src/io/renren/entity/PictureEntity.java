package io.renren.entity;

import java.io.Serializable;
import java.util.Date;

public class PictureEntity  implements Serializable {
	private static final long serialVersionUID = 1L;
	//img_id  上传图片id
	private Integer imgId;
	//img_state 状态0：显示 1：隐藏
	private Integer imgState;
	//img_file_name  上传图片名称
	private String imgName;
	//img_name 上传图片时间加后缀名字
	private String imgNameTemp;
	//create_date 上传图片时间  
	private Date createDate;
	public Integer getImgId() {
		return imgId;
	}

	public void setImgId(Integer imgId) {
		this.imgId = imgId;
	}

	public Integer getImgState() {
		return imgState;
	}

	public void setImgState(Integer imgState) {
		this.imgState = imgState;
	}

	public String getImgName() {
		return imgName;
	}

	public void setImgName(String imgName) {
		this.imgName = imgName;
	}

	public String getImgNameTemp() {
		return imgNameTemp;
	}

	public void setImgNameTemp(String imgNameTemp) {
		this.imgNameTemp = imgNameTemp;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Date getLastUpdateDate() {
		return lastUpdateDate;
	}

	public void setLastUpdateDate(Date lastUpdateDate) {
		this.lastUpdateDate = lastUpdateDate;
	}

	public Long getCreateUserId() {
		return createUserId;
	}

	public void setCreateUserId(Long createUserId) {
		this.createUserId = createUserId;
	}

	public Long getLastUpdateUserId() {
		return lastUpdateUserId;
	}

	public void setLastUpdateUserId(Long lastUpdateUserId) {
		this.lastUpdateUserId = lastUpdateUserId;
	}

	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}

	//last_update_date 最后修改时间 
	private Date lastUpdateDate;
	// create_user_id  上传图片人
	private Long createUserId;
	//last_update_user_id最后修改人
	private Long lastUpdateUserId;
	
	private String imgUrl;
	 
}
