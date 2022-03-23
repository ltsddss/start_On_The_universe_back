package com.lts.start.service.impl;

import com.lts.start.utils.PageUtils;
import com.lts.start.utils.Query;
import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.lts.start.dao.UmsDao;
import com.lts.start.entity.UmsEntity;
import com.lts.start.service.UmsService;


@Service("umsService")
public class UmsServiceImpl extends ServiceImpl<UmsDao, UmsEntity> implements UmsService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<UmsEntity> page = this.page(
                new Query<UmsEntity>().getPage(params),
                new QueryWrapper<UmsEntity>()
        );

        return new PageUtils(page);
    }

}