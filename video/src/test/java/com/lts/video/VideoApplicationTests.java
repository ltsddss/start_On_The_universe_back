package com.lts.video;

import com.lts.start.utils.PageUtils;
import com.lts.video.service.VmsService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringBootTest(classes = VideoApplicationTests.class)
class VideoApplicationTests {

    @Autowired
    private VmsService vmsService;

    @Test
    void contextLoads() {
        Map<String,Object> param=new HashMap<>();
        PageUtils page = vmsService.queryPage(param);

        System.out.println(page);
    }

    @Test
    public void getmap(){
        Map<String,Object> param=new HashMap<>();
        PageUtils page = vmsService.queryPage(param);

        System.out.println(page);
    }

}
