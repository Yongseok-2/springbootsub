package com.example.firstproject.service;

import com.example.firstproject.dto.ArticleForm;
import com.example.firstproject.entity.Article;
import com.example.firstproject.repository.ArticleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ArticleService {

    private final ArticleRepository articleRepository;


    public Article saveArticle(ArticleForm form) {
        Article article = form.toEntity();
        return articleRepository.save(article);
    }

    public Article getArticle(Long id) {
        Optional<Article> article = articleRepository.findById(id);
        return article.orElse(null);
    }
}
