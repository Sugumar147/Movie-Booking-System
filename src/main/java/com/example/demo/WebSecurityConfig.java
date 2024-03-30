//package com.example.demo;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.provisioning.InMemoryUserDetailsManager;
//import org.springframework.security.web.SecurityFilterChain;
//
//import static org.springframework.security.config.Customizer.withDefaults;
//
//@Configuration
//public class WebSecurityConfig {
//
////    @Bean
////    public InMemoryUserDetailsManager userDetailsService() {
////        UserDetails user = User.withDefaultPasswordEncoder()
////                .username("sugu")
////                .password("pass")
////                .roles("USER")
////                .build();
////        return new InMemoryUserDetailsManager(user);
////    }
//
//    @Bean
//    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
////        return http
////                .authorizeHttpRequests((authz) -> authz
////                        .anyRequest().permitAll()
////
////                )
//////                .formLogin(login -> login
//////                        .loginPage("/confirmbooking") // Specify the custom login page URL
//////                        .permitAll()
//////                )
////                .build();
////    }
//}
