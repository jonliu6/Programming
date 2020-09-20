package org.freecode.demo.model;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;

public class Article implements Serializable {
    // @Size for string lengths, while @Min, @Max for number values
    @NotEmpty
    @Size(min = 8, message = "Minimum title must have 8 characters.")
    private String title;
    @Size(min = 8, message = "Content must have at least 8 characters.")
    private String content;
    private Date createAt;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Date createAt) {
        this.createAt = createAt;
    }
}
