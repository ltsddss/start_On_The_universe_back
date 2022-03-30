package com.lts.img.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lts.img.dao.ImsDao;
import com.lts.img.entity.ImsEntity;
import com.lts.img.service.ImsService;
import com.lts.start.utils.PageUtils;
import com.lts.start.utils.Query;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


@Service("imsService")
public class ImsServiceImpl extends ServiceImpl<ImsDao, ImsEntity> implements ImsService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<ImsEntity> page = this.page(
                new Query<ImsEntity>().getPage(params),
                new QueryWrapper<ImsEntity>()
        );

        return new PageUtils(page);
    }

}