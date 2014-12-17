package com.wehavescience.model;

import com.wehavescience.domain.Article;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * @author Gabriel Francisco  <gabfssilva@gmail.com>
 */
@Data
@Accessors(fluent = true)
public class Articles {
    private List<Article> articles;

    public String thought(){
        return "If you can't explain it simply, then you don't understand it well enough";
    }
}
