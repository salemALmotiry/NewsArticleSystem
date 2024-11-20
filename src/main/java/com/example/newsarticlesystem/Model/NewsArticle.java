package com.example.newsarticlesystem.Model;

import jakarta.validation.constraints.AssertFalse;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.hibernate.validator.constraints.URL;

import java.time.LocalDate;


@Data
@AllArgsConstructor
public class NewsArticle {

    @NotEmpty(message = "id cannot be empty")
    @Size(max = 100, message = "max length is 100")
    private String id;

    @NotEmpty(message = "author cannot be empty")
    @Size(min= 4, max = 20, message = "author must be between 4 and 20")
    private String author;

    @NotEmpty(message = "content cannot be empty")
    @Size(min= 200, message = "content must be more than 200 characters")
    private String content;

    @NotEmpty(message = "category cannot be empty")
    @Pattern(regexp = "^(politics|sports|technology)$", message = "category must be politics, sports or technology")
    private String category;

    @NotEmpty(message = "imageUrl cannot be empty")
    private String imageUrl;

    @AssertFalse( message = "isPublished must be false")
    private boolean isPublished;

    @NotNull(message = "publish date cannot be empty")

    private LocalDate publishDate;

}
