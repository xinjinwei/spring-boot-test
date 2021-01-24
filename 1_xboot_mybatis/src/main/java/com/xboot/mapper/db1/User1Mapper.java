package com.xboot.mapper.db1;



import java.util.List;

import com.xboot.model.User;



public interface User1Mapper {
	
	List<User> getAll();
	
	User getOne(Long id);

	void insert(User user);

	void update(User user);

	void delete(Long id);

}