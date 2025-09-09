package com.ywzai.javaailangchain4j.assistant;

import com.ywzai.javaailangchain4j.Assistant.Assistant;
import dev.langchain4j.community.model.dashscope.QwenChatModel;
import dev.langchain4j.service.AiServices;
import dev.langchain4j.service.spring.AiService;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class testAssistant {

    @Resource
    private QwenChatModel qwenChatModel;

    @Test
    public void testAssistant(){
        Assistant assistant = AiServices.create(Assistant.class, qwenChatModel);
        String result = assistant.chat("请写一个关于机器学习的程序");
        System.out.println(result);
    }
    @Resource
    private Assistant assistant;

    @Test
    public void testAssistant2(){
        String result = assistant.chat("给我讲讲jvm");
        System.out.println(result);
    }
}
