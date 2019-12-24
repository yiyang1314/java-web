package com.renting.service;

import com.renting.entity.Result;
import com.renting.entity.Users;

public interface UsersService extends BaseService<Users> {
	/**
	 * 删除
	 * @param T
	 * @return
	 */

	public Result login(String username,String password);
}
