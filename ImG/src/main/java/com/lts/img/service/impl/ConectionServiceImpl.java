package com.lts.img.service.impl;

import com.lts.start.utils.PageUtils;
import com.lts.start.utils.Query;
import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.lts.img.dao.ConectionDao;
import com.lts.img.entity.ConectionEntity;
import com.lts.img.service.ConectionService;


@Service("conectionService")
public class ConectionServiceImpl extends ServiceImpl<ConectionDao, ConectionEntity> implements ConectionService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<ConectionEntity> page = this.page(
                new Query<ConectionEntity>().getPage(params),
                new QueryWrapper<ConectionEntity>()
        );

        return new PageUtils(page);
    }

}