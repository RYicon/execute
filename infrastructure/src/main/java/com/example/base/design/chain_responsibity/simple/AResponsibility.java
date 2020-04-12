package com.example.base.design.chain_responsibity.simple;

public class AResponsibility implements IResponsibility {
    @Override
    public void doSomething(String input, IResponsibility responsibility) {

        // TODO do something
        System.out.println( input +"->A does something");

        //当前没法处理，回调回去，让下一个去处理
        responsibility.doSomething(input, responsibility);
    }


}