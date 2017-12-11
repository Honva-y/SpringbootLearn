package com.honva.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.honva.common.MyProperty;
import com.honva.entity.Person;

@Controller
public class HelloController {
	
	@Autowired
	MyProperty property;
	
	
	@RequestMapping("/hello")
	public String hello(){
		return "hello!!!!!!!!!!";
	}
	/**
	 *  添加@RestController注解，默认返回json数据
	 *  @RestController = @RequestMapping + @ResponseBody
	 * */
	@RequestMapping("/getPerson")
	@ResponseBody
	public Person getPerson(){
		Person p = new Person("Honva",10,"Man"); 
		System.out.println("getTitle:"+property.getTitle());
		System.out.println("getDescrpit:"+property.getDescrpit());
		return p;
	}
}
