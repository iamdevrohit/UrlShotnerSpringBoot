package com.urlshortner.demo.urlshortnerdemo.services.manageuserservice;

import com.urlshortner.demo.urlshortnerdemo.entity.User;

public interface ManageUser {

    User register_user(String username, String password, boolean active, String roles);
}
