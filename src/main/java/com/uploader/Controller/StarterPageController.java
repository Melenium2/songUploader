package com.uploader.Controller;

import com.uploader.Service.FirestoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/")
public class StarterPageController
{
    @Autowired
    private FirestoreService fsHelper;

    @GetMapping
    public String getStarterPage()
    {
        return "starterPage";
    }
}
