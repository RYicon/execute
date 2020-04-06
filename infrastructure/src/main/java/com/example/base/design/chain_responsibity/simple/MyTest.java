package com.example.base.design.chain_responsibity.simple;

import com.example.base.domain.UserInfo;

public class MyTest{
 
	public static void main(String[] args) {
		String input = "A";
		ResponsibilityChain chain= new ResponsibilityChain ();
		chain.add(new AResponsibility()).add(new BResponsibility()).add(new CResponsibility());
		chain.doSomething(input, chain);

		UserInfo userInfo = new UserInfo();
		System.out.println(UserInfo.class==userInfo.getClass());
	}
 
}
