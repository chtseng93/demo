package com.example.demo.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;


import com.example.demo.entity.User;


@Mapper
public interface UserMapper {
	
	@Results({
		@Result(property = "userName", column = "user_name"),
		@Result(property = "passWord", column = "user_password"),
	})
	@Select("select * from user_profile where user_name = #{userName}")
	User getUserByName(String userName);
	
	
	
	@Insert("insert into user_profile(user_name,user_password) values(#{userName},#{passWord})")
	void insertUser(User user);

}
