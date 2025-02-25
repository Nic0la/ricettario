package com.exercise.uno.config;

import com.exercise.uno.models.entity.User;
import com.exercise.uno.repository.UserRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

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
                    .authorizeHttpRequests(auth -> auth
                            .requestMatchers(("/**")).permitAll()
//                            .requestMatchers(("/api/register")).permitAll()
//                            .requestMatchers(("/api/**")).permitAll()
//                            .requestMatchers(("/api/user/**")).permitAll()

                    )
//                    .formLogin(login -> login
//                            .loginPage("/login").loginProcessingUrl("/authenticate")
//                            .usernameParameter("username").passwordParameter("password")
//                    )
                    .build();
        }

}
