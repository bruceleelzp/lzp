package com.lzp.service;

import java.sql.SQLException;

import com.lzp.dao.UserDao;
import com.lzp.pojo.User;

public class UserService {

	public boolean regist(User user) {
		UserDao dao = new UserDao();
		int row = 0;
		try {
			row = dao.regist(user);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return row>0?true:false;
	}

	/*
	 * 校验用户名是否存在
	 */
	public boolean checkUsername(String username)  {
		UserDao dao = new UserDao();
		Long isExist = 0L;
		try {
			 isExist = dao.checkUsername(username);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return isExist>0?true:false;
	}



	

}
