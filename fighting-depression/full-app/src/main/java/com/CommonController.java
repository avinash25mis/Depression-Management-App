package com;



import com.ExceptionHandling.AppExceptions;
import com.configuration.mail.AppEmailSender;
import com.configuration.security.AppUserDetailService;
import com.configuration.security.JwtService;
import com.configuration.security.dto.AuthRequest;
import com.dao.CommonRepository;
import com.dto.DayContentDto;
import com.dto.LoginDto;
import com.model.DashboardContent;
import com.model.DayWiseContent;
import com.model.StoredFile;
import com.service.CommonService;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletContext;
import java.sql.SQLException;
import java.util.*;

@Controller
@CrossOrigin(origins = "*", allowedHeaders = "*")
public  class CommonController {

@Autowired
private CommonRepository commonRepository;

@Autowired
private CommonService commonService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private AppUserDetailService appUserDetailService;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private ServletContext context;

    @Autowired
    private AppEmailSender appEmailSender;

    @ResponseBody
    @GetMapping(value = {"/test"})
    public String test() {

       return  "tested";
    }

    @RequestMapping(value = {"/login","/"})
    public String firstPage(@RequestParam(value = "error",required = false)boolean error,@RequestParam(value = "logout",required = false)boolean logout,Map map) {
       map.put("error",error);
       map.put("logout",logout);
       map.put("contextPath", context.getContextPath());
       return "login";
    }

    @RequestMapping(value = {"/homePage"})
    public String homePage(ModelMap map) {
        map.put("refresh", false);
        map.put("contextPath", context.getContextPath());
        getLatestDashboardData(map);
        return "home";
    }

    @RequestMapping(value = {"/home"})
    public String login(@ModelAttribute AuthRequest request,ModelMap map) {
       if(request==null ||request.getUsername()==null|| request.getUsername()==null){
           return  "redirect:/login?logout=true";
       }else {
           try {
               authenticationManager.authenticate(
                       new UsernamePasswordAuthenticationToken(
                               request.getUsername(),
                               request.getPassword()
                       )
               );
           } catch (BadCredentialsException e) {
             return  "redirect:/login?error=true";

           }
           final UserDetails userDetails = appUserDetailService.loadUserByUsername(request.getUsername());
           String jwt = jwtService.generateTokenFromUserDetail(userDetails);
           map.put("refresh", true);
           map.put("authToken", jwt);
           map.put("contextPath", context.getContextPath());
           getLatestDashboardData(map);
           return "home";
       }
    }


    @PostMapping(value = {"/login"})
    public String login(@ModelAttribute LoginDto loginDto, ModelMap map) {

        return getLatestDashboardData(map);
    }


    @PostMapping(value = {"/dashBoardPage"})
    public String login(ModelMap map) {

        return getLatestDashboardData(map);
    }




    @PostMapping(value = {"/delete/{id}"})
    public String delete(@PathVariable(value = "id")Long id, ModelMap map) {
        commonRepository.deleteDayData(id);
        return  "forward:/viewDayPage";
    }








    @RequestMapping("/viewDayPage")
    public String viewData(ModelMap map)
    {

        return "viewDayPage";
    }

    @RequestMapping("/viewDayPage/data")
    @ResponseBody
    public List viewDataJson()
    {
        List all = commonRepository.findAllDayContent();
        return all;
    }


    @GetMapping(value = {"/dashBoard/json"})
    @ResponseBody
    public List dashboardJson(ModelMap map) {
        List lastRecords = commonRepository.getLastRecords(DashboardContent.class.getName(), 1);
        return lastRecords;
    }


    @GetMapping("/daysData/fromTo")
    @ResponseBody
    @Transactional(readOnly = true)
    public List viewSelectiveData(@RequestParam Integer startDay,@RequestParam Integer endDay) throws SQLException {
        List<DayWiseContent> all = commonRepository.findDaysDataInRange(startDay,endDay);
        for(DayWiseContent dayWiseContent:all){
            if(CollectionUtils.isNotEmpty(dayWiseContent.getFileList())){
                for(StoredFile file:dayWiseContent.getFileList()){
                    if(file.getContent()!=null){
                        file.setBase64(file.getContent().getBytes(1, (int) file.getContent().length()));
                    }
                }
            }
        }

        return all;
    }







    @PostMapping("/addDayPage")
    public String addData(@RequestParam(required = false,name = "id")Long id,ModelMap map)
    {
        DayContentDto content=null;
        if(id!=null) {
            List<DayContentDto>list = commonRepository.findAllDayContentById(id);
            if(CollectionUtils.isNotEmpty(list)){
                content=list.get(0);
            }

        }

        if(content==null){
            content=new DayContentDto();
        }


        map.put("dayContent",content);
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






}
