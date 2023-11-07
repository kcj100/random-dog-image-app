package com.kcj.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ImageController {

    @GetMapping("/displayImage")
    public String displayImagePage() {
        return "image";
    }
}
