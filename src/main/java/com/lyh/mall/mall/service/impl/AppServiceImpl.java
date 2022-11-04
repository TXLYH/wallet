package com.lyh.mall.mall.service.impl;

import com.lyh.mall.mall.common.utils.PageUtils;
import com.lyh.mall.mall.common.utils.Query;
import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lyh.mall.mall.dao.AppDao;
import com.lyh.mall.mall.entity.AppEntity;
import com.lyh.mall.mall.service.AppService;


@Service("appService")
public class AppServiceImpl extends ServiceImpl<AppDao, AppEntity> implements AppService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<AppEntity> page = this.page(
                new Query<AppEntity>().getPage(params),
                new QueryWrapper<AppEntity>()
        );

        return new PageUtils(page);
    }

}