package com.example.firstproject.dto;

import com.example.firstproject.entity.Article;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
@Setter
public class ArticleForm {
    private String title;
    private String content;

    public Article toEntity() {
        return new Article(
                null, // id는 null로 설정 (자동 생성)
                this.title,
                this.content
        );
    }
}
