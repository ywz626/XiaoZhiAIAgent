package com.ywzai.javaailangchain4j;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.swing.*;

@SpringBootApplication
public class JavaAiLangchain4jApplication {

    public static void main(String[] args) {
        SpringApplication springApplication = new SpringApplication(JavaAiLangchain4jApplication.class);
        springApplication.setBanner((environment, sourceClass, out) -> {
            out.println("自定义 Banner！");
        });
        springApplication.run(args);
    }

}
