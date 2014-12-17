package com.wehavescience.repositories;

import com.wehavescience.domain.Token;
import org.mongodb.morphia.Datastore;

import javax.inject.Inject;
import javax.inject.Named;

/**
 * @author Gabriel Francisco  <gabfssilva@gmail.com>
 */
@Named
public class TokenRepository extends Repository<Token> {
    @Inject
    public TokenRepository(Datastore datastore) {
        super(datastore, Token.class);
    }

    public boolean isTokenValid(String username, String token){
        try{
            return find().field("username").equal(username).field("token").equal(token).get() != null;
        } catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }
}
