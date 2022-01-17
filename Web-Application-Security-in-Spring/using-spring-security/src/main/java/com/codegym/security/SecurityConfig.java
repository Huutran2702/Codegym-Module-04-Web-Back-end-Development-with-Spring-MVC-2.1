package com.codegym.security;

import com.codegym.model.User;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import java.util.ArrayList;
import java.util.List;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private static List<User> users = new ArrayList<>();

    static {
        users.add(new User("user","{noop}12345","USER"));
        users.add(new User("admin","{noop}12345","ADMIN"));
        users.add(new User("dba","{noop}12345","ADMIN"));

    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        for (User user: users
             ) {
            auth.inMemoryAuthentication()
                    .withUser(user.getUsername()).password(user.getPassword()).roles(user.getRole());

        }



    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests().antMatchers("/").permitAll()
                .and()
                .authorizeRequests().antMatchers("/user**").hasRole("USER")
                .and()
                .authorizeRequests().antMatchers("/admin**").hasRole("ADMIN")
                .and()
                .formLogin()
                .and()
                .logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout"));
    }

}
