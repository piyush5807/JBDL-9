package org.geekforgeek.jbdl9.RESTAPIs;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
public class TestController {


    @RequestMapping(value = "/song/test-abc", method = RequestMethod.GET)
    public String greetUser(){
        return "Hello User!!";
    }

    @GetMapping("/song/test-abc2")
    public String greetUser2(){
        return "Hello User!!";
    }

    @PostMapping("/view/test-post")
    @ResponseStatus(value = HttpStatus.ACCEPTED)
    public Request getUser(@RequestBody Request request){
        return request;

    }


}
