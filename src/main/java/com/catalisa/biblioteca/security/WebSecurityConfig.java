package com.catalisa.biblioteca.security;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    UserDetailsServiceImpl userDetailsService;


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.httpBasic().and().authorizeHttpRequests()
                .antMatchers(HttpMethod.GET, "/livros").hasAnyRole("ADMIN", "USER")
                .antMatchers(HttpMethod.POST, "/livros").hasAnyRole("ADMIN", "USER")
                .antMatchers(HttpMethod.PUT, "/livros/**").hasAnyRole("ADMIN", "USER")
                .antMatchers(HttpMethod.DELETE, "/livros/**").hasRole("ADMIN")
                .antMatchers(HttpMethod.GET, "/users").hasAnyRole("ADMIN", "USER")
                .antMatchers(HttpMethod.POST, "/users").hasAnyRole("ADMIN", "USER")
                .antMatchers(HttpMethod.PUT, "/users/**").hasAnyRole("ADMIN", "USER")
                .antMatchers(HttpMethod.DELETE, "/users/**").hasRole("ADMIN")
                .anyRequest().authenticated()
                .and().csrf().disable();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        //Autenticação utilizando banco de dados
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

}
