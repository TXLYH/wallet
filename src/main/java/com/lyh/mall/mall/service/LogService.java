package com.lyh.mall.mall.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lyh.mall.mall.common.utils.PageUtils;
import com.lyh.mall.mall.entity.LogEntity;

import java.util.Map;

/**
 * 
 *
 * @author lyh
 * @email lyh.com
 * @date 2022-11-04 00:04:32
 */
public interface LogService extends IService<LogEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

