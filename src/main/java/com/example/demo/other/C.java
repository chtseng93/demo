package com.example.demo.other;

import java.util.HashMap;
import java.util.Map;

public class C {
	
	
	
	
	
	
	
	
	

	private String c1; 

	
	private static Map<String, String> map = new HashMap<>();
	
	
	static {
		
		map.put("com", "comService");
		map.put("fac", "facService");
		
	}
	
	
	public String getC1() {
		return c1;
	}

	public void setC1(String c1) {
		this.c1 = c1;
	}
	
	
}
