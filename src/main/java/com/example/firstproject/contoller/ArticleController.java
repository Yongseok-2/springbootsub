package com.example.firstproject.contoller;

import com.example.firstproject.dto.ArticleForm;
import com.example.firstproject.entity.Article;
import com.example.firstproject.repository.ArticleRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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

    @GetMapping("/articles/main")
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

    @PostMapping("article/update")
    public String update(ArticleForm form) {
        log.info(form.toString());
        Article articleEntity = form.toEntity();
        log.info(articleEntity.toString());

        Article target = articleRepository.findById(articleEntity.getId()).orElse(null);

        if(target != null) {
            articleRepository.save(articleEntity);
        }

        return "redirect:/article/" + articleEntity.getId();
    }

    @GetMapping("/articles/{id}/delete")
    public String delete(@PathVariable Long id, RedirectAttributes attributes) {

        Article target = articleRepository.findById(id).orElse(null);

        if(target != null) {
            articleRepository.delete(target);
            attributes.addFlashAttribute("msg","삭제되었습니다");
        }
        return "redirect:/articles/main";
    }

}
