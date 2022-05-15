package com.lts.img.controller;

import com.alibaba.fastjson.JSONObject;
import com.lts.img.entity.VmsEntity;
import com.lts.img.service.ConectionService;
import com.lts.img.service.VmsService;
import com.lts.start.utils.JWTUtils;
import com.lts.start.utils.PageUtils;
import com.lts.start.utils.R;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.index.query.MatchPhraseQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.Arrays;
import java.util.Map;
import java.util.concurrent.TimeUnit;



/**
 * 
 *
 * @author lts
 * @email 419253381@qq.com
 * @date 2022-03-28 10:37:54
 */
@RestController
@RequestMapping("video/vms")
public class VmsController {
    @Autowired
    private VmsService vmsService;

    @Autowired
    private RestHighLevelClient restHighLevelClient;

    @Autowired
    private ConectionService conectionService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    //@RequiresPermissions("video:vms:list")
    public R list(@RequestBody Map<String, Object> params){

        System.out.println(params);
        PageUtils page = vmsService.queryPage(params);

        return R.ok().put("page", page);
    }

//    按照作者查询
//    @RequestMapping("/search")
//    public R search(String author){
//        QueryWrapper<VmsEntity> wrapper=new QueryWrapper<>();
//        System.out.println(author);
//
//        wrapper.eq("author",author);
//
//        List<VmsEntity> list = vmsService.list(wrapper);
//        System.out.println(list);
//        return R.ok().put("page",list);
//    }

//    使用elasticSearch来进行全站搜索(查询视频的处理逻辑)
    @RequestMapping("/search")
    public R getinformation(String author) throws IOException {
        SearchRequest request=new SearchRequest("lts_index");

        SearchSourceBuilder builder=new SearchSourceBuilder();

        MatchPhraseQueryBuilder QueryBuilder = QueryBuilders.matchPhraseQuery("author", author);
//        设置超时处理
        builder.timeout(new TimeValue(60, TimeUnit.SECONDS));

        builder.query(QueryBuilder);

        request.source(builder);

        SearchResponse search = restHighLevelClient.search(request, RequestOptions.DEFAULT);

        return R.ok().put("page", search);

    }

    /**
     * 收藏接口
     */
    @RequestMapping("/conection/")
    public R toConection(@RequestBody JSONObject jsonObject){
//        用户点击某个视频的收藏按钮，前端会将用户的token及用户收藏视频的id发送过来
//        将需要的信息提取出来
        String token=jsonObject.getString("token");

        Integer vmid=jsonObject.getInteger("vid");

//        对token进行解密获取用户的openid
        String account=(String) JWTUtils.parseJWT(token).get("account");

//        调用IMG服务的收藏功能
//        此接口存放的时候存入用户对于视频的收藏
        int result=conectionService.insertConection(account,vmid);
//        如果result的结果为1，则返回收藏成功，若为空则失败
        if(result==1){
            return R.ok().put("msg","收藏成功");
        }
        else {
            return R.error(400,"收藏失败");
        }
    }

    /**
     * TODO:获取自己所有的作品
     */
    public R getVMC(@RequestBody String token){
//        根据token获取自己的所有的作品信息
        return R.ok();
    }

    /**
     * 信息
     */
    @RequestMapping("/info/{vid}")
    //@RequiresPermissions("video:vms:info")
    public R info(@PathVariable("vid") Integer vid){
		VmsEntity vms = vmsService.getById(vid);

        return R.ok().put("vms", vms);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    //@RequiresPermissions("video:vms:save")
    public R save(@RequestBody VmsEntity vms){
		vmsService.save(vms);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    //@RequiresPermissions("video:vms:update")
    public R update(@RequestBody VmsEntity vms){
		vmsService.updateById(vms);

        return R.ok();
    }
    @RequestMapping("/updatesupport")
    public R update(Integer vid,Integer support){
        System.out.println(vid+" "+support);
        VmsEntity vms=new VmsEntity();
        vms.setVid(vid);
        vms.setSupport(support);
        vmsService.updateById(vms);
        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    //@RequiresPermissions("video:vms:delete")
    public R delete(@RequestBody Integer[] vids){
		vmsService.removeByIds(Arrays.asList(vids));

        return R.ok();
    }

}
