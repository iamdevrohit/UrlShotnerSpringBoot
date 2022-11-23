package com.urlshortner.demo.urlshortnerdemo.config;

import com.urlshortner.demo.urlshortnerdemo.contant.Constant;
import com.urlshortner.demo.urlshortnerdemo.services.authuserservice.AuthUserFromDBifExists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import static com.urlshortner.demo.urlshortnerdemo.contant.Constant.API_MAPPER;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private static final String[] OPEN_APIS_LIST={API_MAPPER+"/user_register",API_MAPPER+"/user_login",API_MAPPER+"/**"};

    @Autowired
    AuthUserFromDBifExists authUserFromDBifExists;


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        super.configure(auth);
        auth.userDetailsService(authUserFromDBifExists);
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.authorizeHttpRequests()
                .antMatchers(API_MAPPER+"/admin/**").hasRole("ADMIN")
                .antMatchers(API_MAPPER+"/user/**").hasAnyRole("USER","ADMIN")
                .antMatchers(OPEN_APIS_LIST).permitAll().anyRequest().authenticated()
                .and().formLogin().permitAll().and().logout().permitAll().and().httpBasic();

        http.cors().disable().csrf().disable();

    }


    @Bean
    public PasswordEncoder getPasswordEncoder(){
        return NoOpPasswordEncoder.getInstance();
    }

}
