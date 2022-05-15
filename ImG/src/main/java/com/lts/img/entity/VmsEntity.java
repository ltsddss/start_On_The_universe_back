package com.lts.img.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * 
 * @author lts
 * @email 419253381@qq.com
 * @date 2022-03-28 10:37:54
 */
@TableName("vms")
public class VmsEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 视频id
	 */
	@TableId
	private Integer vid;
	/**
	 * url地址
	 */
	private String httpAdress;
	/**
	 * 作者
	 */
	private String author;
	/**
	 * 更新时间
	 */
	private Date updateTime;
	/**
	 * 收藏
	 */
	private Integer connection;
	/**
	 * 点赞
	 */
	private Integer support;
	/**
	 * 状态
	 */
	private Integer status;

	public VmsEntity() {
	}

	public VmsEntity(Integer vid, String httpAdress, String author, Date updateTime, Integer connection, Integer support, Integer status) {
		this.vid = vid;
		this.httpAdress = httpAdress;
		this.author = author;
		this.updateTime = updateTime;
		this.connection = connection;
		this.support = support;
		this.status = status;
	}

	public Integer getVid() {
		return vid;
	}

	public void setVid(Integer vid) {
		this.vid = vid;
	}

	public String getHttpAdress() {
		return httpAdress;
	}

	public void setHttpAdress(String httpAdress) {
		this.httpAdress = httpAdress;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public Integer getConnection() {
		return connection;
	}

	public void setConnection(Integer connection) {
		this.connection = connection;
	}

	public Integer getSupport() {
		return support;
	}

	public void setSupport(Integer support) {
		this.support = support;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}
}
