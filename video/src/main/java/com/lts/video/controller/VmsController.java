package com.lts.video.controller;

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

import com.lts.video.entity.VmsEntity;
import com.lts.video.service.VmsService;



/**
 * 
 *
 * @author lts
 * @email 419253381@qq.com
 * @date 2022-03-28 10:37:54
 */
@RestController
@RequestMapping("video/vms")
public class VmsController {
    @Autowired
    private VmsService vmsService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    //@RequiresPermissions("video:vms:list")
    public R list(@RequestBody Map<String, Object> params){

        System.out.println(params);
        PageUtils page = vmsService.queryPage(params);

        return R.ok().put("page", page);
    }

//    按照作者查询
    @RequestMapping("/search")
    public R search(String author){
        QueryWrapper<VmsEntity> wrapper=new QueryWrapper<>();
        System.out.println(author);

        wrapper.eq("author",author);

        List<VmsEntity> list = vmsService.list(wrapper);
        System.out.println(list);
        return R.ok().put("page",list);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{vid}")
    //@RequiresPermissions("video:vms:info")
    public R info(@PathVariable("vid") Integer vid){
		VmsEntity vms = vmsService.getById(vid);

        return R.ok().put("vms", vms);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    //@RequiresPermissions("video:vms:save")
    public R save(@RequestBody VmsEntity vms){
		vmsService.save(vms);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    //@RequiresPermissions("video:vms:update")
    public R update(@RequestBody VmsEntity vms){
		vmsService.updateById(vms);

        return R.ok();
    }
    @RequestMapping("/updatesupport")
    public R update(Integer vid,Integer support){
        System.out.println(vid+" "+support);
        VmsEntity vms=new VmsEntity();
        vms.setVid(vid);
        vms.setSupport(support);
        vmsService.updateById(vms);
        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    //@RequiresPermissions("video:vms:delete")
    public R delete(@RequestBody Integer[] vids){
		vmsService.removeByIds(Arrays.asList(vids));

        return R.ok();
    }

}
