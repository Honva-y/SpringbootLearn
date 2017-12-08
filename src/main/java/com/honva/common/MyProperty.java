package com.honva.common;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties
public class MyProperty {
	@Value("${com.honva.title}")
	private String title;
	@Value("${com.honva.descript}")
	private String descrpit;
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescrpit() {
		return descrpit;
	}
	public void setDescrpit(String descrpit) {
		this.descrpit = descrpit;
	}
	
}
