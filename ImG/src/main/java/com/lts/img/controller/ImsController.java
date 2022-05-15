package com.lts.img.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.lts.start.utils.PageUtils;
import com.lts.start.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lts.img.entity.ImsEntity;
import com.lts.img.service.ImsService;



/**
 * 
 *
 * @author lts
 * @email 419253381@qq.com
 * @date 2022-03-23 15:10:39
 */
@RestController
@RequestMapping("img/ims")
public class ImsController {
    @Autowired
    private ImsService imsService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    //@RequiresPermissions("img:ims:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = imsService.queryPage(params);

        return R.ok().put("page", page);
    }

    /**
     * 获取点赞最高的5个图片
     */
    @RequestMapping("/list/getImg")
    public R getHotList(){
        /**
         * 获取
         */
        QueryWrapper<ImsEntity> wrapper=new QueryWrapper<>();

        wrapper.select("http_adress");

        wrapper.orderByDesc("support");

        wrapper.last("limit 5");
        List<ImsEntity> list=imsService.list(wrapper);
        List result=new ArrayList();

        for (int i = 0; i < list.size(); i++) {
            result.add(list.get(i).getHttpAdress());
        }
        return R.ok().put("page",result);
    }

    /**
     * 收藏图片功能，很少用到（不写了）
     */


    /**
     * 信息
     */
    @RequestMapping("/info/{iid}")
    //@RequiresPermissions("img:ims:info")
    public R info(@PathVariable("iid") Integer iid){
		ImsEntity ims = imsService.getById(iid);

        return R.ok().put("ims", ims);
    }



    /**
     * 保存
     */
    @RequestMapping("/save")
    //@RequiresPermissions("img:ims:save")
    public R save(@RequestBody ImsEntity ims){
		imsService.save(ims);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    //@RequiresPermissions("img:ims:update")
    public R update(@RequestBody ImsEntity ims){
		imsService.updateById(ims);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    //@RequiresPermissions("img:ims:delete")
    public R delete(@RequestBody Integer[] iids){
		imsService.removeByIds(Arrays.asList(iids));

        return R.ok();
    }

}
