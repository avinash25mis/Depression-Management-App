package com.controller;

import com.dao.CommonRepository;
import com.dto.response.GenericResponse;
import com.model.common.AppUser;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ResourceBundle;

/**
 * @author avinash.a.mishra
 */

@RestController
@RequestMapping("/User")
public class UserController {

   @Autowired
   private CommonRepository commonRepository;

@PostMapping("/register")
public GenericResponse registerUser(@Valid @RequestBody AppUser appUser) {
    commonRepository.saveOrUpdate(appUser);
    ResourceBundle bundle=ResourceBundle.getBundle("messages/messages");
    String message = bundle.getString("app.success");
    return new GenericResponse(message,"User Registered",null);
  }



  @PostMapping("/update")
    public GenericResponse updateUser(@RequestBody AppUser appUser){

      return new GenericResponse("success","User Registered",null);
  }



    @GetMapping("/test")
    public String  get(){

        return "hello u loged in";
    }
}
