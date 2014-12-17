package com.wehavescience.resources;

import com.wehavescience.model.Articles;
import com.wehavescience.repositories.ArticleRepository;
import spark.ModelAndView;
import spark.template.mustache.MustacheTemplateEngine;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Named;

import static spark.Spark.*;


/**
 * @author Gabriel Francisco  <gabfssilva@gmail.com>
 */
@Named
public class HomeResource {
    @Inject
    private ArticleRepository articleRepository;

    @PostConstruct
    public void init(){
        get("/rs/home", (req, resp) -> new ModelAndView(new Articles().articles(articleRepository.findLastestOrderedByDate()), "index.mustache"), new MustacheTemplateEngine());
    }
}
