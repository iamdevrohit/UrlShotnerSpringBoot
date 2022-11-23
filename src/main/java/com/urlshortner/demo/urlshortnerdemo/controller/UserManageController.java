package com.urlshortner.demo.urlshortnerdemo.controller;

import com.urlshortner.demo.urlshortnerdemo.entity.User;
import com.urlshortner.demo.urlshortnerdemo.services.manageuserservice.ManageUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.Map;
import static com.urlshortner.demo.urlshortnerdemo.contant.Constant.API_MAPPER;

@RestController
@RequestMapping(path=API_MAPPER)
public class UserManageController {

    @Autowired
    ManageUser manage_user;


    @PostMapping(path = "/user_register")
    public  @ResponseBody ResponseEntity<Map<String, Object>> register_user(
            @RequestParam String username,
            @RequestParam String password,
            @RequestParam boolean active,
            @RequestParam String roles) {

        Map<String, Object> response = new HashMap<>();

        try {

            User registering_user = manage_user.register_user(username, password, active, roles);

            response.put("data", registering_user);
            response.put("api_status", true);
            response.put("api_message", "user registered");

            return new ResponseEntity<>(response, HttpStatus.OK);

        } catch (Exception e) {
            System.out.println("register user cause : " + e.getMessage());
            response.put("api_status", false);
            response.put("api_message", "error cause : " + e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }

    }

}
