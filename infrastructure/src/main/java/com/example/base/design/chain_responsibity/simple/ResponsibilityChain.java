package com.example.base.design.chain_responsibity.simple;

import java.util.ArrayList;
import java.util.List;

public class ResponsibilityChain implements IResponsibility {
 
	// 完整责任链列表
	private List<IResponsibility> list= new ArrayList<IResponsibility>();
	// 索引
	private int index = 0;
 
	// 添加责任对象
	public ResponsibilityChain add(IResponsibility responsibility) {
		list.add(responsibility);
		return this;
	}
 
	@Override
	public void doSomething(String input, IResponsibility responsibility) {
		// 所有遍历完了，直接返回
		if (index == list.size()) return;
		// 获取当前责任对象
		IResponsibility currentResponsibility = list.get(index);
		// 修改索引值，以便下次回调获取下个节点，达到遍历效果
		index++;
		// 调用当前责任对象处理方法
		currentResponsibility .doSomething(input, this);
	}

}
