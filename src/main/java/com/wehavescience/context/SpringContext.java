package com.wehavescience.context;

import com.mongodb.MongoClient;
import com.wehavescience.domain.Author;
import com.wehavescience.domain.Image;
import com.wehavescience.repositories.Repository;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

import java.net.UnknownHostException;

/**
 * @author Gabriel Francisco - gabfssilva@gmail.com
 */
@Configuration
@ComponentScan("com.wehavescience")
@DependsOn({
        "sparkHelper"
})
public class SpringContext {
    @Bean
    public Datastore datastore() throws UnknownHostException {
        return new Morphia().createDatastore(new MongoClient(), "blog");
    }

    @Bean
    public Repository<Image> imageRepository() throws UnknownHostException {
        return new Repository<>(datastore(), Image.class);
    }

    @Bean
    public Repository<Author> authorRepository() throws UnknownHostException {
        return new Repository<>(datastore(), Author.class);
    }
}
