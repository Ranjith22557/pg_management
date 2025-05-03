package com.example.pgmanagement.security;

import com.example.pgmanagement.service.CustomerUserDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.LogoutConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final CustomerUserDetailsService customerUserDetailsService;

    public SecurityConfig(CustomerUserDetailsService customerUserDetailsService) {
        this.customerUserDetailsService = customerUserDetailsService;
    }


    @Bean
    public PasswordEncoder passwordEncoder(){
        return  new BCryptPasswordEncoder();
    }


    @Bean
    public AuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();

        authProvider.setUserDetailsService(customerUserDetailsService);
        authProvider.setPasswordEncoder(passwordEncoder());

        return authProvider;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{

        http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(
                authorizeHttpRequests ->
                        authorizeHttpRequests.requestMatchers("/signup","/register","/login","/css/**","/images/**","/js/**","/swagger-ui/**",
                                        "/v3/api-docs/**",
                                        "/swagger-ui.html")
                                .permitAll()
                                .requestMatchers("/rooms/**","/customers/**","/payments/**").authenticated()
                                .anyRequest().authenticated())
                .formLogin(from ->
                        from.loginPage("/login")
                                .loginProcessingUrl("/login")
                                .defaultSuccessUrl("/home",true)
                                .failureUrl("/login?error=true")
                                .permitAll())
                .logout(logout ->logout.permitAll())
                .rememberMe(rememberMe ->
                        rememberMe
                                .key("dKvD59S7I6P6m0vHbIn8pOsdLqkhIgyTAp7X+L1wzFk=")
                                .tokenValiditySeconds(86400)  // 1 day validity
                                .userDetailsService(customerUserDetailsService)
                )
                .authenticationProvider(authenticationProvider());



        return http.build();

    }
}
