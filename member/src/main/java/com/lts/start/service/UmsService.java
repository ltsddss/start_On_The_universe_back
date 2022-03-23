package com.lts.start.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lts.start.entity.UmsEntity;
import com.lts.start.utils.PageUtils;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * 
 *
 * @author lts
 * @email 419253381@qq.com
 * @date 2022-03-22 18:03:00
 */
@Service
public interface UmsService extends IService<UmsEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

