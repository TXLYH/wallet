package com.lyh.mall.mall.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.lyh.mall.mall.common.utils.DateUtils;
import com.lyh.mall.mall.common.utils.PageUtils;
import com.lyh.mall.mall.common.utils.R;
import com.lyh.mall.mall.dao.AppDao;
import com.lyh.mall.mall.dao.UserDao;
import com.lyh.mall.mall.entity.AppEntity;
import com.lyh.mall.mall.entity.LogEntity;
import com.lyh.mall.mall.entity.UserEntity;
import com.lyh.mall.mall.service.AppService;
import com.lyh.mall.mall.service.LogService;
import com.lyh.mall.mall.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author liuyinghao
 * @date 2022/11/4 11:02
 */
@RestController
@RequestMapping("mall/mall")
public class mallController {

    @Autowired
    private LogService logService;
    @Autowired
    private AppService appService;
    @Autowired
    private UserService userService;
    @Autowired
    private AppDao appDao;
    @Autowired
    private UserDao useDao;

    /**
     * 查询用户钱包余额
     * @param params
     * @return
     */
    @RequestMapping("/list")
    public R applist(@RequestParam Map<String, Object> params){
        PageUtils page = appService.queryPage(params);
        return R.ok().put("page", page);
    }

    /**
     * 用户消费100元的接口
     * @param appId
     * @param price
     * @return
     */
    @Transactional(rollbackFor = {RuntimeException.class})
    @RequestMapping("/expend")
    public R expend(@PathVariable("appId") Integer appId,@PathVariable("price") Integer price) throws RuntimeException{
        //更新app表的用户钱包
        AppEntity appEntity = appDao.selectById(appId);
        appEntity.setAppAccount(appEntity.getAppAccount() - price);
        appEntity.setCreatedTime(DateUtils.formateDateTime(new Date()));
        boolean update = appService.updateById(appEntity);
        if (!update) return R.error(400,"购买失败");
        //记录日志
        LogEntity logEntity = new LogEntity();
        logEntity.setCreatTime(DateUtils.formateDateTime(new Date()));
        logEntity.setPay(price);
        logEntity.setUserId(appEntity.getUserId());
        logService.save(logEntity);
        return R.ok();
    }

    /**
     * 退款钱提到银行卡
     * @param useId
     * @param price
     * @return
     * @throws RuntimeException
     */
    @Transactional(rollbackFor = {RuntimeException.class})
    @RequestMapping("/refund")
    public R refund(@PathVariable("useId") Integer useId,@PathVariable("price") Integer price) throws RuntimeException{
        //退款钱提到银行卡
        UserEntity userEntity = useDao.selectById(useId);
        userEntity.setCard(userEntity.getCard() + price);
        userEntity.setCreatedTime(DateUtils.formateDateTime(new Date()));
        boolean update = userService.updateById(userEntity);
        if (!update) return R.error(400,"退款失败");
        //记录日志
        LogEntity logEntity = new LogEntity();
        logEntity.setCreatTime(DateUtils.formateDateTime(new Date()));
        logEntity.setPay(price);
        logEntity.setUserId(userEntity.getUserId());
        logService.save(logEntity);
        return R.ok();
    }

    /**
     * 查询某个用户的账单日志
     * @param useId
     * @return
     * @throws RuntimeException
     */
    @RequestMapping("/logList")
    public R logList(@PathVariable("useId") Integer useId) throws RuntimeException{
        List<LogEntity> list = logService.list(new QueryWrapper<LogEntity>().eq("user_id", useId));
        R r = new R();
        list.forEach((item)->{
            Integer money = item.getMoney();
            Integer pay = item.getPay();
            String time = item.getCreatTime();
            if(pay != null && pay != 0) r.put("用户消费：",pay).put("日期",time);
            if(money != null && money != 0) r.put("用户收入：",money).put("日期",time);
        });
        return r.ok();
    }
}
