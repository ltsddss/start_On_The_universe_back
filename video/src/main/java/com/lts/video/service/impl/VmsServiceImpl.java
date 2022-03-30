package com.lts.video.service.impl;

import com.lts.start.utils.PageUtils;
import com.lts.start.utils.Query;
import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.lts.video.dao.VmsDao;
import com.lts.video.entity.VmsEntity;
import com.lts.video.service.VmsService;


@Service("vmsService")
public class VmsServiceImpl extends ServiceImpl<VmsDao, VmsEntity> implements VmsService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<VmsEntity> page = this.page(
                new Query<VmsEntity>().getPage(params),
                new QueryWrapper<VmsEntity>()
        );

        return new PageUtils(page);
    }

}