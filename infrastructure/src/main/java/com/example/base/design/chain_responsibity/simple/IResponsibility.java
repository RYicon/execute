package com.example.base.design.chain_responsibity.simple;

interface IResponsibility {
	// 处理逻辑的方法
	void doSomething(String input, IResponsibility responsibility);
}