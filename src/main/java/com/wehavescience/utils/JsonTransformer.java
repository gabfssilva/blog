package com.wehavescience.utils;

import spark.ResponseTransformer;

import javax.inject.Named;

import static com.wehavescience.utils.JsonUtils.asJson;

/**
 * @author Gabriel Francisco - gabfssilva@gmail.com
 */
@Named
public class JsonTransformer implements ResponseTransformer {
    @Override
    public String render(Object model) throws Exception {
        return asJson(model);
    }
}
