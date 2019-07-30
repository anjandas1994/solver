package com.problem.solver.entity;

import java.util.List;



public class BookVo {
	String title;
	String author;
	String publishedDate;
	String isbn;
	
	public String getIsbn() {
		return isbn;
	}
	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getPublishedDate() {
		return publishedDate;
	}
	public void setPublishedDate(String publishedDate) {
		this.publishedDate = publishedDate;
	}
	@Override
	public String toString() {
		return "Book [title=" + title + ", author=" + author + ", publishedDate=" + publishedDate + ", isbn=" + isbn
				+ "]";
	}
	
	
	
	
}
