package org.freecode.demo.entity;

import java.io.Serializable;
import java.util.Date;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Book implements Serializable {

    private String isbn;
    private String title;
    private Date publishedOn;
    private Integer pageNo;
    
    public Book() {
        
    }
    
    public Book(String isbn, String title, Date publishedOn, Integer pageNo) {
        this.isbn = isbn;
        this.title = title;
        this.publishedOn = publishedOn;
        this.pageNo = pageNo;
    }
    
    @XmlElement(name="isbn")
    public String getIsbn() {
        return isbn;
    }
    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }
    
    @XmlElement(name="title")
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    
    @XmlAttribute(name="publishedOn")
    public Date getPublishedOn() {
        return publishedOn;
    }
    public void setPublishedOn(Date publishedOn) {
        this.publishedOn = publishedOn;
    }
    
    @XmlAttribute(name="pageNo")
    public Integer getPageNo() {
        return pageNo;
    }
    public void setPageNo(Integer pageNo) {
        this.pageNo = pageNo;
    }
    
}
