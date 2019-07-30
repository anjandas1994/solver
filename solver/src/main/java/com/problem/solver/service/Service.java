package com.problem.solver.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.problem.solver.dao.BaseDao;
import com.problem.solver.entity.Book;

@Component
public class Service {
	
	@Autowired
	BaseDao baseDao;
	
	public List<Book> getAllBookByTitle(String title){
		return baseDao.findByTitle(title);
	}

}
