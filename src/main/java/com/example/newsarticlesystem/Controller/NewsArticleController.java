package com.example.newsarticlesystem.Controller;

import com.example.newsarticlesystem.ApiResponse.ApiResponse;
import com.example.newsarticlesystem.Model.NewsArticle;
import com.example.newsarticlesystem.Service.NewsArticleService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Array;
import java.util.ArrayList;

@RestController
@RequestMapping("/api/v1/news-article-management-system")
@RequiredArgsConstructor
public class NewsArticleController {

    private final NewsArticleService newsArticleService  ;



    @GetMapping("/get")
    public ResponseEntity getNews(){
        return ResponseEntity.status(200).body(newsArticleService.getNewsArticles());
    }

    @PostMapping("/add")
    public ResponseEntity addArticle(@RequestBody @Valid NewsArticle newsArticle, Errors errors){

        if (errors.hasErrors()){
            return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());
        }

        boolean isAdded = newsArticleService.addNewsArticle(newsArticle);

        if (isAdded){
            return ResponseEntity.status(200).body("Article added");
        }else
            return ResponseEntity.status(400).body(new ApiResponse("Article already exits"));

    }

    @PutMapping("/update/{id}")
    public ResponseEntity UpdateArticle(@PathVariable String id, @RequestBody @Valid NewsArticle newsArticle, Errors errors){
        if (errors.hasErrors()){
            return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());
        }

        boolean isUpdated = newsArticleService.updateNewsArticle(id,newsArticle);

        if (isUpdated){
            return ResponseEntity.status(200).body("Article updated");
        }else
            return ResponseEntity.status(400).body(new ApiResponse("Article id not exits"));

    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteArticle(@PathVariable String id){

        boolean isDeleted = newsArticleService.deleteNewsArticle(id);

        if (isDeleted){
            return ResponseEntity.status(200).body(new ApiResponse("Article deleted"));
        }else
            return ResponseEntity.status(400).body(new ApiResponse("Article is not exits"));

    }

    @PutMapping("publishing/{id}")
    public ResponseEntity publishingArticle(@PathVariable String id){

        boolean isPublish= newsArticleService.publishNewsArticle(id);
        if (isPublish){
            return ResponseEntity.status(200).body(new ApiResponse("Article published"));
        }else
            return ResponseEntity.status(400).body(new ApiResponse("Article is not exits"));

    }

    @GetMapping("/get-published")
    public ResponseEntity getAllPublished(){

        ArrayList<NewsArticle> newsArticles = newsArticleService.getAllPublishNews();
        if (newsArticles != null){
            return ResponseEntity.status(200).body(newsArticles);
        }else {
            return ResponseEntity.status(400).body(new ApiResponse("There is not published Article"));

        }
    }

    @GetMapping("/get-article-by-category/{category}")
    public ResponseEntity getArticleByCategory(@PathVariable String category){

        ArrayList<NewsArticle> newsArticles = newsArticleService.getArticleByCategory(category);
        if (newsArticles != null){
            return ResponseEntity.status(200).body(newsArticles);
        }else {
            return ResponseEntity.status(400).body(new ApiResponse("There is not article under this category"));

        }
    }







}

