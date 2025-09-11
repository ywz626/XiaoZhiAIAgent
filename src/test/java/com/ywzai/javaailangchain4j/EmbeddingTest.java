package com.ywzai.javaailangchain4j;

import dev.langchain4j.data.document.Document;
import dev.langchain4j.data.document.loader.FileSystemDocumentLoader;
import dev.langchain4j.data.embedding.Embedding;
import dev.langchain4j.data.segment.TextSegment;
import dev.langchain4j.model.embedding.EmbeddingModel;
import dev.langchain4j.model.output.Response;
import dev.langchain4j.store.embedding.*;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;

@SpringBootTest
public class EmbeddingTest {

    @Resource
    private EmbeddingModel embeddingModel;

    @Test
    public void testEmbedding() {
        Response<Embedding> text = embeddingModel.embed("我叫于汶泽");
        Embedding content = text.content();
        System.out.println(text.content().vector().length);
        System.out.println(text);
    }

    @Resource
    private EmbeddingStore embeddingStore;

    /**
     * 将文本转换成向量，然后存储到pinecone中
     *
     * 参考：
     * https://docs.langchain4j.dev/tutorials/embedding-stores
     */
    @Test
    public void testPineconeEmbeded() {

        //将文本转换成向量
        TextSegment segment1 = TextSegment.from("我喜欢羽毛球");
        Embedding embedding1 = embeddingModel.embed(segment1).content();
        //存入向量数据库
        embeddingStore.add(embedding1, segment1);

        TextSegment segment2 = TextSegment.from("今天天气很好");
        Embedding embedding2 = embeddingModel.embed(segment2).content();
        embeddingStore.add(embedding2, segment2);
    }

    /**
     * 测试能否从向量数据库中检索到信息
     */
    @Test
    public void testPineconeSearch() {
        Embedding content = embeddingModel.embed("你最喜欢的运动是什么?").content();
        EmbeddingSearchRequest searchRequest = EmbeddingSearchRequest.builder()
                .queryEmbedding(content)
                .maxResults(1)
                .build();
        EmbeddingSearchResult<TextSegment> search = embeddingStore.search(searchRequest);
        EmbeddingMatch<TextSegment> textSegmentEmbeddingMatch = search.matches().get(0);
        System.out.println(textSegmentEmbeddingMatch.score());
        System.out.println(textSegmentEmbeddingMatch.embedded().text());
    }

    /**
     * 将知识库文档存入向量数据库中
     */
    @Test
    public void dataTest(){
        Document document1 = FileSystemDocumentLoader.loadDocument("E:\\BaiduNetdiskDownload\\小智医疗\\2.资料\\knowledge\\科室信息.md");
        Document document2 = FileSystemDocumentLoader.loadDocument("E:\\BaiduNetdiskDownload\\小智医疗\\2.资料\\knowledge\\医院信息.md");
        Document document3 = FileSystemDocumentLoader.loadDocument("E:\\BaiduNetdiskDownload\\小智医疗\\2.资料\\knowledge\\神经内科.md");
        List<Document> list = Arrays.asList(document1, document2, document3);
        EmbeddingStoreIngestor.builder()
                .embeddingStore(embeddingStore)
                .embeddingModel(embeddingModel)
                .build()
                .ingest(list);
    }
}
