package org.freecode.demo.model;

import java.util.Date;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.NumberFormat;

public class Book {
    private String title;
    private String isbn;
    private Integer pageCount;
    private Date publishedOn;
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	
	@Size(min=10, max=13) // validation by javax
	public String getIsbn() {
		return isbn;
	}
	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}
	
	@NotNull @Min(1) // @Positive specific to JDK version 8+, so use the same between compile and runtime
	public Integer getPageCount() {
		return pageCount;
	}
	public void setPageCount(Integer pageCount) {
		this.pageCount = pageCount;
	}
	
	@DateTimeFormat(pattern="yyyy-MM-dd")
	public Date getPublishedOn() {
		return publishedOn;
	}
	public void setPublishedOn(Date publishedOn) {
		this.publishedOn = publishedOn;
	}
	@Override
	public String toString() {
		return "Book [title=" + title + ", isbn=" + isbn + ", pageCount=" + pageCount + ", publishedOn=" + publishedOn
				+ "]";
	}
}
