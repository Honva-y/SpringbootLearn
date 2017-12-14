package com.honva.dao;

import java.util.List;

import com.honva.entity.Student;


public interface StundetDao {
//	@Select("select * from student")
//	@Results({
//		@Result(property="userName",column="user_name"),
//		@Result(property="userAge",column="user_age")
//	})
//	List<Student> getAll();
	
	List<Student> getAll();
}
