package com.example.elasticsearch.demo.demo.elasticsearch.controller;

import com.example.elasticsearch.demo.demo.elasticsearch.model.BlogModel;
import com.example.elasticsearch.demo.demo.elasticsearch.service.BlogRepository;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.aggregation.AggregatedPage;
import org.springframework.data.elasticsearch.core.query.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * elasticsearchTemplate模板查询
 */
@RestController
@RequestMapping("/blog2")
public class ElasticsearchTemplateController {

    @Autowired
    private BlogRepository blogRepository;

    @Autowired
    private ElasticsearchTemplate elasticsearchTemplate;

    @PostMapping("/add")
    public String add(@RequestBody BlogModel blogModel) {
//        blogRepository.save(blogModel);
        IndexQuery indexQuery = new IndexQueryBuilder().withIndexName("blog").withId(blogModel.getId()).withObject(blogModel).build();
        elasticsearchTemplate.index(indexQuery);
        return "SAVE IN ELASTICSEARCH OK";
    }

    @GetMapping("/get")
    public BlogModel get(@RequestParam String id) {
//        blogRepository.save(blogModel);
        GetQuery getQuery = new GetQuery();
        getQuery.setId(id);
        BlogModel blogModel = elasticsearchTemplate.queryForObject(getQuery,BlogModel.class);
        return blogModel;
    }

    @GetMapping("/get/boolquery")
    public Page<BlogModel> get2(@RequestParam String title) {
        title="自行车";
//        title="美国";
//        blogRepository.save(blogModel);
        BoolQueryBuilder queryBuilder = QueryBuilders.boolQuery();
        queryBuilder.should(QueryBuilders.fuzzyQuery("friends",title));
        Pageable page =  PageRequest.of(0,100);
        //构建查询
        NativeSearchQuery searchQuery = new NativeSearchQueryBuilder()
                .withQuery(queryBuilder)
//                .withQuery(QueryBuilders.matchQuery("title",title))
//                .withIndices("blog")
//                .withIndices("java")
                .withPageable(page)

                .build();

//        List<BlogModel> blogModels = elasticsearchTemplate.queryForList(searchQuery, BlogModel.class);
//        AggregatedPage<BlogModel> blogModels = elasticsearchTemplate.queryForPage(searchQuery, BlogModel.class);
        Page<BlogModel> search = blogRepository.search(searchQuery);

        return search;

    }




}
