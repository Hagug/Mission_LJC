/*package com.example.myboard;

import com.example.myboard.entity.Article;
import com.example.myboard.repo.ArticleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class DemoController {
    private final ArticleRepository repository;

    @GetMapping("/test/{id}")
    public String test(
            @PathVariable("id")
            Long id
    ) {
        // CREATE
        // 1. 새로운 ArticleEntity 인스턴스 생성
        Article article = new Article();
        // 2. 인스턴스에 저장하고싶은 데이터 넣기
        article.setTopic("Free");
        article.setTitle("Test");
        article.setContent("Lorem Ipsum");
        article.setPassword("1234");

        // 3. repository의 save 메서드를 이용해서 저장
        repository.save(article);

        // READ ALL
        List<Article> articles = repository.findAll();
        for (Article entity : articles) {
            System.out.println(entity.toString());
        }

        // READ ONE
        Optional<Article> optionalArticle = repository.findById(4L);
        if (optionalArticle.isPresent()) {
            System.out.println("found 4L");
            System.out.println(optionalArticle.get());
        }

        // UPDATE
        // 어떤 Article이 있고, title을 바꾼다.
        Optional<Article> targetOptional = repository.findById(id);
        if (targetOptional.isPresent()) {
            System.out.println("target found");
            Article target = targetOptional.get();
            target.setTitle("Updated Title");
            repository.save(target);
        }

        // DELETE
        targetOptional = repository.findById(id);
        if (targetOptional.isPresent()) {
            System.out.println("delete target found");
            Article target = targetOptional.get();
            repository.delete(target);
        }
        //repository.deleteById(id);

        return "done";
    }
}*/
