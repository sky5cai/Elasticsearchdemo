package com.example.elasticsearch.demo.demo.elasticsearch.model;


import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.util.JSONPObject;
import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;
import java.util.Map;

@Data
@Accessors(chain = true)
@Document(indexName = "blog", type = "java")
public class BlogModel implements Serializable {

    private static final long serialVersionUID = 6320548148250372657L;

    @Id
    private String id;

    @Field(type = FieldType.Text, analyzer = "ik_max_word")
    private String title;

    //@Field(type = FieldType.Date, format = DateFormat.basic_date)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date time;
    @Field(type = FieldType.Text, analyzer = "ik_max_word")
    private String friends;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFriends() {
        return friends;
    }

    public void setFriends(String friends) {
        this.friends = friends;
    }

    public void setFriends2(Friends friends) {
        this.friends = JSONObject.toJSONString(friends);
    }
}