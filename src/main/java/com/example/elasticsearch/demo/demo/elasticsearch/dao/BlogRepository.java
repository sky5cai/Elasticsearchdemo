package com.example.elasticsearch.demo.demo.elasticsearch.dao;

import com.example.elasticsearch.demo.demo.elasticsearch.model.BlogModel;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface BlogRepository extends ElasticsearchRepository<BlogModel, String> {
}