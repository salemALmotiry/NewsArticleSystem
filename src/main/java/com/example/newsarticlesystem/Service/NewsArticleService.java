package com.example.newsarticlesystem.Service;


import com.example.newsarticlesystem.Model.NewsArticle;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;

@Service
public class NewsArticleService {

    ArrayList<NewsArticle> newsArticles = new ArrayList<>();


    public ArrayList<NewsArticle> getNewsArticles(){

        return newsArticles;

    }


    public boolean addNewsArticle(NewsArticle newsArticle){
        for (int i = 0; i < newsArticles.size(); i++) {
            if (newsArticles.get(i).getId().equals(newsArticle.getId())){
                return false;

            }
        }
        newsArticles.add(newsArticle);
        return true;
    }


    public boolean updateNewsArticle(String id , NewsArticle newsArticle){

        for (int i = 0; i < newsArticles.size(); i++) {
            if (newsArticles.get(i).getId().equals(id)){
                newsArticles.set(i,newsArticle);
                return true;
            }
        }
        return false;

    }


    public boolean deleteNewsArticle(String id){

        for (int i = 0; i < newsArticles.size(); i++) {
            if (newsArticles.get(i).getId().equals(id)){
                newsArticles.remove(i);
                return true;
            }
        }
        return false;
    }


    public boolean publishNewsArticle(String id){

        for (NewsArticle article : newsArticles) {
            if (article.getId().equals(id)) {

                if (!article.isPublished()) {
                    article.setPublished(true);
                    article.setPublishDate(LocalDate.now());
                }

                return true;
            }
        }
        return false;
    }

    public ArrayList<NewsArticle> getAllPublishNews(){
        ArrayList<NewsArticle> tem = new ArrayList<>();

        for (NewsArticle article : newsArticles){
            if (article.isPublished()){
                tem.add(article);
            }
        }

        if (tem.isEmpty())
            return null;

        return tem;

    }

    public ArrayList<NewsArticle> getArticleByCategory(String category){
        ArrayList<NewsArticle> tem = new ArrayList<>();
        for (NewsArticle article : newsArticles){
            if (article.getCategory().equals(category) ){
                tem.add(article);
            }
        }

        if (tem.isEmpty())
            return null;

        return tem;

    }



}
