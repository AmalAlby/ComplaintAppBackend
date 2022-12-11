package com.nest.complaintappbackend.controller;


import com.nest.complaintappbackend.dao.Userdetailsdao;
import com.nest.complaintappbackend.model.ComplaintModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
public class UserController {

    @Autowired
    private Userdetailsdao udao;

    @CrossOrigin(origins = "*")
    @GetMapping("/")
    public String HomePage(){
        return "Welcome Page";
    }
    @CrossOrigin(origins = "*")
    @PostMapping(path = "/userReg", consumes = "application/json", produces = "application/json")
    public HashMap<String, String> UserRegistration(@RequestBody ComplaintModel cm){
        HashMap<String, String> hm = new HashMap<>();
        List<ComplaintModel> result = (List<ComplaintModel>) udao.FindUser(cm.getUsername());
        if(result.size() !=0){
            hm.put("status","failed");
        }else{
            udao.save(cm);
            hm.put("status","success");
        }
        return hm;
    }
    @CrossOrigin(origins = "*")
    @PostMapping(path = "/userLogin", consumes = "application/json", produces = "application/json")
    public HashMap<String, String> UserLogin(@RequestBody ComplaintModel um){
        List<ComplaintModel> result = (List<ComplaintModel>) udao.UserLogin(um.getUsername(), um.getPassword());
        HashMap<String, String> hm = new HashMap<>();
        if(result.size() == 0){
            hm.put("status","failed");
        }else{
            hm.put("status","success");
            hm.put("userId",String.valueOf(result.get(0).getId()));
        }
        return hm;
    }

    @CrossOrigin(origins = "*")
    @PostMapping(path = "/userById", consumes = "application/json", produces = "application/json")
    public List<ComplaintModel> FindUserById(@RequestBody ComplaintModel um){
        List<ComplaintModel> result = (List<ComplaintModel>) udao.FindUserById(String.valueOf(um.getId()));
        return result;
    }



}
