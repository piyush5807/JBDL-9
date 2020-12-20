package com.example.jbdl9.demojpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.nio.file.Files;
import java.util.List;

@SpringBootApplication
public class DemoJpaApplication implements CommandLineRunner{

	@Autowired
	BankRepository bankRepository;

	@Autowired
	UserRepository userRepository;

	@Value("${my.prop}")
	String prop;

	@Value("${spring.datasource.url}")
	String url;

	public static void main(String[] args) {
		SpringApplication.run(DemoJpaApplication.class, args);

		System.out.println("Hello World");

//		System.out.println(prop);

	}

	@Override
	public void run(String... args) throws Exception {

//		List<Bank> banks = bankRepository.findAll();
//		for(Bank bank : banks){
//			System.out.println(bank.getBankId() + " " + userRepository.getUsersByBankId(bank.getBankId()));
//		}
//		for(Bank bank: banks){
//			System.out.println(bank.getBankId() + " " + bank.getBankName());
//		}
//		System.out.println(bankRepository.findAll());
//		System.out.println(prop);
//		System.out.println(url);

//		Bank b = new Bank();
//		b.setBankName("DEF");
//
//		System.out.println(bankRepository.getAllBanks());

//		System.out.println(bankRepository.findByBanksId(3));
	}


//	@Override
//	public void run(String... args) throws Exception {
//		System.out.println("Hello World");
//
//		Bank b = new Bank();
//		b.setBankName("ABC");
//
////		bankRepository.save(b);
//
//		tempFunc("ABC", "DEF", "PQR");
//	}
//
//	public void tempFunc(String... values){
//
//		for(String value : values){
//			System.out.println(value);
//		}
//
//		System.out.println(values.getClass());
//	}
}
