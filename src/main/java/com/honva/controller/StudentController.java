package com.honva.controller;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.honva.dao.StundetDao;
import com.honva.entity.Student;

@RestController
public class StudentController {
	@Autowired
	private StundetDao mapper;
	
	@RequestMapping("/getAll")
	public List<Student> getUser(){
		List<Student> list = mapper.getAll();
		for (Student student : list) {
			System.out.print("name:"+student.getUserName());
			System.out.println("age:"+student.getUserAge());
		}
		return list;
	}
}
