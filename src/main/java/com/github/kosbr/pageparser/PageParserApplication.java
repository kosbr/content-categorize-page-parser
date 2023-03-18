package com.github.kosbr.pageparser;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.github.kosbr.pageparser")
public class PageParserApplication {

    public static void main(String[] args) {
        SpringApplication.run(PageParserApplication.class, args);
    }

}
