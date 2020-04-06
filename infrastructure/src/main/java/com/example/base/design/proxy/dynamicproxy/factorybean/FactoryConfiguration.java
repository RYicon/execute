package com.example.base.design.proxy.dynamicproxy.factorybean;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FactoryConfiguration {

    @Bean
    public ClientInvokerProxy clientInvokerProxy() {
        return new ClientInvokerProxy();
    }

}
