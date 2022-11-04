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

import com.lyh.mall.mall.entity.UserEntity;
import com.lyh.mall.mall.service.UserService;



/**
 * 
 *
 * @author lyh
 * @email lyh.com
 * @date 2022-11-04 00:04:32
 */
@RestController
@RequestMapping("mall/user")
public class UserController {
    @Autowired
    private UserService userService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    //@RequiresPermissions("mall:user:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = userService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{userId}")
    //@RequiresPermissions("mall:user:info")
    public R info(@PathVariable("userId") Integer userId){
		UserEntity user = userService.getById(userId);

        return R.ok().put("user", user);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    //@RequiresPermissions("mall:user:save")
    public R save(@RequestBody UserEntity user){
		userService.save(user);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    //@RequiresPermissions("mall:user:update")
    public R update(@RequestBody UserEntity user){
		userService.updateById(user);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    //@RequiresPermissions("mall:user:delete")
    public R delete(@RequestBody Integer[] userIds){
		userService.removeByIds(Arrays.asList(userIds));

        return R.ok();
    }

}
