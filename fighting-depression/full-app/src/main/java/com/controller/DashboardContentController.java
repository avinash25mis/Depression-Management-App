package com.controller;

import com.dao.CommonRepository;
import com.dto.LoginDto;
import com.model.DashboardContent;
import com.model.DayWiseContent;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/dashboard")
public class DashboardContentController {

    @Autowired
    private CommonRepository commonRepository;

    @PostMapping(value = {"/login"})
    public String login(@ModelAttribute LoginDto loginDto, ModelMap map) {
       return getLatestDashboardData(map);
    }

    @GetMapping(value = {"/home"})
    public String login(ModelMap map) {
        return getLatestDashboardData(map);
    }

    private String getLatestDashboardData(ModelMap map) {
        List lastRecords = commonRepository.getLastRecords(DashboardContent.class.getName(), 1);
        if(CollectionUtils.isNotEmpty(lastRecords)) {
            map.put("content", lastRecords.get(0));
        }else{
            map.put("content", new DashboardContent());
        }
        return  "dashboard";
    }

    @PostMapping(value = {"/update"})
    public String login(@ModelAttribute DashboardContent content, ModelMap map) {

       commonRepository.saveOrUpdate(content);
        return getLatestDashboardData(map);

    }
}


