package com.uploadvideo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.uploadvideo.serviceImpl.FileSystemStorageService;

@Controller
public class FileUploadController {
    @Autowired
    private FileSystemStorageService storageService;

    @RequestMapping("/")
    public String index()
    {
    	System.out.println("I am here -------");
        return "addVideo";
    }

    @GetMapping("/addVideo")
    public String  addVideo()
    {
    	System.out.println("I am here --where are uou-----");
        return "addVideo";
    }

    @PostMapping("/persistVideo")
    public String  persistVideo(@RequestParam("videoFile") MultipartFile videoFile)
    {
        storageService.store(videoFile);
        return "addVideo";
    }
}
