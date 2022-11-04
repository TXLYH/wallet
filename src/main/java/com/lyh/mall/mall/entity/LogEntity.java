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
@TableName("log")
public class LogEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 日志id
	 */
	@TableId
	private Integer logId;
	/**
	 * 用户id
	 */
	private Integer userId;
	/**
	 * 支出
	 */
	private Integer pay;
	/**
	 * 日期
	 */
	private String creatTime;
	/**
	 * 收入
	 */
	private Integer money;

}
