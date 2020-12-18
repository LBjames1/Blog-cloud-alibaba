package com.lauz.blogcloud.repository;

import com.lauz.blogcloud.dto.ArticleSearchDTO;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface EsArticleRepository extends ElasticsearchRepository<ArticleSearchDTO,Long> {
}
