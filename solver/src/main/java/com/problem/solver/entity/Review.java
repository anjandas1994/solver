package com.problem.solver.entity;

public class Review {
	String reviewerName;
	String content;
	float rating;
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
		return "Review [reviewerName=" + reviewerName + ", content=" + content + ", rating=" + rating + "]";
	}
	
	
}
