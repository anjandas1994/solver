package com.problem.solver.entity;

import java.util.List;



public class Book {
	String title;
	String author;
	String publishedDate;
	String isbn;
	List<Review> reviews;
	
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
	public List<Review> getReviews() {
		return reviews;
	}
	public void setReviews(List<Review> reviews) {
		this.reviews = reviews;
	}
	@Override
	public String toString() {
		return "Book [title=" + title + ", author=" + author + ", publishedDate=" + publishedDate + ", isbn=" + isbn
				+ ", reviews=" + reviews + "]";
	}
	
	
}
