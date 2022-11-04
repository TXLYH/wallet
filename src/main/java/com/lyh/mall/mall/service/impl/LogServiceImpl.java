package com.lyh.mall.mall.service.impl;

import com.lyh.mall.mall.common.utils.PageUtils;
import com.lyh.mall.mall.common.utils.Query;
import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.lyh.mall.mall.dao.LogDao;
import com.lyh.mall.mall.entity.LogEntity;
import com.lyh.mall.mall.service.LogService;


@Service("logService")
public class LogServiceImpl extends ServiceImpl<LogDao, LogEntity> implements LogService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<LogEntity> page = this.page(
                new Query<LogEntity>().getPage(params),
                new QueryWrapper<LogEntity>()
        );

        return new PageUtils(page);
    }

}