package com.wehavescience.resources;

import com.wehavescience.domain.Image;
import com.wehavescience.repositories.Repository;
import com.wehavescience.resources.exceptions.ResourceNotFoundException;
import org.bson.types.ObjectId;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletResponse;

import java.io.OutputStream;

import static spark.Spark.get;


/**
 * @author Gabriel Francisco  <gabfssilva@gmail.com>
 */
@Named
public class ImageResource {
    @Inject
    private Repository<Image> repository;

    @PostConstruct
    public void init(){
        get("/rs/images/:id", (req, resp) ->{
            Image image = repository.findById(new ObjectId(req.params(":id")));

            if(image == null){
                throw new ResourceNotFoundException();
            }

            resp.type(image.contentType());

            HttpServletResponse httpServletResponse = resp.raw();

            OutputStream out = httpServletResponse.getOutputStream();
            out.write(image.image()); // Passando o array de bytes
            out.close();

            return null;
        });

    }
}
