package com.wehavescience.context;

import javax.annotation.PostConstruct;
import javax.inject.Named;

import static spark.SparkBase.staticFileLocation;

/**
 * @author Gabriel Francisco - gabfssilva@gmail.com
 */
@Named
public class SparkHelper {

    @PostConstruct
    public void init(){
        staticFileLocation("webresources");
    }
}
