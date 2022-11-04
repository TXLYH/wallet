package com.lyh.mall.mall.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import lombok.Data;

/**
 * 
 * 
 * @author lyh
 * @email lyh.com
 * @date 2022-11-04 00:04:32
 */
@Data
@TableName("user")
public class UserEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId
	private Integer userId;
	/**
	 * 
	 */
	private String userName;
	/**
	 * 
	 */
	private Integer card;
	/**
	 * 
	 */
	private String createdTime;

}
