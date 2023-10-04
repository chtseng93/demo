package com.example.demo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

//攔Controllerexception
@ControllerAdvice
public class ActionExceptionHandler {
	
	
	@ExceptionHandler(ActionException.class)
	public String exception(ActionException exception,Model model) {
		String errorMsg = exception.getErrorMessage();
		model.addAttribute("errorMsg",errorMsg );
		return "error";
	}

}
