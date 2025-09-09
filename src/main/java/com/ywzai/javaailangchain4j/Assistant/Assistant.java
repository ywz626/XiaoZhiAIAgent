package com.ywzai.javaailangchain4j.Assistant;

import dev.langchain4j.service.spring.AiService;
import dev.langchain4j.service.spring.AiServiceWiringMode;

@AiService(wiringMode = AiServiceWiringMode.EXPLICIT,chatModel = "qwenChatModel")
public interface Assistant {
    String chat(String userMessage);
}
