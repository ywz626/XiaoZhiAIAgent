package com.ywzai.javaailangchain4j.Assistant;

import dev.langchain4j.service.MemoryId;
import dev.langchain4j.service.SystemMessage;
import dev.langchain4j.service.UserMessage;
import dev.langchain4j.service.spring.AiService;

import static dev.langchain4j.service.spring.AiServiceWiringMode.EXPLICIT;

@AiService(wiringMode = EXPLICIT,
        chatModel = "qwenChatModel",
        chatMemoryProvider = "chatMemoryXiaoZhi")
public interface XiaoZhiAgent {


    @SystemMessage(fromResource = "system-xiaozhi-prompt.txt")
    String chat(@MemoryId Long memoryId, @UserMessage String userMessage);
}
