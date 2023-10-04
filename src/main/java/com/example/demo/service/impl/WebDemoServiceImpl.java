package com.example.demo.service.impl;

import java.lang.reflect.Member;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import com.example.demo.entity.Person;
import com.example.demo.entity.User;
import com.example.demo.mapper.PersonMapper;
import com.example.demo.mapper.UserMapper;
import com.example.demo.model.req.EmployeeReq;
import com.example.demo.model.req.UserReq;
import com.example.demo.model.res.EmployeeRes;
import com.example.demo.model.res.UserRes;
import com.example.demo.service.WebDemoService;


@Service
public class WebDemoServiceImpl implements WebDemoService{
	
	@Autowired
	PersonMapper personMapper;
	@Autowired
	UserMapper userMapper;
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	@Override
	public List<EmployeeRes> getEmployeeList() {
		
		List<EmployeeRes> employResList = new ArrayList<>();
		
		List<Person> personEntities =personMapper.findAll();
//		personEntities.forEach(person -> System.out.println(person.getAddress()));
		personEntities.forEach(person ->{
			EmployeeRes employee = new EmployeeRes();
			employee.setId(person.getId());
			employee.setLastName(person.getLastName());
			employee.setFirstName(person.getFirstName());
			employee.setEmail(person.getEmail());
			employResList.add(employee);
		});
		

		return employResList;
	}

	@Override
	public List<EmployeeRes> deleteEmployeeById(Integer id) {
		
		personMapper.deleteEmpolyeeById(id);
		return this.getEmployeeList();
	}
	
	@Override
	public EmployeeRes getEmployeeById(Integer id) {
		
		EmployeeRes employee =new EmployeeRes();
		Person person = personMapper.getEmpolyeeById(id);
		employee.setId(person.getId());
		employee.setEmail(person.getEmail());
		employee.setFirstName(person.getFirstName());
		employee.setLastName(person.getLastName());
		
		return employee;
	}

	@Override
	public void saveEmployee(EmployeeReq req) {
		if (req != null && req.getId()!=null) {
			Person person = personMapper.getEmpolyeeById(req.getId());
			person.setEmail(req.getEmail());
			person.setFirstName(req.getFirstName());
			person.setLastName(req.getLastName());
			personMapper.updatePerson(person);
		}else {
			Person person =new Person();
			person.setEmail(req.getEmail());
			person.setFirstName(req.getFirstName());
			person.setLastName(req.getLastName());
			personMapper.insertPerson(person);
		}
		
	}

	@Override
	public UserRes userLogin(UserReq userReq) {
		UserRes userRes =new UserRes();
		if(userReq!=null && userReq.getUserName()!=null && userReq.getPassWord()!=null) {
			
			User user  =new User() ;
			try {
				user = userMapper.getUserByName(userReq.getUserName());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			if(user!=null && user.getPassWord()!=null) {
				if(userReq.getPassWord().equals(user.getPassWord())) {
					userRes.setUserName(user.getUserName());
				}else{
					userRes.setErrorMsg("Wrong PassWord!");
				}
								
			}else {
				userRes.setErrorMsg("There is no user called : "+userReq.getUserName());
			}
		}else {
			userRes.setErrorMsg("Incorrect Insert Data!");
		}
		return userRes;
		
	}
	
	private String getMd5Password(String password, String salt) {
		// 對password + salt 進行三次加密
		String str = password + salt;
		for (int i = 0; i < 3; i++) {
			str = DigestUtils.md5DigestAsHex(str.getBytes()).toUpperCase();
		}
		return str;
	}
	@Override
	public Optional<String> register(UserReq userReq) {
		// TODO Auto-generated method stub
		//str.matches("[a-zA-Z0-9|\\.]*
		// 驗證欄位是否填寫及格式
		String regExp = "[A-Za-z0-9]*";
		// Create a Pattern object
	    Pattern pattern = Pattern.compile(regExp);

	     // Now create matcher object.
	    Matcher pswMatcher = pattern.matcher(userReq.getPassWord());
	    Matcher nameMatcher = pattern.matcher(userReq.getUserName());
	    System.out.println("=======>檢查前");
		if(!pswMatcher.matches()) return Optional.of("密碼格式不符");
		if(!nameMatcher.matches()) return Optional.of("帳號格式不符");
		System.out.println("=======>檢查後");
		// 檢查帳號是否重複註冊
		User user = userMapper.getUserByName(userReq.getUserName());
		System.out.println("=======>Check userName"+userReq.getUserName());
		if(user != null) return Optional.of("The account already exists");
		
		System.out.println("=======>檢查是否帳號已被使用");

		// 新增MemberAccount 資料
		User newUser  = new User();
		newUser.setUserName(userReq.getUserName());
		newUser.setPassWord(passwordEncoder.encode(userReq.getPassWord()));
		System.out.println("=======>newUser"+newUser.getPassWord());
		userMapper.insertUser(newUser);
		System.out.println("=======>returnUser"+userReq.getUserName());
		User returnUser = userMapper.getUserByName(userReq.getUserName());
		if(returnUser==null) return Optional.of("新增會員帳號時發生錯誤");
		System.out.println("=======>returnUser"+returnUser.getPassWord());

		
		return Optional.empty();
	}

	
	

}
