package com.lts.img.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.lts.img.entity.ConectionEntity;
import com.lts.img.entity.ImsEntity;
import com.lts.img.entity.VmsEntity;
import com.lts.img.service.ConectionService;
import com.lts.img.service.ImsService;
import com.lts.img.service.VmsService;
import com.lts.start.entity.UmsEntity;
import com.lts.start.service.UmsService;
import com.lts.start.utils.JWTUtils;
import com.lts.start.utils.PageUtils;
import com.lts.start.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;



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

    @Autowired
    private ImsService imsService;

    @Autowired
    private RedisTemplate<String,String> redisTemplate;

    @Autowired
    private VmsService vmsService;

    @Autowired
    private UmsService umsService;

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
     * 获取某个用户的收藏，仅图片
     */

    /**
     * 获取某个用户的收藏，仅视频
     */


    /**
     * 获取某个用户的所有的收藏的图片和视频
     */
    @RequestMapping("/getInfo")
    public R getConection(@RequestParam String token){

        System.out.println(token);
//        查询redis，查看用户是否登录
        String status=redisTemplate.opsForValue().get(token);
//            分离token将信息分离
        String openid= (String) JWTUtils.parseJWT(token).get("account");
        if(status!=null){
//            如果redis中存在该用户的信息，则执行响应的功能

//            首先查询该用户所有的收藏的信息，imgID和vmID
//            构造查询条件
            QueryWrapper<ConectionEntity> queryWrapper=new QueryWrapper<>();

            queryWrapper.eq("userOpenID",openid);
            List<ConectionEntity> list = conectionService.list(queryWrapper);

//            遍历list查询每一个id所对应的消息，将信息存入一个list<Object>
            List<Object> result=new ArrayList<>();
            for (ConectionEntity conectionEntity : list) {
                if(conectionEntity.getImgid()!=null){
//                    如果存在imgid则查询图片的收藏
                    ImsEntity imsEntity = imsService.getById(conectionEntity.getImgid());

                    result.add(imsEntity);
                    continue;
                }
                else if(conectionEntity.getVmid()!=null){
//                    如果存在vmid，则查询视频的收藏
                    VmsEntity vmsEntity= vmsService.getById(conectionEntity.getVmid());

                    result.add(vmsEntity);

                }
            }

//            将结果返回
            return R.ok().put("msg",result);
//            查询出对应的imgID和vmID的信息
        }
        else {
//            如果redis中不存在，说明用户未登录，返回错误信息
            return R.error(400,"禁止绕过登录");
        }
    }

    /**
     * 增加收藏信息
     */
    public R insertConection(){
        return R.ok();
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
