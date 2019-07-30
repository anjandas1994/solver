package com.problem.solver.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.problem.solver.entity.Book;
import com.problem.solver.entity.BookVo;
import com.problem.solver.entity.Review;
import com.problem.solver.entity.ReviewVo;


@Repository
public class BaseDao {

	public static Boolean insertFlag=false;
	
	public static 
	NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	
	@Autowired
	public void setNamedParameterJdbcTemplate(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
		this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
	}
	
	
	  public List<Book> findByTitle(String name) {
	  
	  Map<String, Object> params = new HashMap<String, Object>();
	  params.put("title", "%"+name.toLowerCase()+"%");
	  
	  String sql = "SELECT * FROM book WHERE LOWER(title) like :title";
	  
	  List<Book> result = namedParameterJdbcTemplate.query( sql, params, new
			  BMapper()); 
	  result=result.stream().map(x->{
		  List<Review> data = findReviewByIsbn(x.getIsbn()).stream().map(y->{
			  Review tmp=new Review();
			  tmp.setContent(y.getContent());
			  tmp.setRating(y.getRating());
			  tmp.setReviewerName(y.getReviewerName());
			  return tmp;
		  }).collect(Collectors.toList());
		  x.setReviews(data);
		  return x;
	  }).collect(Collectors.toList());
	  return result;
	  }
	  public List<ReviewVo> findReviewByIsbn(String name) {
		  
		  Map<String, Object> params = new HashMap<String, Object>();
		  params.put("isbn", name);
		  
		  String sql = "SELECT * FROM review WHERE isbn=:isbn";
		  
		  List<ReviewVo> result = namedParameterJdbcTemplate.query(sql, params, new ReviewMapper());
	      return result;
		  }
	 
	public void insertAll(List<Book> books) {
		String query="insert into book (title,author,publishedDate,isbn) values(:title,:author,:publishedDate,:isbn)";
		books.parallelStream().forEach(x->{
			Map<String, Object> params = new HashMap<String, Object>();
	        params.put("title", x.getTitle());
	        params.put("author", x.getAuthor());
	        params.put("publishedDate", x.getPublishedDate());
	        params.put("isbn", x.getIsbn());
	        namedParameterJdbcTemplate.update(query,params);
		});
		String queryReview="insert into review (reviewerName,content,rating,isbn) values(:reviewerName,:content,:rating,:isbn)";
		books.parallelStream().forEach(x->{
			List<Review> data=x.getReviews();
			for(Review a: data) {
			Map<String, Object> params = new HashMap<String, Object>();
	        params.put("reviewerName", a.getReviewerName());
	        params.put("content", a.getContent());
	        params.put("rating", a.getRating());
	        params.put("isbn", x.getIsbn());
	        namedParameterJdbcTemplate.update(queryReview,params);
			}
		});
		
	}
	public List<BookVo> findAll() {
		
		Map<String, Object> params = new HashMap<String, Object>();	
		String sql = "SELECT * FROM book";
        List<BookVo> result = namedParameterJdbcTemplate.query(sql, params, new BookMapper());
        return result;
        
	}
	public List<ReviewVo> findAllReview() {
		
		Map<String, Object> params = new HashMap<String, Object>();	
		String sql = "SELECT * FROM review";
        List<ReviewVo> result = namedParameterJdbcTemplate.query(sql, params, new ReviewMapper());
        return result;
        
	}

	private static final class BMapper implements RowMapper<Book> {

		public Book mapRow(ResultSet rs, int rowNum) throws SQLException {
			Book book = new Book();
			book.setIsbn(rs.getString("isbn"));
			book.setAuthor(rs.getString("author"));
			book.setTitle(rs.getString("title"));
			book.setPublishedDate(rs.getString("publishedDate"));

			return book;
		}
	}
	private static final class BookMapper implements RowMapper<BookVo> {

		public BookVo mapRow(ResultSet rs, int rowNum) throws SQLException {
			BookVo book = new BookVo();
			book.setIsbn(rs.getString("isbn"));
			book.setAuthor(rs.getString("author"));
			book.setTitle(rs.getString("title"));
			book.setPublishedDate(rs.getString("publishedDate"));

			return book;
		}
	}
	private static final class ReviewMapper implements RowMapper<ReviewVo> {

		public ReviewVo mapRow(ResultSet rs, int rowNum) throws SQLException {
			ReviewVo review = new ReviewVo();
			review.setIsbn(rs.getString("isbn"));
			review.setContent(rs.getString("content"));
			review.setRating(rs.getFloat("rating"));
			review.setReviewerName(rs.getString("reviewerName"));
			return review;
		}
	}
	
	

}
