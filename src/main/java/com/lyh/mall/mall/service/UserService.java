package com.lyh.mall.mall.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lyh.mall.mall.common.utils.PageUtils;
import com.lyh.mall.mall.entity.UserEntity;

import java.util.Map;

/**
 * 
 *
 * @author lyh
 * @email lyh.com
 * @date 2022-11-04 00:04:32
 */
public interface UserService extends IService<UserEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

