package com.wehavescience.resources.utils;

import javax.inject.Named;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Gabriel Francisco  <gabfssilva@gmail.com>
 */
@Named
public class RedirectionRule {
    private Map<String, String> resources;

    public RedirectionRule(){
        resources = new HashMap<>();

        resources.put("uma-visao-geral-do-cdi-por-que-e-como-usar", "cdi-visao-geral");
    }

    public String resource(String path) {
        return resources.get(path);
    }
}
