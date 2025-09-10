package com.ywzai.javaailangchain4j.tools;

import dev.langchain4j.agent.tool.Tool;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class CalculatorTool {

    @Tool
    public double sum(double a, double b) {
        log.info("使用了加法工具");
        return a + b;
    }
}
