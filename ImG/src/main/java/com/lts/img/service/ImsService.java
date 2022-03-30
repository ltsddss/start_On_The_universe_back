package com.lts.img.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lts.img.entity.ImsEntity;
import com.lts.start.utils.PageUtils;

import java.util.Map;

/**
 * 
 *
 * @author lts
 * @email 419253381@qq.com
 * @date 2022-03-23 15:10:39
 */
public interface ImsService extends IService<ImsEntity> {

    PageUtils queryPage(Map<String, Object> params);

}

