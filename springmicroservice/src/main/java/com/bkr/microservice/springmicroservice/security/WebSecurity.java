package com.bkr.microservice.springmicroservice.security;

import com.bkr.microservice.springmicroservice.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@EnableWebSecurity
public class WebSecurity extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserService userDetailleService;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.cors().and()
                .csrf().disable()
                .authorizeRequests()
                .antMatchers(HttpMethod.POST,SecurityConstants.SIGN_UP_URL).permitAll()
                .antMatchers(HttpMethod.GET,SecurityConstants.SIGN_UP_URL).permitAll()
                .anyRequest().authenticated()
                .and()
                .addFilter(authenticationFilter());
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailleService).passwordEncoder(bCryptPasswordEncoder);
    }

    @Bean
    public AuthenticationFilter authenticationFilter() throws Exception {
        final AuthenticationFilter filter = new AuthenticationFilter(authenticationManager());
        filter.setFilterProcessesUrl("/login");
        return filter;
    }
}
