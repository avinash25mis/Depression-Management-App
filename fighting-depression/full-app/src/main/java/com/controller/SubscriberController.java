package com.controller;

import com.ExceptionHandling.AppExceptions;
import com.dao.CommonRepository;
import com.model.Subscribers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/Subscriber")
public class SubscriberController {

    @Autowired
    private CommonRepository repository;

    @PostMapping
    public Long createProfile(@RequestBody Subscribers subscribers){
        Subscribers subscribers1 = repository.saveOrUpdate(subscribers);
        subscribers1.setCreationDate(LocalDateTime.now());
        return subscribers1.getId();
    }






    @GetMapping("/all")
    public List<Subscribers> findAll(){
        List<Subscribers> subscribersList = repository.findAll(Subscribers.class.getName());
        return subscribersList;
    }


    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") Long id){
       Subscribers subscriber= repository.findById(Subscribers.class,id);
       repository.delete(subscriber);
       return "Subscriber deleted";
    }



    @PutMapping("/emailOrFeedback")
    public String updateProfile(@RequestBody Subscribers subscribers){
        Subscribers subscribers1 = repository.findById(Subscribers.class,subscribers.getId());
        if(subscribers1!=null){
            if(!StringUtils.isEmpty(subscribers.getEmail())) {
                subscribers1.setEmail(subscribers.getEmail());
            }
            if(!StringUtils.isEmpty(subscribers.getFeedback())) {
                subscribers1.setFeedback(subscribers.getFeedback());
            }
            repository.saveOrUpdate(subscribers1);
        }else{
            throw new AppExceptions("Subscriber not found");
        }

        return "Email/Feedback Updated SuccessFully";

    }
}
