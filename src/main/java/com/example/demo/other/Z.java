package com.example.demo.other;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Z implements Runnable {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		String s = "1";
		
		C c = new C();
		c.setC1("c1");
		Z.method1(s, c);
		
		s = "122";
		
		System.out.println(c.getC1());
		
		System.out.println(s);
		
		List<String> argList = Arrays.asList(args);
		
		//結果不會改變
		argList.stream().map( m -> {
			
			return "Test-" + m;
		});
		
		
		//產生新的array[Test-1, Test-2, Test-3]
		List<String> newArrays = argList.stream().map( m -> {
			
			return "Test-" + m;
		}).collect(Collectors.toList());
		
		//[Test-1, Test-2, Test-3]
		System.out.println(newArrays);
		
		
		
		argList.stream().forEach( m -> {
			System.out.println("Test-" + m);
		});
		
		//foreach = for
//		for(String m : argList) {
//			System.out.println(m);
//		}
		
		
	}
	
	public static void method1(String p1, C c ) {
		p1 = "123";
		c.setC1("c2");
		
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}


	

}
