package com.lauz.blogcloud.service.impl;

import cn.hutool.core.util.StrUtil;
import com.lauz.blogcloud.dao.EsArticleDao;
import com.lauz.blogcloud.dto.ArticleSearchDTO;
import com.lauz.blogcloud.repository.EsArticleRepository;
import com.lauz.blogcloud.service.EsArticleSearchService;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.stereotype.Service;

import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class EsArticleSearchServiceImpl implements EsArticleSearchService {

    @Autowired
    private ElasticsearchRestTemplate elasticsearchRestTemplate;
    @Autowired
    private EsArticleRepository esArticleRepository;
    @Autowired
    private EsArticleDao esArticleDao;


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
        return null;
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
        searchHits.stream().map(SearchHit::getContent).collect(Collectors.toList());
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
        return null;
    }


    private List<ArticleSearchDTO> dealResult(SearchHits<ArticleSearchDTO> searchHits){
        searchHits.getSearchHits().stream().forEach(hits->{
            hits.getHighlightFields().get("articleTitle");
        });
        return null;
    }
}
