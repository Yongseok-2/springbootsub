package com.example.firstproject.dto;

import com.example.firstproject.entity.Article;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
@Setter
public class ArticleForm {
    private Long id;
    private String title;
    private String content;

    public Article toEntity() {
        return new Article(
                this.id,
                this.title,
                this.content
        );
    }
}
