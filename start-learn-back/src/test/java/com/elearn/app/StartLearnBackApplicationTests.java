package com.elearn.app;

import com.elearn.app.config.security.JwtUtil;
import com.elearn.app.repositories.CategoryRepo;
import com.elearn.app.repositories.CourseRepo;
import com.elearn.app.services.CategoryService;
import com.elearn.app.services.CourseService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
class StartLearnBackApplicationTests {


	@Autowired
	private CategoryService categoryService;

	@Test
	void contextLoads()
	{
		
	}

	@Test
	public void testCategoryCourseRelation(){


		categoryService.addCourseToCategory("f8b7fe6d-d07f-4963-a543-a630a567d8b9","98e97d68-db26-4b08-8a6d-e9b8d8470100");

	}

	@Autowired
	private CategoryRepo repo;
	@Autowired
	private CourseRepo courseRepo;
	@Test
	@Transactional
	public void testRelation(){

		int size = repo.findById("f8b7fe6d-d07f-4963-a543-a630a567d8b9").get().getCourses().size();
		System.out.println(size);

		int size1 = courseRepo.findById("1fe72eb7-173c-42df-9ee3-afee75ae68b4").get().getCategoryList().size();
		System.out.println(size1);
	}

	@Autowired
	private JwtUtil util;
	@Test
	public void testJwt(){


		System.out.println("testing jwt");

		String token = util.generateToken("Aayush Sharma");
		System.out.println(token);

		System.out.println(util.validateToken(token,"Aayush Sharma"));

		System.out.println(util.extractUsername(token));


	}

}
