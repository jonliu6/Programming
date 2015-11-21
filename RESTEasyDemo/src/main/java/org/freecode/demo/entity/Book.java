package org.freecode.demo.entity;

import java.io.Serializable;
import java.util.Date;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Book implements Serializable {

    public String isbn;
    public String title;
    public Date publishedOn;
    public Integer pageNo;
    
    public Book() {
        
    }
    
    public Book(String isbn, String title, Date publishedOn, Integer pageNo) {
        this.isbn = isbn;
        this.title = title;
        this.publishedOn = publishedOn;
        this.pageNo = pageNo;
    }
    
    @XmlElement
    public String getIsbn() {
        return isbn;
    }
    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }
    
    @XmlElement
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    
    @XmlAttribute
    public Date getPublishedOn() {
        return publishedOn;
    }
    public void setPublishedOn(Date publishedOn) {
        this.publishedOn = publishedOn;
    }
    
    @XmlAttribute
    public Integer getPageNo() {
        return pageNo;
    }
    public void setPageNo(Integer pageNo) {
        this.pageNo = pageNo;
    }
    
}
