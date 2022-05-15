package com.lts.img.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 
 * 
 * @author lts
 * @email 419253381@qq.com
 * @date 2022-03-23 15:10:39
 */
@TableName("conection")
@Data
public class ConectionEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId
	private Integer id;
	/**
	 * 
	 */
	private String useropenid;
	/**
	 * 
	 */
	private Integer imgid;

	private Integer vmid;

	public ConectionEntity() {
	}

	public ConectionEntity(Integer id, String useropenid, Integer imgid, Integer vmid) {
		this.id = id;
		this.useropenid = useropenid;
		this.imgid = imgid;
		this.vmid = vmid;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUseropenid() {
		return useropenid;
	}

	public void setUseropenid(String useropenid) {
		this.useropenid = useropenid;
	}

	public Integer getImgid() {
		return imgid;
	}

	public void setImgid(Integer imgid) {
		this.imgid = imgid;
	}

	public Integer getVmid() {
		return vmid;
	}

	public void setVmid(Integer vmid) {
		this.vmid = vmid;
	}
}
