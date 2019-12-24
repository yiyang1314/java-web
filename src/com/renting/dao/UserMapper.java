package com.renting.dao;

import java.util.Map;

import com.renting.entity.Users;

public interface UserMapper extends BaseMapper<Users> {
	Users login(Map<String,String> map);
}
