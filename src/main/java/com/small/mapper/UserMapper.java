package com.small.mapper;

import java.util.List;

import com.small.pojo.Users;

public interface UserMapper {
	public List<Users> select(Users user);
}
