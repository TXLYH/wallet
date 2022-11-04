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
@TableName("app")
public class AppEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 系统的id
	 */
	@TableId
	private Integer appId;
	/**
	 * 用户id
	 */
	private Integer userId;
	/**
	 * 钱包余额
	 */
	private Integer appAccount;
	/**
	 * 时间
	 */
	private String createdTime;

}
