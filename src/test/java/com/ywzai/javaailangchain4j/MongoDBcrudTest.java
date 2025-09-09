package com.ywzai.javaailangchain4j;

import com.ywzai.javaailangchain4j.bean.ChatMessages;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;

@SpringBootTest
public class MongoDBcrudTest {

    @Resource
    private MongoTemplate mongoTemplate;


}
