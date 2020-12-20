package com.example.jbdl9.demojpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;
import java.io.IOException;
import java.util.List;

@RestController
public class BankController {

    @Autowired
    BankRepository bankRepository;

    @GetMapping("/banks")
    public List<Bank> getBanks(){
        return bankRepository.findAll();
    }

    @GetMapping("/bank/{id}")
    public Bank getBank(@PathVariable("id") int id){
        return bankRepository.findByBanksId(id);
    }

    @PostMapping("/bank")
    public void addBank(@RequestBody Bank bank){
        bankRepository.save(bank);
    }

    @PostMapping("/banks")
    public void addBanks(@RequestBody List<Bank> banks){
        bankRepository.saveAll(banks);
    }

    @GetMapping("/test")
    public void test(HttpServletRequest httpServletRequest) throws IOException, ServletException {
        Part part = httpServletRequest.getPart("file");

        System.out.println(part.getSubmittedFileName());


    }
}
