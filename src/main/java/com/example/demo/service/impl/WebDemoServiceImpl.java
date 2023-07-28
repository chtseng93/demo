package com.example.demo.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
	
	

}
