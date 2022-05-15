package com.lts;

import com.alibaba.fastjson.JSON;
import com.lts.pojo.User;
import org.apache.lucene.util.QueryBuilder;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.support.master.AcknowledgedResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.client.indices.GetIndexRequest;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.TermQueryBuilder;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.naming.directory.SearchResult;
import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

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
    @Test
    void getDocution() throws IOException {
        GetRequest getRequest=new GetRequest("lts_index","1");

        GetResponse documentFields = restHighLevelClient.get(getRequest, RequestOptions.DEFAULT);

//        打印文档内容
        System.out.println(documentFields.getSourceAsString());
    }

//    批量插入数据
    @Test
    void putdataList() throws IOException {
        BulkRequest bulkRequest=new BulkRequest();

        bulkRequest.timeout("10s");

        ArrayList<User> list=new ArrayList<>();
        
        list.add(new User(12,"张三"));
        list.add(new User(12,"里斯"));
        list.add(new User(12,"王五"));
        list.add(new User(12,"妞儿"));
        list.add(new User(12,"二愣子"));
        list.add(new User(12,"三炮"));
        list.add(new User(12,"丈二麻子"));

        for (int i = 0; i < list.size(); i++) {
            bulkRequest.add(
                    new IndexRequest("lts_index")
//                            .id(""+(i+1))不写的或就会默认生成一个id
                            .source(JSON.toJSONString(list.get(i)),XContentType.JSON)
            );
        }

        restHighLevelClient.bulk(bulkRequest,RequestOptions.DEFAULT);

    }
    @Test
    void Searchbudiessr() throws IOException {
//        查询索引
        SearchRequest request=new SearchRequest("lts_index");

//      构造查询条件
        SearchSourceBuilder searchSourceBuilder=new SearchSourceBuilder();
//      queryBudier来实现创建查询条件

//        精确：
//QueryBuilders.termQuery("name","王五");
        TermQueryBuilder termQueryBuilder = QueryBuilders.termQuery("age", "12");

        searchSourceBuilder.query(termQueryBuilder);

        searchSourceBuilder.timeout(new TimeValue(60, TimeUnit.SECONDS));

        request.source(searchSourceBuilder);
        SearchResponse search = restHighLevelClient.search(request, RequestOptions.DEFAULT);

        System.out.println(JSON.toJSONString(search.getHits()));
        System.out.println("==================");
    }
}
