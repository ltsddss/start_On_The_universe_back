package com.lts.img.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 
 * 
 * @author lts
 * @email 419253381@qq.com
 * @date 2022-03-23 15:10:39
 */
@TableName("ims")
public class ImsEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId
	private Integer iid;
	/**
	 * 
	 */
	private String httpAdress;
	/**
	 * 点赞
	 */
	private Integer support;
	/**
	 * 收藏
	 */
	private Integer collection;
	/**
	 * 
	 */
	private String selects;

	public ImsEntity() {
	}

	public ImsEntity(Integer iid, String httpAdress, Integer support, Integer collection, String selects) {
		this.iid = iid;
		this.httpAdress = httpAdress;
		this.support = support;
		this.collection = collection;
		this.selects = selects;
	}

	public Integer getIid() {
		return iid;
	}

	public void setIid(Integer iid) {
		this.iid = iid;
	}

	public String getHttpAdress() {
		return httpAdress;
	}

	public void setHttpAdress(String httpAdress) {
		this.httpAdress = httpAdress;
	}

	public Integer getSupport() {
		return support;
	}

	public void setSupport(Integer support) {
		this.support = support;
	}

	public Integer getCollection() {
		return collection;
	}

	public void setCollection(Integer collection) {
		this.collection = collection;
	}

	public String getSelects() {
		return selects;
	}

	public void setSelects(String selects) {
		this.selects = selects;
	}
}
