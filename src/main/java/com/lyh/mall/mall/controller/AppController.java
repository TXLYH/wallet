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

import com.lyh.mall.mall.entity.AppEntity;
import com.lyh.mall.mall.service.AppService;



/**
 * 
 *
 * @author lyh
 * @email lyh.com
 * @date 2022-11-04 00:04:32
 */
@RestController
@RequestMapping("mall/app")
public class AppController {
    @Autowired
    private AppService appService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    //@RequiresPermissions("mall:app:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = appService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{appId}")
    //@RequiresPermissions("mall:app:info")
    public R info(@PathVariable("appId") Integer appId){
		AppEntity app = appService.getById(appId);

        return R.ok().put("app", app);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    //@RequiresPermissions("mall:app:save")
    public R save(@RequestBody AppEntity app){
		appService.save(app);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    //@RequiresPermissions("mall:app:update")
    public R update(@RequestBody AppEntity app){
		appService.updateById(app);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    //@RequiresPermissions("mall:app:delete")
    public R delete(@RequestBody Integer[] appIds){
		appService.removeByIds(Arrays.asList(appIds));

        return R.ok();
    }

}
