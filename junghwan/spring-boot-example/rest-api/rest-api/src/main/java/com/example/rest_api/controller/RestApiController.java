package com.example.rest_api.controller;

import com.example.rest_api.model.BookQueryParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api")
public class RestApiController {

    @GetMapping(path = "/hello")
    public String hello() {
        var html = "<html> <body> <h1> Hello Spring Boot </h1> </body> </html>";
        return html;
    }

    @GetMapping(path = "/echo/{message}/age/{age}/is-man/{isMan}")
    public String echo(@PathVariable("message") String msg,
                       @PathVariable int age,
                       @PathVariable boolean isMan) {
        System.out.println("echo message: " + msg);
        System.out.println("echo age: " + age);
        System.out.println("echo isMan: " + isMan);

        return msg.toUpperCase();
    }

    @GetMapping(path = "/book")
    public void queryParam(
            @RequestParam String category,
            @RequestParam String issuedYear,
            @RequestParam("issued-month") String issuedMonth,
            @RequestParam("issued_day") String issuedDay
    ) {
        System.out.println(category);
        System.out.println(issuedYear);
        System.out.println(issuedMonth);
        System.out.println(issuedDay);
    }
    @GetMapping(path = "/book2")
    public void queryParamDto(
        BookQueryParam bookQueryParam
    ) {
        System.out.println(bookQueryParam);
    }

    @DeleteMapping(path = {"/user/{userName}/delete", "/user/{userName}/del"})
    public void delete(@PathVariable String userName) {
        log.info("userName: {}", userName);
    }
}
