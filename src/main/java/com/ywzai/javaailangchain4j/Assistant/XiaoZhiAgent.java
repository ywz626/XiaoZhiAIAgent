package com.ywzai.javaailangchain4j.Assistant;

import dev.langchain4j.service.MemoryId;
import dev.langchain4j.service.SystemMessage;
import dev.langchain4j.service.UserMessage;
import dev.langchain4j.service.spring.AiService;
import reactor.core.publisher.Flux;

import static dev.langchain4j.service.spring.AiServiceWiringMode.EXPLICIT;

@AiService(wiringMode = EXPLICIT,
        streamingChatModel= "qwenStreamingChatModel",
        chatMemoryProvider = "chatMemoryXiaoZhi",
        tools = "appointmentTool",
        contentRetriever = "contentRetrieverXiaoZhi" )
public interface XiaoZhiAgent {


    @SystemMessage(fromResource = "system-xiaozhi-prompt.txt")
    Flux<String> chat(@MemoryId Long memoryId, @UserMessage String userMessage);
}
