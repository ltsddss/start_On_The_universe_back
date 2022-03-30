package com.lts.img.controller;

import java.util.Arrays;
import java.util.Map;

import com.lts.start.utils.PageUtils;
import com.lts.start.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lts.img.entity.ConectionEntity;
import com.lts.img.service.ConectionService;



/**
 * 
 *
 * @author lts
 * @email 419253381@qq.com
 * @date 2022-03-23 15:10:39
 */
@RestController
@RequestMapping("img/conection")
public class ConectionController {
    @Autowired
    private ConectionService conectionService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    //@RequiresPermissions("img:conection:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = conectionService.queryPage(params);

        return R.ok().put("page", page);
    }



    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    //@RequiresPermissions("img:conection:info")
    public R info(@PathVariable("id") Integer id){

		ConectionEntity conection = conectionService.getById(id);

        return R.ok().put("conection", conection);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    //@RequiresPermissions("img:conection:save")
    public R save(@RequestBody ConectionEntity conection){
		conectionService.save(conection);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    //@RequiresPermissions("img:conection:update")
    public R update(@RequestBody ConectionEntity conection){
		conectionService.updateById(conection);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    //@RequiresPermissions("img:conection:delete")
    public R delete(@RequestBody Integer[] ids){
		conectionService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
