package com.example.elasticsearch.demo.demo.elasticsearch.service;

import com.example.elasticsearch.demo.demo.elasticsearch.model.BlogModel;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public interface BlogRepository extends ElasticsearchRepository<BlogModel, String> {

    List<BlogModel> findByTitleLike(String keyword);
}
