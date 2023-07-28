package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.model.req.EmployeeReq;
import com.example.demo.model.req.UserReq;
import com.example.demo.model.res.UserRes;
import com.example.demo.service.WebDemoService;
import com.mysql.cj.util.StringUtils;

@Controller
public class WebDemoController {
	
	@Autowired
	private WebDemoService webService;

	@GetMapping("/webDemo/index")
	public String index(Model model) {
		
		
		model.addAttribute("allemplist", webService.getEmployeeList());
		
		return "index";
	}
	

	@GetMapping("/webDemo/employee")
	public String employee(Model model) {
		
		
		model.addAttribute("allemplist", webService.getEmployeeList());
		
		return "employee";
	}
	
	
//	@PostMapping("/webDemo/deleteEmployee")
//	public String deleteEmployee(@ModelAttribute("employee") EmployeeReq employeeReq,Model model) {
//		
//		System.out.println("=>>>>>/webDemo/deleteEmployee");
//		System.out.println("=>>>>>"+employeeReq.getId());
//
//		model.addAttribute("allemplist", webService.deleteEmployeeById(employeeReq.getId()));
//		
//		return "employee";
//	}
	
	@PostMapping("/webDemo/deleteEmployee")
	public String deleteEmployee(@ModelAttribute("employee") EmployeeReq employeeReq,Model model) {
		
		System.out.println("=>>>>>/webDemo/deleteEmployee");
		System.out.println("=>>>>>"+employeeReq.getId());

		webService.deleteEmployeeById(employeeReq.getId());
		
		return "redirect:/webDemo/employee";
	}
	
	@PostMapping("/webDemo/updateEmployee")
	public String updateEmployee(@ModelAttribute("employee") EmployeeReq employeeReq,Model model) {
		
		System.out.println("=>>>>>/webDemo/updateEmployee");
		System.out.println("=>>>>>"+employeeReq.getId());

		model.addAttribute("employee", webService.getEmployeeById(employeeReq.getId()));
		
		return "update";
	}
	
		
	@PostMapping("/webDemo/save")
    public String saveEmployee(@ModelAttribute("employee") EmployeeReq employeeReq) {
		webService.saveEmployee(employeeReq);
        return "redirect:/webDemo/employee";
    }
	
	@GetMapping("/addnew")
    public String addNewEmployee(Model model) {
        EmployeeReq employee = new EmployeeReq();
        model.addAttribute("employee", employee);
        return "newemployee";
	}
	
	@GetMapping("/login")
    public String login() {
        return "login";
    }
	
//	@PostMapping("/userLogin")
//    public String userlogin(@ModelAttribute("user") UserReq userReq) {
//		UserRes res= webService.userLogin(userReq);
//		if(StringUtils.isNullOrEmpty(res.getErrorMsg())) {
//			return "redirect:/webDemo/employee";
//		}
//		return "login";
//    }
    
}
