package com.urlshortner.demo.urlshortnerdemo.services.manageuserservice;

import com.urlshortner.demo.urlshortnerdemo.entity.User;
import com.urlshortner.demo.urlshortnerdemo.repository.UserManageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ManageUserService implements ManageUser {

    @Autowired
    UserManageRepository userManageRepository;

    @Override
    public User register_user(String username, String password, boolean active, String roles) {

        User user = new User();
        user.setActive(active);
        user.setPassword(password);
        user.setUsername(username);
        user.setRoles(roles);

        userManageRepository.save(user);

        return user;
    }
}
