package com.ywzai.javaailangchain4j.controllor;

import com.ywzai.javaailangchain4j.Assistant.XiaoZhiAgent;
import com.ywzai.javaailangchain4j.bean.UserMessage;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.annotation.Resource;
import opennlp.tools.cmdline.ArgumentParser;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
@RequestMapping("/xiaozhi")
public class XiaoZhiAgentController {
    @Resource
    private XiaoZhiAgent xiaoZhiAgent;
    @Operation(summary = "对话")
    @PostMapping(value = "/chat",produces = "text/stream;charset=utf-8")
    public Flux<String> chat(@RequestBody UserMessage chatForm)  {
        return xiaoZhiAgent.chat(chatForm.getMemoryId(), chatForm.getMessage());
    }
}
