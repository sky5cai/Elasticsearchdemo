package com.example.elasticsearch.demo.demo.elasticsearch.controller;

import com.example.elasticsearch.demo.demo.elasticsearch.dao.BlogRepository;
import com.example.elasticsearch.demo.demo.elasticsearch.model.BlogModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/blog")
public class BlogController {
    @Autowired
    private BlogRepository blogRepository;

    @PostMapping("/add")
    public String add(@RequestBody BlogModel blogModel) {
        blogRepository.save(blogModel);
        return "SAVE IN ELASTICSEARCH OK";
    }

    @GetMapping("/get/{id}")
    public BlogModel getById(@PathVariable String id) {

        Optional<BlogModel> blogModelOptional = blogRepository.findById(id);
        if (blogModelOptional.isPresent()) {
            BlogModel blogModel = blogModelOptional.get();
            return blogModel;
        }
        return null;
    }

    @GetMapping("/get")
    public List getAll() {
        Iterable<BlogModel> iterable = blogRepository.findAll();
        List<BlogModel> list = new ArrayList<>();
        iterable.forEach(list::add);
        return list;
    }
}
