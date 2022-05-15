package com.lts.img.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lts.img.entity.VmsEntity;
import com.lts.start.utils.PageUtils;

import java.util.Map;

/**
 * 
 *
 * @author lts
 * @email 419253381@qq.com
 * @date 2022-03-28 10:37:54
 */
public interface VmsService extends IService<VmsEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

