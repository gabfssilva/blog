package com.wehavescience.resources;

import com.wehavescience.resources.utils.RedirectionRule;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Named;

import static spark.Spark.*;

/**
 * @author Gabriel Francisco  <gabfssilva@gmail.com>
 */
@Named
public class RedirectResource {
    @Inject
    private RedirectionRule redirectRule;

//    @PostConstruct
    public void init(){
        get("/*/*/*/:resourceName", (req, resp) -> {
            resp.redirect("/rs/articles/".concat(redirectRule.resource(req.params(":resourceName"))));
            return null;
        });
    }
}
