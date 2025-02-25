package com.exercise.uno.config;

import com.exercise.uno.modules.entity.User;
import com.exercise.uno.repository.UserRepository;
import com.exercise.uno.security.AuthoritiesConstants;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.servlet.util.matcher.MvcRequestMatcher;

import static org.springframework.security.config.http.MatcherType.mvc;

@Configuration
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public UserDetailsService userDetailsService(UserRepository userRepo){
        return username -> {
            User user = userRepo.findByUsername(username);
            if (user != null) return user;
            throw new UsernameNotFoundException("User '" + username + "' not found");
        };
    }


        @Bean
        public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
            return http
//                    .csrf( csrf -> csrf
//                                    .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
//                                    .csrfTokenRequestHandler(new SpaCsrfTokenRequestHandler())
//                            )

                    .authorizeHttpRequests(auth -> auth
                            .requestMatchers(("/api/recipe")).permitAll()
                            .requestMatchers(("/api/register")).permitAll()
                            .requestMatchers(("/api/register")).permitAll()
                            .requestMatchers(("/api/**")).permitAll()
                            //.requestMatchers(("/api/user")).hasRole("USER")

                    )
//                    .formLogin(login -> login
//                            .loginPage("/login").loginProcessingUrl("/authenticate")
//                            .usernameParameter("username").passwordParameter("password")
//                    )
                    .build();
        }

}
