package com.controller;

import com.dao.CommonRepository;
import com.model.DashboardContent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/dashboard")
public class DashboardContentController {

    @Autowired
    private CommonRepository commonRepository;



    @ResponseBody
    @PostMapping(value = {"/update"})
    public String login(@ModelAttribute DashboardContent content, ModelMap map) {

       commonRepository.saveOrUpdate(content);
        return "saved";

    }
}


