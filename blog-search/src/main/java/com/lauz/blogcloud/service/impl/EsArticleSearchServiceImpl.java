package com.lauz.blogcloud.service.impl;

import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import com.lauz.blogcloud.dao.EsArticleDao;
import com.lauz.blogcloud.dto.ArticleSearchDTO;
import com.lauz.blogcloud.repository.EsArticleRepository;
import com.lauz.blogcloud.service.EsArticleSearchService;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.text.Text;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightField;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class EsArticleSearchServiceImpl implements EsArticleSearchService {

    @Autowired
    private ElasticsearchRestTemplate elasticsearchRestTemplate;
    @Autowired
    private EsArticleRepository esArticleRepository;
    @Resource
    private EsArticleDao esArticleDao;
    @Autowired
    private RestHighLevelClient client;


    @Override
    public int importAll() {
        List<ArticleSearchDTO> esArticles = esArticleDao.getEsArticles();
        Iterable<ArticleSearchDTO> esArticleIterable = esArticleRepository.saveAll(esArticles);
        Iterator<ArticleSearchDTO> iterator = esArticleIterable.iterator();
        int result = 0;
        while (iterator.hasNext()) {
            result++;
            iterator.next();
        }
        return result;
    }

    @Override
    public List<ArticleSearchDTO> search(String keyword) {
        return searchArticle(buildQuery(keyword));
    }

    /**
     * 搜索文章构造
     *
     * @param keyword 条件
     * @return es条件构造器
     */
    private NativeSearchQueryBuilder buildQuery(String keyword) {
        //条件构造器
        NativeSearchQueryBuilder nativeSearchQueryBuilder = new NativeSearchQueryBuilder();
        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
        //根据关键词搜索文章标题或内容
        if (StrUtil.isNotBlank(keyword)) {
            boolQueryBuilder.must(QueryBuilders.boolQuery().should(QueryBuilders.matchQuery("articleTitle", keyword))
                    .should(QueryBuilders.matchQuery("articleContent", keyword)));
        }
        //查询
        nativeSearchQueryBuilder.withQuery(boolQueryBuilder);
        return nativeSearchQueryBuilder;
    }

    /**
     * 文章搜索结果高亮
     * 使用elasticsearchRestTemplate
     * @param nativeSearchQueryBuilder es条件构造器
     * @return 搜索结果
     */
    private List<ArticleSearchDTO> searchArticle(NativeSearchQueryBuilder nativeSearchQueryBuilder) {
        //添加文章标题高亮
        HighlightBuilder.Field titleField = new HighlightBuilder.Field("articleTitle");
        titleField.preTags("<span style='color:#f47466'>");
        titleField.postTags("</span>");
        //添加文章内容高亮
        HighlightBuilder.Field contentField = new HighlightBuilder.Field("articleContent");
        contentField.preTags("<span style='color:#f47466'>");
        contentField.postTags("</span>");
        contentField.fragmentSize(200);
        nativeSearchQueryBuilder.withHighlightFields(titleField, contentField);
        NativeSearchQuery searchQuery = nativeSearchQueryBuilder.build();
        SearchHits<ArticleSearchDTO> searchHits = elasticsearchRestTemplate.search(searchQuery,ArticleSearchDTO.class);
        List<ArticleSearchDTO> searchDTOS = searchHits.stream().map(SearchHit::getContent).collect(Collectors.toList());
        //elasticsearchRestTemplate.queryForPage()
        /*AggregatedPage<ArticleSearchDTO> page = elasticsearchRestTemplate.queryForPage(nativeSearchQueryBuilder.build(), ArticleSearchDTO.class, new SearchResultMapper() {
            @Override
            public <T> AggregatedPage<T> mapResults(SearchResponse response, Class<T> aClass, Pageable pageable) {
                List list = new ArrayList();
                for (SearchHit hit : response.getHits()) {
                    //获取所有数据
                    ArticleSearchDTO article = JSON.parseObject(hit.getSourceAsString(), ArticleSearchDTO.class);
                    //获取文章标题高亮数据
                    HighlightField titleField = hit.getHighlightFields().get("articleTitle");
                    if (titleField != null && titleField.getFragments() != null) {
                        //替换标题数据
                        article.setArticleTitle(titleField.getFragments()[0].toString());
                    }
                    //获取文章内容高亮数据
                    HighlightField contentField = hit.getHighlightFields().get("articleContent");
                    if (contentField != null && contentField.getFragments() != null) {
                        //替换内容数据
                        article.setArticleContent(contentField.getFragments()[0].toString());
                    }
                    list.add(article);
                }
                return new AggregatedPageImpl<T>(list, pageable, response.getHits().getTotalHits());
            }
        });*/
        return searchDTOS;
    }


    /**
     * 文章搜索结果高亮
     * 使用RestHighLevelClient
     * @param keyword
     * @return
     */
    private List<ArticleSearchDTO> searchArticle(String keyword){
        List<ArticleSearchDTO> searchDTOS = new ArrayList<>();
        //1.构建检索条件
        SearchRequest searchRequest = new SearchRequest();
        //2.指定要检索的索引库
        searchRequest.indices("");
        //3.指定检索条件
        SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
        sourceBuilder.query(QueryBuilders.multiMatchQuery(keyword,"articleTitle"));
        sourceBuilder.query(QueryBuilders.multiMatchQuery(keyword,"articleContent"));
        //4.结果高亮
        HighlightBuilder highlightBuilder = new HighlightBuilder();
        //如果该属性中有多个关键字 则都高亮
        highlightBuilder.requireFieldMatch(true);
        highlightBuilder.field("articleTitle").field("articleContent");
        highlightBuilder.preTags("<span style='color:#f47466'>");
        highlightBuilder.postTags("</span>");
        sourceBuilder.highlighter(highlightBuilder);
        searchRequest.source(sourceBuilder);
        SearchResponse response = null;
        try{
            response = client.search(searchRequest, RequestOptions.DEFAULT);
            org.elasticsearch.search.SearchHit[] hits = response.getHits().getHits();
            for (org.elasticsearch.search.SearchHit hit : hits) {
                //如果不做高亮，则可以直接转为json，然后转为对象
                //String value = hit.getSourceAsString();
                //ArticleSearchDTO dto = JSON.parseObject(value, ArticleSearchDTO.class);
                //解析高亮字段
                //获取当前命中的对象的高亮的字段
                Map<String, HighlightField> highlightFields = hit.getHighlightFields();
                HighlightField articleTitle = highlightFields.get("articleTitle");
                HighlightField articleContent = highlightFields.get("articleContent");
                String newArticleTitle = "";
                String newArticleContent = "";
                if (articleTitle != null){
                    //获取该高亮字段的高亮信息
                    Text[] fragments = articleTitle.getFragments();
                    //将前缀、关键词、后缀进行拼接
                    for (Text fragment : fragments) {
                        newArticleTitle += fragment;
                    }
                }
                if (articleContent != null){
                    //获取该高亮字段的高亮信息
                    Text[] fragments = articleContent.getFragments();
                    //将前缀、关键词、后缀进行拼接
                    for (Text fragment : fragments) {
                        newArticleContent += fragment;
                    }
                }
                Map<String, Object> sourceAsMap = hit.getSourceAsMap();
                //将高亮后的值替换掉旧值
                sourceAsMap.put("articleTitle",newArticleTitle);
                sourceAsMap.put("articleContent",newArticleContent);
                String json = JSONUtil.toJsonStr(sourceAsMap);
                ArticleSearchDTO searchDTO = JSONUtil.toBean(json,ArticleSearchDTO.class);
                searchDTOS.add(searchDTO);
            }
            return searchDTOS;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
