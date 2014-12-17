package com.wehavescience.resources.utils;

import com.wehavescience.resources.exceptions.RestfulException;

import javax.annotation.PostConstruct;
import javax.inject.Named;

import static spark.Spark.*;

/**
 * @author Gabriel Francisco - gabfssilva@gmail.com
 */
@Named
public class SparkResponseMapper {

    @PostConstruct
    public void init(){
        exception(RestfulException.class, (e, req, resp) -> {
            RestfulException exception = (RestfulException) e;
            resp.body(exception.getMessage());
            resp.status(exception.getStatusCode());
        });
    }
}
