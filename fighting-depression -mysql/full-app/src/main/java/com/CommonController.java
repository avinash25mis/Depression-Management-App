package com;



import com.dao.CommonRepository;
import com.dto.request.CommonRequestVO;
import com.dto.response.GenericResponse;
import com.model.DayWiseContent;
import com.model.StoredFile;
import com.service.CommonService;
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


    @GetMapping(value = {"/", "/index"})
    public String index() {

        return  "index";
    }




    @RequestMapping("/viewDayPage")
    public String viewData(ModelMap map)
    {
      /*  List all = commonRepository.findAll(DayWiseContent.class.getName());
        map.put("dayDataList",all);*/
        return "viewDayPage";
    }


    @RequestMapping("/viewDayPage/json")
    @ResponseBody
    public List viewDataJson(ModelMap map)
    {
        List<DayWiseContent> all = commonRepository.findAll(DayWiseContent.class.getName());

        map.put("dayDataList",all);
        return all;
    }

    @RequestMapping("/addDayPage")
    public String addData(ModelMap map)
    {
        map.put("dayContent",new DayWiseContent());
        return "addDayPage";
    }





}
