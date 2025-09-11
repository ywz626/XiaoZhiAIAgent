package com.ywzai.javaailangchain4j.config;

import com.ywzai.javaailangchain4j.memorystore.MongoChatMemoryStore;

import dev.langchain4j.data.document.Document;
import dev.langchain4j.data.document.loader.FileSystemDocumentLoader;
import dev.langchain4j.data.segment.TextSegment;
import dev.langchain4j.memory.chat.ChatMemoryProvider;
import dev.langchain4j.memory.chat.MessageWindowChatMemory;
import dev.langchain4j.rag.content.retriever.ContentRetriever;
import dev.langchain4j.rag.content.retriever.EmbeddingStoreContentRetriever;
import dev.langchain4j.store.embedding.EmbeddingStoreIngestor;
import dev.langchain4j.store.embedding.inmemory.InMemoryEmbeddingStore;
import jakarta.annotation.Resource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.List;

@Configuration
public class XiaoZhiAgentConfig {


    @Resource
    private MongoChatMemoryStore mongoChatMemoryStore;

    @Bean
    public ChatMemoryProvider chatMemoryXiaoZhi() {
        return memoryId -> MessageWindowChatMemory.builder()
                .id(memoryId)
                .maxMessages(20)
                .chatMemoryStore(mongoChatMemoryStore)
                .build();
    }

    @Bean
    public ContentRetriever contentRetrieverXiaoZhi() {
        Document document1 = FileSystemDocumentLoader.loadDocument("E:\\BaiduNetdiskDownload\\小智医疗\\2.资料\\knowledge\\科室信息.md");
        Document document2 = FileSystemDocumentLoader.loadDocument("E:\\BaiduNetdiskDownload\\小智医疗\\2.资料\\knowledge\\医院信息.md");
        Document document3 = FileSystemDocumentLoader.loadDocument("E:\\BaiduNetdiskDownload\\小智医疗\\2.资料\\knowledge\\神经内科.md");
        List<Document> list = Arrays.asList(document1, document2, document3);
        InMemoryEmbeddingStore<TextSegment> embeddingStore = new InMemoryEmbeddingStore<>();
        //使用默认的文档分割器
        EmbeddingStoreIngestor.ingest(list, embeddingStore);

        //从嵌入存储（EmbeddingStore）里检索和查询内容相关的信息
        return EmbeddingStoreContentRetriever.from(embeddingStore);
    }
}
