package com.wehavescience.resources.secure;

import com.wehavescience.repositories.TokenRepository;
import spark.Request;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Named;

import static spark.Spark.*;

/**
 * @author Gabriel Francisco - gabfssilva@gmail.com
 */
@Named
public class SecureFilter {

    @Inject
    private TokenRepository tokenRepository;

    @PostConstruct
    public void init(){
        before("/rs/articles", (request, response) -> {
            String method = request.requestMethod();

            if(method.equals("GET")){
                return;
            }

            auth(request);
        });
    }

    private void auth(Request request) {
        String token = request.headers("user-token");
        String username = request.headers("username");

        if (tokenRepository.isTokenValid(username, token)) {
            return;
        }

        halt(401, "You are not allowed to call this resource");
    }
}
