package com.lts.img.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lts.img.entity.ConectionEntity;
import com.lts.start.utils.PageUtils;

import java.util.Map;

/**
 * 
 *
 * @author lts
 * @email 419253381@qq.com
 * @date 2022-03-23 15:10:39
 */
public interface ConectionService extends IService<ConectionEntity> {

    PageUtils queryPage(Map<String, Object> params);

//    根据用户的openid和发送过来的vmid进行收藏
    int insertConection(String openid,Integer vmid);

}

