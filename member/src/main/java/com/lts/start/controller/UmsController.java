package com.lts.start.controller;

import java.io.IOException;
import java.util.Arrays;
import java.util.Date;
import java.util.Map;
import java.util.UUID;

import com.lts.start.utils.GetOpenId;
import com.lts.start.utils.JWTUtils;
import com.lts.start.utils.PageUtils;
import com.lts.start.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lts.start.entity.UmsEntity;
import com.lts.start.service.UmsService;

/**
 * 
 *
 * @author lts
 * @email 419253381@qq.com
 * @date 2022-03-22 18:03:00
 */
@RestController
@RequestMapping("/start/ums")
public class UmsController {

    @Autowired
    private UmsService umsService;

    /**
     * 列表(查询所有用户的信息)
     */
    @RequestMapping("/list")
    //@RequiresPermissions("start:ums:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = umsService.queryPage(params);
        return R.ok().put("page", page);
    }


    /**
     * 信息（根据openid查询信息）
     */
    @RequestMapping("/tologin")
    //@RequiresPermissions("start:ums:info")
    public R info(String code) throws IOException {

//        根据code获取openid
        String openid= GetOpenId.getOpenID(code);
//        获取openid之后查询是否存在用户
		UmsEntity ums = umsService.getById(openid);

        if(ums==null){
//            如果不存在，则将信息注册进入
            UmsEntity umsen=new UmsEntity();
            umsen.setOpenid(openid);
            umsen.setNickname(UUID.randomUUID().toString());
            umsen.setCreatetime(new Date());
            umsService.save(umsen);
            System.out.println(umsen);
            String token=JWTUtils.acquireJWT(umsen.getNickname(),umsen.getOpenid());
            return R.ok().put("token",token);
        }else {
//           如果存在，则返回token
            String token=JWTUtils.acquireJWT(ums.getNickname(),ums.getOpenid());
            return R.ok().put("token",token);
        }
    }

//    /**
//     * 保存
//     */
//    @RequestMapping("/save")
//    //@RequiresPermissions("start:ums:save")
//    public R save(@RequestBody UmsEntity ums){
//		umsService.save(ums);
//
//        return R.ok();
//    }

    /**
     * 修改(修改用户信息，只能改昵称)
     */
    @RequestMapping("/update")
    //@RequiresPermissions("start:ums:update")
    public R update(@RequestBody UmsEntity ums){
		umsService.updateById(ums);

        return R.ok();
    }

    /**
     * 删除(注销的时候触发)
     */
    @RequestMapping("/delete")
    //@RequiresPermissions("start:ums:delete")
    public R delete(@RequestBody String[] openids){
		umsService.removeByIds(Arrays.asList(openids));

        return R.ok();
    }

}
