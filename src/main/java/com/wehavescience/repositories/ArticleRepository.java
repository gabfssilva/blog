package com.wehavescience.repositories;

import com.wehavescience.domain.Article;
import org.mongodb.morphia.Datastore;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.LinkedList;
import java.util.List;

/**
 * @author Gabriel Francisco  <gabfssilva@gmail.com>
 */
@Named
public class ArticleRepository extends Repository<Article> {

    @Inject
    public ArticleRepository(Datastore datastore) {
        super(datastore, Article.class);
    }

    public List<Article> findLastestOrderedByDate() {
        return findAllOrderBy(9, "date");
    }

    public Article findByResource(String resource) {
        return findOneBy("resource", resource);
    }

    public List<Article> findAllByTag(String tag) {
        List<Article> articles = new LinkedList<>();
        find().field("tags").contains(tag).forEach(article -> articles.add(article));
        return articles;
    }

    public List<Article> findAnythingThatContains(String value) {
        List<Article> articles = new LinkedList<>();
        find().field("content.content").contains(value).forEach(article -> articles.add(article));
        return articles;
    }
}
