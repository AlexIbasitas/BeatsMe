package com.company.beatsmebackend.controller;

import com.company.beatsmebackend.service.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Controller
public class IndexController {


    private final StorageService storageService;
    @Autowired
    public IndexController(StorageService storageService) {
        this.storageService = storageService;
    }
    @GetMapping
    public String getHome(Model model){
        model.addAttribute("songKey", storageService.getFilenames());
        return "index";
    }

    @PostMapping
    public String uploadFile(@RequestParam("file") MultipartFile file) throws IOException {
        storageService.upload(file);
        return "redirect:/";
    }
}
