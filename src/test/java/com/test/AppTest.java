package com.test;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.honva.Application;
import com.honva.controller.HelloController;
import com.honva.dao.StundetDao;
import com.honva.entity.Student;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class AppTest {
	private MockMvc mvc;

	@Before
	public void setUp() {
		try {
			mvc = MockMvcBuilders.standaloneSetup(new HelloController()).build();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void getHello() throws Exception {
		mvc.perform(MockMvcRequestBuilders.get("/hello").accept(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isOk()).andDo(MockMvcResultHandlers.print()).andReturn();
	}
/**-----------------------------------------------**/
	/**测试连接redis是否正确**/
	@Autowired
	private StringRedisTemplate template;

	@Test
	public void testRedis() throws Exception {
		String string = template.opsForValue().get("string");
		System.out.println(string);

	}
/**-----------------------------------------------**/
	@Autowired
	private StundetDao mapper;
	
	@Test
	public void getUser(){
		List<Student> list = mapper.getAll();
		for (Student student : list) {
			System.out.print("name:"+student.getUserName());
			System.out.println("age:"+student.getUserAge());
		}
	}
	
}
