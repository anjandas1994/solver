package com.problem.solver.web.controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.problem.solver.dao.BaseDao;
import com.problem.solver.entity.Book;
import com.problem.solver.entity.BookVo;
import com.problem.solver.service.Service;





@Controller
public class WelcomeController {

	private static final Logger logger = LoggerFactory.getLogger(WelcomeController.class);

	public static Boolean insertFlag=false;
	
	@Autowired
	BaseDao baseDao;
	
	@Autowired
	Service titleService;
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String welcome(Model model) {
		if(!insertFlag)
			readAndInsert();
		insertFlag=true;
		List<BookVo> books = baseDao.findAll();
		model.addAttribute("book", books);
		model.addAttribute("msg", "<h1>Problem 1</h1><br/><br/><h2>After Insert get All Books and Reviews</h2>");
		model.addAttribute("review", baseDao.findAllReview());
		return "welcome";

	}
	
	@RequestMapping(value = "/search", method = RequestMethod.GET)
    public String greeting(@RequestParam(value="title", required=true, defaultValue="") String title, Model model) {
		if(!insertFlag)
			readAndInsert();
		insertFlag=true;
		if(title==null)
			return "welcome2";
		List<BookVo> books = baseDao.findAll();
		model.addAttribute("book", books);
		model.addAttribute("msg", "<h1>Problem 1</h1><br/><h2>After Insert get All Books and Reviews</h2>");
		model.addAttribute("review", baseDao.findAllReview());
		model.addAttribute("book2", new Gson().toJson(titleService.getAllBookByTitle(title)));
		model.addAttribute("msg2", "<h1>Problem 2</h1><br/><h2>Search By Title</h2>");
		return "welcome2";
    }
	
	private void readAndInsert() {
		String fileName = "H:\\Java Work\\solver\\test.json";
		try (Stream<String> stream = Files.lines(Paths.get(fileName))) {	
			StringBuilder build=new StringBuilder();
			stream.forEach(x->build.append(x));
			Gson gson=new Gson();
			List<Book> books=gson.fromJson(build.toString(), new TypeToken<List<Book>>() {}.getType());
			books.stream().forEach(System.out::println);
			baseDao.insertAll(books);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}