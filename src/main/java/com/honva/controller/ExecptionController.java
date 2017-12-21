package com.honva.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ExecptionController {
	
	@RequestMapping("/exception")
	public void globalException() throws Exception{
		throw new Exception();
	}
	@RequestMapping("/aaa")
	public String test(){
		return "a";
	}
}
