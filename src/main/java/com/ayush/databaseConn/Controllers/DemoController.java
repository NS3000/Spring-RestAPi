package com.ayush.databaseConn.Controllers;

import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/demo")
@Tag(name="Demo Api")
public class DemoController {

    @GetMapping("/print1")
    @Hidden
    public String hiddenPrint(){
        return "HELLO";
    }

    @GetMapping("/print2")
    public String shownPrint(){
        return "HELLO";
    }
}
