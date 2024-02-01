package com.company.beatsmebackend.controller;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
public class EchoController {

    @RequestMapping(value = "/echo/{input}", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    public String echo(@PathVariable String input) {
        return input;
    }
}