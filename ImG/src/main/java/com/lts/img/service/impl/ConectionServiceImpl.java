package com.lts.img.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lts.img.dao.ConectionDao;
import com.lts.img.entity.ConectionEntity;
import com.lts.img.service.ConectionService;
import com.lts.start.utils.PageUtils;
import com.lts.start.utils.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;


@Service("conectionService")
public class ConectionServiceImpl extends ServiceImpl<ConectionDao, ConectionEntity> implements ConectionService {


    @Autowired
    private ConectionDao conectionDao;
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<ConectionEntity> page = this.page(
                new Query<ConectionEntity>().getPage(params),
                new QueryWrapper<ConectionEntity>()
        );

        return new PageUtils(page);
    }

//    存放，当存入失败的时候返回一个空值
    @Override
    public int insertConection(String openid, Integer vmid) {
        ConectionEntity conectionEntity=new ConectionEntity(null,openid,null,vmid);

        int result=conectionDao.insert(conectionEntity);
        return result==1?1:null;
    }

}