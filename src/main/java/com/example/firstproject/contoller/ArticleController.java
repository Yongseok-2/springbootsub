package com.example.firstproject.contoller;

import com.example.firstproject.dto.ArticleForm;
import com.example.firstproject.entity.Article;
import com.example.firstproject.repository.ArticleRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@Slf4j
@Controller
@RequiredArgsConstructor
public class ArticleController {

    private final ArticleRepository articleRepository;

    @GetMapping("/articles/new")
    public String newArticleForm() {
        return "articles/new";
    }

    @PostMapping("/articles/create")
    public String createArticle(ArticleForm form) {
        log.info(form.toString());
        Article article = form.toEntity();
        Article saved = articleRepository.save(article);
        Long id = saved.getId();
        return "redirect:/article/" + id;
    }

    @GetMapping("/article/{id}")
    public String viewArticle(@PathVariable Long id, Model model) {
        log.info("id : " + id);

        Article articleEntity = articleRepository.findById(id).orElse(null);
        log.info(articleEntity.toString());

        model.addAttribute("article", articleEntity);
        return "articles/viewArticle";
    }

    @GetMapping("/articles/index")
    public String index(Model model) {
        ArrayList<Article> articleList = (ArrayList<Article>)articleRepository.findAll();

        model.addAttribute("articleList", articleList);
        return "articles/index";
    }

    @GetMapping("/articles/{id}/edit")
    public String editForm(@PathVariable Long id, Model model) {
        Article articleEntity = articleRepository.findById(id).orElse(null);
        model.addAttribute("article", articleEntity);
        return "articles/edit";
    }

    @PatchMapping("article/edit")
    public String edit(@RequestBody ArticleForm form) {

        return "";
    }
}
