package com.lts.start.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

import lombok.Data;

/**
 * 
 * 
 * @author lts
 * @email 419253381@qq.com
 * @date 2022-03-22 18:03:00
 */
@TableName("start_ums")
public class UmsEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 用户的唯一标识
	 */
	@TableId
	private String openid;
	/**
	 * 用户的昵称
	 */
	private String nickname;
	/**
	 * 用户注册时间
	 */
	private Date createtime;

	public String getOpenid() {
		return openid;
	}

	public void setOpenid(String openid) {
		this.openid = openid;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public Date getCreatetime() {
		return createtime;
	}

	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}
}
