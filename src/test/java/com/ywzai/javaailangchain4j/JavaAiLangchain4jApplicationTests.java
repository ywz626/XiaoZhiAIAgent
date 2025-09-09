package com.ywzai.javaailangchain4j;

import com.alibaba.dashscope.aigc.videosynthesis.VideoSynthesis;
import com.alibaba.dashscope.aigc.videosynthesis.VideoSynthesisParam;
import com.alibaba.dashscope.aigc.videosynthesis.VideoSynthesisResult;
import com.alibaba.dashscope.exception.InputRequiredException;
import com.alibaba.dashscope.exception.NoApiKeyException;
import com.alibaba.dashscope.utils.JsonUtils;
import dev.langchain4j.community.model.dashscope.QwenChatModel;
import dev.langchain4j.community.model.dashscope.WanxImageModel;
import dev.langchain4j.data.image.Image;
import dev.langchain4j.model.ollama.OllamaChatModel;
import dev.langchain4j.model.openai.OpenAiChatModel;
import dev.langchain4j.model.output.Response;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.net.URI;
import java.util.Scanner;

@SpringBootTest
class JavaAiLangchain4jApplicationTests {

    @Resource
    private OpenAiChatModel openAiChatModel;

    @Resource
    private OllamaChatModel ollamaChatModel;

    @Resource
    private QwenChatModel qwenChatModel;

    @Test
    void contextLoads() {
        String answer = openAiChatModel.chat("你是谁？");
        System.out.println(answer);
    }

    @Test
    void contextLoads2() {
        String answer = ollamaChatModel.chat("你是谁");
        System.out.println(answer);
    }

    @Test
    void testQwen() {
        String answer = qwenChatModel.chat("告诉我西红柿炒鸡蛋怎么做");
        System.out.println(answer);
    }

    @Test
    void testWan() {
        WanxImageModel wanxImageModel = WanxImageModel.builder()
                .apiKey("sk-83fcf6f642fd4a438e25a1c57eda6675")
                .modelName("wan2.2-t2i-plus")
                .build();
        Response<Image> response = wanxImageModel.generate("蒸汽朋克城市：巨大的齿轮和管道纵横交错，覆盖着整个城市的建筑。高耸入云的烟囱中喷出浓浓的黑烟，天空被染成了暗灰色。街道上，蒸汽驱动的机械车辆穿梭往来，发出嘈杂的轰鸣声。人们穿着皮质的长风衣、戴着护目镜和金属头盔，手中拿着各种机械工具和武器。一座巨大的钟楼矗立在城市中央，齿轮在钟楼上飞速转动，钟声沉闷而悠远。城市边缘，巨大的蒸汽动力飞行器缓缓升空，准备开始新的旅程。\n");
        URI url = response.content().url();
        System.out.println(url);
    }

    @Test
    void testWan2() throws NoApiKeyException, InputRequiredException {
        VideoSynthesis vs = new VideoSynthesis();
        VideoSynthesisParam param = VideoSynthesisParam.builder()
                .model("wan2.2-t2v-plus")
                .apiKey("sk-83fcf6f642fd4a438e25a1c57eda6675")
                .prompt("一头恶魔在灰蒙蒙的天空中飞行，太阳是灰色的")
                .size("1920*1080")
                .build();
        VideoSynthesisResult result = vs.call(param);
        System.out.println(JsonUtils.toJson(result));
    }
}
