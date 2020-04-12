package com.example.base;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;

/**
 * ServiceInitializer
 *
 * @author jt_hu
 * @date 2018/6/6
 */
@SpringBootApplication
@ComponentScan("com.example")
public class ServiceInitializer extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(ServiceInitializer.class);
    }
}
