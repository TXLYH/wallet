package com.lyh.mall.mall.controller;

import java.util.Arrays;
import java.util.Map;

import com.lyh.mall.mall.common.utils.PageUtils;
import com.lyh.mall.mall.common.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lyh.mall.mall.entity.LogEntity;
import com.lyh.mall.mall.service.LogService;



/**
 * 
 *
 * @author lyh
 * @email lyh.com
 * @date 2022-11-04 00:04:32
 */
@RestController
@RequestMapping("mall/log")
public class LogController {
    @Autowired
    private LogService logService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    //@RequiresPermissions("mall:log:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = logService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{logId}")
    //@RequiresPermissions("mall:log:info")
    public R info(@PathVariable("logId") Integer logId){
		LogEntity log = logService.getById(logId);

        return R.ok().put("log", log);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    //@RequiresPermissions("mall:log:save")
    public R save(@RequestBody LogEntity log){
		logService.save(log);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    //@RequiresPermissions("mall:log:update")
    public R update(@RequestBody LogEntity log){
		logService.updateById(log);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    //@RequiresPermissions("mall:log:delete")
    public R delete(@RequestBody Integer[] logIds){
		logService.removeByIds(Arrays.asList(logIds));

        return R.ok();
    }

}
