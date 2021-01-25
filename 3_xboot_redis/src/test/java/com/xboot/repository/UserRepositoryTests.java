package com.xboot.repository;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.xboot.repository.model.User;

import javax.annotation.Resource;
import java.text.DateFormat;
import java.util.Date;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserRepositoryTests {
	@Resource
	private UserRepository userRepository;


	@Test
	public void testSave() throws Exception {
		Date date = new Date();
//		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG);
//		String formattedDate = dateFormat.format(date);

		userRepository.save(new User("aa", "123456",  date));
		userRepository.save(new User("bb", "123456", date));
//		userTest2Repository.save(new User("cc", "cc123456","cc@126.com", "cc",  formattedDate));
	}


//	@Test
//	public void testDelete() throws Exception {
//		userTest1Repository.deleteAll();
//		userTest2Repository.deleteAll();
//	}

//	@Test
//	public void testBaseQuery() {
//		Date date = new Date();
//		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG);
//		String formattedDate = dateFormat.format(date);
//		User user=new User("ff", "ff123456","ff@126.com", "ff",  formattedDate);
//		userTest1Repository.findAll();
//		userTest2Repository.findById(3l);
//		userTest2Repository.save(user);
//		user.setId(2l);
//		userTest1Repository.delete(user);
//		userTest1Repository.count();
//		userTest2Repository.findById(3l);
//	}


}