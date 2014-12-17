package com.wehavescience.domain;

import lombok.Data;
import lombok.experimental.Accessors;
import org.mongodb.morphia.annotations.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

/**
 * @author Gabriel Francisco  <gabfssilva@gmail.com>
 */
@Entity("articles")
@Data
@Accessors(fluent = true)
public class Article extends AbstractEntity {
    private String title;
    @Embedded
    private Content content;
    @Embedded
    private Rate rate;
    @Reference
    private Author author;
    @Embedded
    private List<Tag> tags;
    @Embedded
    private List<Comment> comments;
    @Transient
    private List<Comment> approvedComments;
    private String resource;
    private Date date;

    public String preview() {
        int i = content().content().indexOf("<!--preview-->");

        if(i < 1){
            i = 300;
        }

        return content.content().substring(0, i);
    }

    public int commentsNumber() {
        return approvedComments() == null ? 0 : approvedComments().size();
    }

    @PostLoad
    public void loadTransientData() {
        approvedComments = fetchApprovedComments();
    }

    private List<Comment> fetchApprovedComments() {
        if (comments == null) {
            return null;
        }

        List<Comment> approvedComments = new LinkedList<>();

        for (Comment comment : comments) {
            if (comment.approved()) {
                approvedComments.add(comment);
            }
        }

        return approvedComments;
    }

    public String brazilianDateFormat() {
        if(date == null){
            date = new Date();
        }

        return new SimpleDateFormat("dd/MM/yyyy hh:mm").format(date);
    }
}
