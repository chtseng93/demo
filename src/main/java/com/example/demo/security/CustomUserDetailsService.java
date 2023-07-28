package com.example.demo.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.demo.mapper.UserMapper;

@Service
public class CustomUserDetailsService implements UserDetailsService {

	@Autowired
	UserMapper userMapper;

	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		 com.example.demo.entity.User user =  userMapper.getUserByName(username);
		
		if(user == null) {
			 throw new UsernameNotFoundException(username + " not found");
		}
		

		UserDetails userDetails = User.builder()
                .username(user.getUserName())
        		// 密碼前面加上"{noop}"使用NoOpPasswordEncoder，也就是不對密碼進行任何格式的編碼。
                .password("{noop}" + user.getPassWord())
                .roles("user").build();
		
		
		return userDetails;
	}

}
