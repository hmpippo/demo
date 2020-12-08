package org.example.controller;

import lombok.extern.slf4j.Slf4j;
import org.example.service.Calculator;
import org.example.service.DemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Slf4j
@Controller
public class DemoController {

    @Autowired
    private DemoService demoService;

    @GetMapping("/say")
    @ResponseBody
    public String say() {
        return demoService.say();
    }

    @Autowired
    private Calculator calculator;

    @RequestMapping("/{x}")
    @ResponseBody
    public String home(@PathVariable int x) {
        int ret = calculator.fib(x);
        return String.format("fib(%s): %s", x, ret);
    }

}
