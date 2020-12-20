package com.example.jbdl9.minor1;

import com.apple.eawt.Application;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

@SpringBootApplication
public class Minor1Application {

	public static void main(String[] args) {
		SpringApplication.run(Minor1Application.class, args);

		AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();

		ctx.refresh();
	}

}
