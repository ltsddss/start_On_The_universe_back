package com.lts.start.controller;

import com.lts.start.entity.UmsEntity;
import com.lts.start.service.UmsService;
import com.lts.start.utils.GetOpenId;
import com.lts.start.utils.JWTUtils;
import com.lts.start.utils.PageUtils;
import com.lts.start.utils.R;
import com.lts.start.xss.SQLFilter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.*;

/**
 * 
 *
 * @author lts
 * @email 419253381@qq.com
 * @date 2022-03-22 18:03:00
 */
@RestController
@Slf4j
@RequestMapping("/start/ums")
public class UmsController {

    @Autowired
    private UmsService umsService;

    @Autowired
    private RedisTemplate<String,String> redisTemplate;

    /**
     * 列表(查询所有用户的信息)
     */
    /**
     * 后台接口（操作所有用户）
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

//        对用户发送的信息进行判断，防止用户进行注入攻击
        if(Objects.equals(SQLFilter.sqlInject(code), "")){
            return R.error(400,"禁止进行注入");
        }
//        如果openid为空，说明code不合法，则返回错误信息
        if(openid==null){
            return R.error(400,"禁止发送无用消息");
        }

//        使用openid创建token
        String token=JWTUtils.acquireJWT("用户账号",openid);

//        向redis中存入该用户的token，查看是否存在

        String state=redisTemplate.opsForValue().get(token);

        if(state!=null){
//            说明用户已经在redis中存在，直接返回响应信息
            log.info("openid为："+openid+"的用户成功登录");
            return R.ok().put("token",token);
        }
        else{
//            如果在redis中没有找到该用户，则首先向数据库中查询是否存在该用户
            UmsEntity ums = umsService.getById(openid);

            if(ums!=null){
//                如果查询到了该用户的信息，则将用户的信息存入redis
                if(ums.getStatus()==0){
//                    如果用户处于未启用状态，则返回无法登录
                    return R.error(400,"对不起您无法登录");
                }
                redisTemplate.opsForValue().set(token,"1");
//                返回用户的信息
                return R.ok().put("token",token);
            }
            else {
//                如果数据库中不存在该用户的信息，则将该用户存入数据库
                UmsEntity umsEntity=new UmsEntity(openid,UUID.randomUUID().toString(),new Date(),1);

                umsService.save(umsEntity);

                return R.ok().put("token",token);
            }

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
