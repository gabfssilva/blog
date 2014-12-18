package com.wehavescience.resources;

import com.wehavescience.domain.Article;
import com.wehavescience.domain.Author;
import com.wehavescience.domain.Comment;
import com.wehavescience.model.Articles;
import com.wehavescience.repositories.ArticleRepository;
import com.wehavescience.repositories.Repository;
import com.wehavescience.resources.exceptions.ResourceNotFoundException;
import com.wehavescience.utils.JsonTransformer;
import com.wehavescience.utils.JsonUtils;
import spark.ModelAndView;
import spark.Route;
import spark.template.mustache.MustacheTemplateEngine;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.ArrayList;
import java.util.Date;


import static spark.Spark.*;

/**
 * @author Gabriel Francisco  <gabfssilva@gmail.com>
 */
@Named
public class ArticleResource {
    @Inject
    private ArticleRepository articleRepository;

    @Inject
    private Repository<Author> authorRepository;

    @Inject
    private JsonTransformer jsonTransformer;

    @PostConstruct
    public void init() {
        post("/rs/articles", (req, resp) -> {
            Article article = JsonUtils.asObject(req.body(), Article.class);

            Author author = authorRepository.findOneBy("authorName", article.author().authorName());

            if (author == null) {
                author = article.author();
                authorRepository.save(author);
            }

            article.author(author);
            articleRepository.save(article);
            return articleRepository.findOneBy("title", article.title());
        }, jsonTransformer);

        get("/rs/articles", (req, resp) -> new ModelAndView(new Articles().articles(articleRepository.findAnythingThatContains(req.queryParams("query"))), "articles.mustache"), new MustacheTemplateEngine());

        get("/rs/articles/tags/:tag", (req, resp) -> new ModelAndView(new Articles().articles(articleRepository.findAllByTag(req.params(":tag"))), "articles.mustache"), new MustacheTemplateEngine());

        put("/rs/articles/:resource/comments/:authorEmail/approve", (req, resp) -> {
            Article article = articleRepository.findByResource(req.params(":resource"));

            if (article == null) {
                throw new ResourceNotFoundException();
            }

            for (Comment comment : article.comments()) {
                if (comment.email().equals(req.params(":authorEmail"))) {
                    comment.approved(true);
                }
            }

            articleRepository.save(article);
            notifySubscribers(article);
            return article;
        }, jsonTransformer);


        put("/rs/articles/:resource/comments", (req, resp) -> {
            Article article = articleRepository.findByResource(req.params(":resource"));

            if (article == null) {
                throw new ResourceNotFoundException();
            }

            Comment comment = new Comment();

            comment
                    .approved(false)
                    .author(req.params("author"))
                    .email(req.params("email"))
                    .content(req.params("content"))
                    .date(new Date());

            if (article.comments() == null) {
                article.comments(new ArrayList<>());
            }

            article.comments().add(comment);
            articleRepository.save(article);

            resp.redirect("/rs/articles/".concat(article.resource()));
            return null;
        }, jsonTransformer);


        get("/rs/articles/:resource", (req, resp) -> {
            Article article = articleRepository.findByResource(req.params(":resource"));

            if (article == null) {
                throw new ResourceNotFoundException();
            }

            return new ModelAndView(article, "article.mustache");
        }, new MustacheTemplateEngine());
    }


    private void notifySubscribers(Article article) {
    }
}
