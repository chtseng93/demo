package com.example.demo.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)//
public class ActionException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 
	 */
//	private static final long serialVersionUID = -901795118420559676L;//序列化ID
	
	private String errorMessage;
	
	public ActionException(String errorMessage) {
//		super();
		this.errorMessage = errorMessage;
	}
	
	
	
	
	
	
	
	
	

}
