//package com.harshit.book_rental_system.configuration;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.web.SecurityFilterChain;
//
//@EnableWebSecurity
//@Configuration
//public class SecurityConfig {
//
//    private final RestAuthenticationEntryPoint authenticationEntryPoint;
//
//    @Autowired
//    public BasicAuthSecurity(RestAuthenticationEntryPoint authenticationEntryPoint) {
//        this.authenticationEntryPoint = authenticationEntryPoint;
//    }
//
//    @Bean
//    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//        http.csrf().disable();
//
//        http.authorizeRequests()
//                .antMatchers("/public/**")
//                .permitAll()
//                .anyRequest()
//                .authenticated()
//                .and()
//                .httpBasic()
//                .authenticationEntryPoint(authenticationEntryPoint);
//        return http.build();
//    }
//    @Autowired
//    public void configureGlobal(AuthenticationManagerBuilder auth, PasswordEncoder passwordEncoder) throws Exception {
//        auth.inMemoryAuthentication()
//                .passwordEncoder(PasswordEncoderConfig.passwordEncoder())
//                .withUser("user1")
//                .password(PasswordEncoderConfig.passwordEncoder().encode("password"))
//                .roles("ADMIN");
//    }
//    @Bean
//    public UserDetailsService userDetailsService(){
//
//    }
//
////    @Bean
////    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
////        http.authorizeRequests()
////                .antMatchers("/public/**").permitAll() // Allow access to public resources
////                .antMatchers("/admin/**").hasRole("ADMIN") // Allow access to admin resources for users with ADMIN role
////                .anyRequest().authenticated() // Require authentication for all other requests
////                .and()
////                .formLogin(); // Use form-based authentication
////
////        return http.build();
////    }
//
//    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
//        return httpSecurity.csrf().disable()
//                .authorizeHttpRequests()
//                .requestMatchers("/").permitAll()
//                .and().authorizeHttpRequests()
//                .requestMatchers("/")
//                .authenticated()
//                .and().httpBasic()
//                .and().build();
//
//    }
//}
