package com.example.demo.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.exception.ActionException;
import com.example.demo.model.req.EmployeeReq;
import com.example.demo.model.req.UserReq;
import com.example.demo.service.WebDemoService;

@Controller
public class WebDemoController {
	
	@Autowired
	private WebDemoService webService;

	@GetMapping("/webDemo/index")
	public String index(Model model) {
		
		model.addAttribute("allemplist", webService.getEmployeeList());
		model.addAttribute("page", "fragments/employee");
		model.addAttribute("feature", "employee");
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
	
	@GetMapping("/logine")
    public String logine() {
        throw new ActionException("請洽系統管理員!!");
    }
	

	
	@PostMapping("/register")
	public String doRegister(@ModelAttribute("user") UserReq userReq,
			RedirectAttributes redirectAttributes) {
		System.out.println("=======>dodoRegister");
		System.out.println("=======>UserName"+userReq.getUserName());
		System.out.println("=======>UserPWD"+userReq.getPassWord());
		Optional<String> optional = webService.register(userReq);
		String message = optional.orElse("success");
		if("success".equals(message)) {
			redirectAttributes.addFlashAttribute("registerSuccessMessage", "Success!!Your account has been created!");
			return "redirect:login";
		}
		System.out.println("=======>message"+message);
		redirectAttributes.addFlashAttribute("errorMessage", message);
		return "redirect:login";
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
