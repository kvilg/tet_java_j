package org.example.rest;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ControllerTiamlife {


    @GetMapping(value="/")
    public String getALlStudents() {
        return "index";
    }
}
