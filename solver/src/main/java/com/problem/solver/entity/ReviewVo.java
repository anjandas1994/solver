package com.problem.solver.entity;

public class ReviewVo {
	String reviewerName;
	String content;
	float rating;
	String isbn;
	
	public String getIsbn() {
		return isbn;
	}
	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}
	public String getReviewerName() {
		return reviewerName;
	}
	public void setReviewerName(String reviewerName) {
		this.reviewerName = reviewerName;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public float getRating() {
		return rating;
	}
	public void setRating(float rating) {
		this.rating = rating;
	}
	@Override
	public String toString() {
		return "ReviewVo [reviewerName=" + reviewerName + ", content=" + content + ", rating=" + rating + ", isbn="
				+ isbn + "]";
	}
	
	
	
}
