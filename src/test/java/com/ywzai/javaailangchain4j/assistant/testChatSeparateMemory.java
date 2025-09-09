package com.ywzai.javaailangchain4j.assistant;

import com.ywzai.javaailangchain4j.Assistant.SeparateChatAssistant;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class testChatSeparateMemory {

    @Resource
    private SeparateChatAssistant separateChatAssistant;

    @Test
    public void testChatSeparateMemory() {
        String result = separateChatAssistant.chat(1, "我是ywz");
        System.out.println(result);
        result = separateChatAssistant.chat(1, "我是谁");
        System.out.println(result);
        result = separateChatAssistant.chat(2, "我是谁");
        System.out.println(result);
    }
}
