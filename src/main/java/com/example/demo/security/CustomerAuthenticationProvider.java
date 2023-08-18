package com.example.demo.security;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.example.demo.mapper.UserMapper;

@Component
public class CustomerAuthenticationProvider implements AuthenticationProvider {

	@Autowired
	private UserMapper userMapper;
	
	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		String userId = authentication.getName();
		Optional<String> pwd = Optional.of(authentication.getCredentials().toString());
		String password = pwd.get();
		
		
		 com.example.demo.entity.User user =  userMapper.getUserByName(userId);
			
		if(user == null) {
			 throw new UsernameNotFoundException(userId + " not found");
		}
		
//
//		UserDetails userDetails = User.builder()
//               .username(user.getUserName())
//       		// 密碼前面加上"{noop}"使用NoOpPasswordEncoder，也就是不對密碼進行任何格式的編碼。
//               .password("{noop}" + user.getPassWord())
//               .roles("user").build();
		
		CustomUserProfile userProfile = new CustomUserProfile();
		userProfile.setUserName(user.getUserName());
		userProfile.setPwd(user.getPassWord());
		
		List<GrantedAuthority> authorities = new ArrayList<>();
		authorities.add(new SimpleGrantedAuthority("user"));
		
		return new UsernamePasswordAuthenticationToken(userProfile, password, authorities);
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return true;
	}

}
