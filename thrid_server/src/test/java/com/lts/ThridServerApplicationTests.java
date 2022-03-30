package com.lts;

import com.alibaba.fastjson.JSON;
import com.lts.pojo.User;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.support.master.AcknowledgedResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.client.indices.GetIndexRequest;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.common.xcontent.XContentType;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;

@SpringBootTest
class ThridServerApplicationTests {

    @Autowired
    private RestHighLevelClient restHighLevelClient;

    //    测试elasearch的请求
    @Test
    void createIndex() throws IOException {
//        创建索引请求
        CreateIndexRequest createIndexRequest = new CreateIndexRequest("lts_index");
//          发送请求
        restHighLevelClient.indices().create(createIndexRequest, RequestOptions.DEFAULT);
    }

    //    测试获取索引
    @Test
    void getIndex() throws IOException {
        GetIndexRequest getIndexRequest = new GetIndexRequest("lts_index");

//        判断索引是否存在
        boolean lts_index = restHighLevelClient.indices().exists(getIndexRequest, RequestOptions.DEFAULT);

        System.out.println(lts_index);

    }

//    测试删除索引
    @Test
   void deleteIndex() throws IOException {
       DeleteIndexRequest deleteIndexRequest=new DeleteIndexRequest("lts_index");
        AcknowledgedResponse delete = restHighLevelClient.indices().delete(deleteIndexRequest, RequestOptions.DEFAULT);
        System.out.println(delete);
    }

//    创建索引，将数据存储在elasearch中
    @Test
    void forindex() throws IOException {
        User user=new User(18,"lts");
        IndexRequest indexRequest=new IndexRequest("lts_index");

        indexRequest.id("1");
        indexRequest.timeout(TimeValue.timeValueSeconds(1));
        indexRequest.timeout("1s");
//        将请求数据放入json
        indexRequest.source(JSON.toJSONString(user), XContentType.JSON);

        IndexResponse index = restHighLevelClient.index(indexRequest, RequestOptions.DEFAULT);

        System.out.println(index.status());
        System.out.println(index.toString());
    }

//      获取文档信息
    void getDocution() throws IOException {
        GetRequest getRequest=new GetRequest("lts_index");

        GetResponse documentFields = restHighLevelClient.get(getRequest, RequestOptions.DEFAULT);

//        打印文档内容
        System.out.println(documentFields.getSourceAsString());
    }
}
