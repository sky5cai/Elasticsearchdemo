package com.example.elasticsearch.demo.demo.elasticsearch.controller;

import com.example.elasticsearch.demo.demo.elasticsearch.model.Friends;
import com.example.elasticsearch.demo.demo.elasticsearch.service.BlogRepository;
import com.example.elasticsearch.demo.demo.elasticsearch.model.BlogModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/blog")
public class BlogController {
    @Autowired
    private BlogRepository blogRepository;

    @PostMapping("/add")
    public String add(@RequestBody BlogModel blogModel) {
        Map<String, Friends> maps = new HashMap<String,Friends>();
        Friends friends = new Friends();
        friends.setName("自行车在路上");
        friends.setNo("333");
        maps.put("11",friends);
        blogModel.setFriends2(friends);
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

    @GetMapping("/title")
    public List repSearchTitle(@RequestParam String keyword) {
        if (StringUtils.isEmpty(keyword))
            return null;
        return blogRepository.findByTitleLike(keyword);
    }
}
