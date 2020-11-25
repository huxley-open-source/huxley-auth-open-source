package com.thehuxley.auth.config

import com.thehuxley.auth.security.userdetails.HuxleyUserDetailsService
import com.thehuxley.auth.security.crypto.password.ShaPasswordEncoder
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.Ordered
import org.springframework.core.annotation.Order
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.crypto.password.PasswordEncoder

@Configuration
@EnableWebSecurity
@Order(value = Ordered.HIGHEST_PRECEDENCE)
class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    HuxleyUserDetailsService huxleyUserDetailsService

    @Override
    public void configure(HttpSecurity http) throws Exception {

        http
                .requestMatchers()
                    .antMatchers("/login", "/logout", "/oauth/authorize").and()
                .authorizeRequests()
                    .antMatchers("/oauth/authorize").authenticated().and()
                .formLogin().permitAll().and()
                .rememberMe().and()
                .logout().permitAll().deleteCookies("JSESSIONID").and()
                .csrf().disable()
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {

        auth
                .userDetailsService(huxleyUserDetailsService)
                .passwordEncoder(passwordEncoder())

    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        new ShaPasswordEncoder("SHA-512")
    }
}
