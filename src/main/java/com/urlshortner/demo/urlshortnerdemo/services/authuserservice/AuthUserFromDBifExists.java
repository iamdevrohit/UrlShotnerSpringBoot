package com.urlshortner.demo.urlshortnerdemo.services.authuserservice;

import com.urlshortner.demo.urlshortnerdemo.entity.User;
import com.urlshortner.demo.urlshortnerdemo.repository.UserManageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class AuthUserFromDBifExists implements UserDetailsService {

    @Autowired
    UserManageRepository userCredRepository;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Optional<User> user = userCredRepository.findByUsername(username);

        user.orElseThrow(() -> new UsernameNotFoundException("username not found with give data as "+username));

        return user.map(AuthUserDetail::new).get();

    }
}
