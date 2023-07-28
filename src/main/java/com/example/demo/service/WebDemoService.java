package com.example.demo.service;

import java.util.List;

import com.example.demo.model.req.EmployeeReq;
import com.example.demo.model.req.UserReq;
import com.example.demo.model.res.EmployeeRes;
import com.example.demo.model.res.UserRes;

public interface WebDemoService {

	public List<EmployeeRes> getEmployeeList();
	
	public List<EmployeeRes> deleteEmployeeById(Integer id);
	
	public EmployeeRes getEmployeeById(Integer id);
	
	public void saveEmployee(EmployeeReq req);
	
	public UserRes userLogin(UserReq userReq);
	

}
