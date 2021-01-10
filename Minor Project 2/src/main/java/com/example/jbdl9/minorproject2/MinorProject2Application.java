package com.example.jbdl9.minorproject2;

import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@SpringBootApplication
public class MinorProject2Application implements CommandLineRunner {

	List<String> keywords = Arrays.asList("cooking");

	@Autowired
	VideoService videoService;

	public static void main(String[] args) {
		SpringApplication.run(MinorProject2Application.class, args);
	}

	public void run(String ...args) throws IOException, ParseException {
//		videoService.initDB();
//		videoService.fetchVideos(keywords);
	}
}
