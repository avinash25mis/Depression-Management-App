package com;



import com.dao.CommonRepository;
import com.dto.LoginDto;
import com.dto.request.CommonRequestVO;
import com.dto.response.GenericResponse;
import com.model.DashboardContent;
import com.model.DayWiseContent;
import com.model.StoredFile;
import com.service.CommonService;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import java.util.*;

@Controller
@CrossOrigin(origins = "*", allowedHeaders = "*")
public  class CommonController {

@Autowired
private CommonRepository commonRepository;

@Autowired
private CommonService commonService;

    @ResponseBody
    @GetMapping(value = {"/test"})
    public String test() {

       return  "tested";
    }


    @RequestMapping(value = {"/index"})
    public String index() {

        return  "index";
    }


    @PostMapping(value = {"/login"})
    public String login(@ModelAttribute LoginDto loginDto, ModelMap map) {
        return getLatestDashboardData(map);
    }


    @PostMapping(value = {"/dashBoardPage"})
    public String login(ModelMap map) {

        return getLatestDashboardData(map);
    }








    @RequestMapping("/viewDayPage")
    public String viewData(ModelMap map)
    {

        return "viewDayPage";
    }




    @PostMapping("/addDayPage")
    public String addData(ModelMap map)
    {
        map.put("dayContent",new DayWiseContent());
        return "addDayPage";
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

    @RequestMapping("/viewDayPage/json")
    @ResponseBody
    public List viewDataJson(ModelMap map)
    {
        List<DayWiseContent> all = commonRepository.findAll(DayWiseContent.class.getName());

        map.put("dayDataList",all);
        return all;
    }




}
