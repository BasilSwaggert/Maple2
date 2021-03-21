package com.Helpdesk.Maple2.configurations;

import com.Helpdesk.Maple2.services.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@EnableWebSecurity
@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    private AccountService accountService;

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.cors().and().csrf().disable();

        httpSecurity.authorizeRequests()
                .antMatchers("/dashboard/**").access("hasRole('ROLE_ADMIN') or hasRole('ROLE_EMPLOYEE')")
                .antMatchers("/account/**").access("hasRole('ROLE_ADMIN') or hasRole('ROLE_EMPLOYEE')")
                .and()
                .formLogin()
                .loginPage("/login-panel")
                .loginProcessingUrl("/login/process-login")
                .defaultSuccessUrl("/login-panel/welcome")
                .failureUrl("/login-panel/login?error")
                .usernameParameter("username")
                .passwordParameter("password")
                .and().logout()
                .logoutUrl("/process-logout")
                .logoutSuccessUrl("/login-panel/login?logout")
                .deleteCookies("JSESSIONID")
                .and()
                .exceptionHandling()
                .accessDeniedPage("/login-panel/accessDenied");
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder builder) throws Exception {
        builder.userDetailsService(accountService);
    }

    @Bean
    public BCryptPasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }
}
