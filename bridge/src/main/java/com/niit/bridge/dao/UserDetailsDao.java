package com.niit.bridge.dao;


import java.util.List;

import com.niit.bridge.model.UserDetails;

public interface UserDetailsDao {
	
	public boolean saveUser(UserDetails user);
	public boolean updateUser(UserDetails user);
	public boolean deleteUser(UserDetails user);
	public List<UserDetails> getAllUser();
	public UserDetails UserAuthentication(String userId, String userPassword);
	public UserDetails getUserByUserId(String id);



}
