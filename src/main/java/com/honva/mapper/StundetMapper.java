package com.honva.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import com.honva.entity.Student;

public interface StundetMapper {
	@Select("select * from student")
	@Results({
		@Result(property="userName",column="user_name"),
		@Result(property="userAge",column="user_age")
	})
	List<Student> getAll();
}
