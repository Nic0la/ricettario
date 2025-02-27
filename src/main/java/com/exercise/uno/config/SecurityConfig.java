package com.exercise.uno.config;

import com.exercise.uno.models.entity.User;
import com.exercise.uno.repository.UserRepository;
import com.exercise.uno.service.JwtFilter;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfig {


    private final JwtFilter jwtFilter;

    public SecurityConfig(JwtFilter jwtFilter) {
        this.jwtFilter = jwtFilter;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(csrf -> csrf.disable())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/**", "/").permitAll()
                        .anyRequest().authenticated())
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration)
            throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }


    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

//    @Bean
//    public JwtDecoder jwtDecoder() throws Exception {
//        // Carica la chiave pubblica da un certificato X.509
//        FileInputStream fis = new FileInputStream("path-to-public-key.pem");
//        CertificateFactory cf = CertificateFactory.getInstance("X.509");
//        X509Certificate cert = (X509Certificate) cf.generateCertificate(fis);
//        RSAPublicKey publicKey = (RSAPublicKey) cert.getPublicKey();
//
//        // Crea un decoder con la chiave pubblica
//        return NimbusJwtDecoder.withPublicKey(publicKey).build();
//    }

//    @Bean
//    public JwtDecoder jwtDecoder() {
//        // Specifica l'URL dell'endpoint JWKS
//        String jwkSetUri = "https://your-provider.com/.well-known/jwks.json"; // Sostituisci con il tuo URL
//
//        // Restituisci un JwtDecoder che usa l'issuer e recupera la chiave pubblica dal JWKS
//        return JwtDecoders.fromIssuerLocation(jwkSetUri);
//    }

    @Bean
    public UserDetailsService userDetailsService(UserRepository userRepo){
        return username -> {
            User user = userRepo.findByUsername(username);
            if (user != null) return user;
            throw new UsernameNotFoundException("User '" + username + "' not found");
        };
    }

//    // Definisci un AuthenticationManager come bean
//    @Bean
//    public AuthenticationManager authenticationManager(HttpSecurity http) throws Exception {
//        AuthenticationManagerBuilder authenticationManagerBuilder =
//                http.getSharedObject(AuthenticationManagerBuilder.class);
//        authenticationManagerBuilder.inMemoryAuthentication()
//                .withUser("user")
//                .password("{noop}password") // {noop} è un prefisso che indica nessuna codifica
//                .roles("USER")
//                .and()
//                .withUser("admin")
//                .password("{noop}admin")
//                .roles("ADMIN");
//        return authenticationManagerBuilder.build();
//    }

//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        http
//                .authorizeHttpRequests(auth -> auth
//                        .requestMatchers("/**", "/").hasRole("ADMIN")
//                        .requestMatchers("/api/**").hasRole("USER")
//                        .requestMatchers("/**", "/login").permitAll()
//                )
//                .oauth2ResourceServer(oauth2 -> oauth2
//                        .jwt(jwt -> jwt.decoder(jwtDecoder)
//                                .jwtAuthenticationConverter(jwtAuthenticationConverter()))
//                )
//                .csrf(csrf -> csrf.disable()); // Se usi solo API REST, puoi disabilitare CSRF
//
//        return http.build();
//    }

//    @Bean
//    @Deprecated
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        http
//                .authorizeHttpRequests(auth -> auth
//                        .requestMatchers("/api/recipe/**","/","/**", "/login").permitAll() // Permetti l'accesso a tutte le pagine, incluso il login
//                        .anyRequest().authenticated() // Richiedi autenticazione per tutte le altre richieste
//                )
//                .csrf(csrf -> csrf.disable()); // Disabilita CSRF se hai solo API REST
//
//        return http.build();
//    }
//
//    @Bean
//    public JwtAuthenticationConverter jwtAuthenticationConverter() {
//        JwtAuthenticationConverter converter = new JwtAuthenticationConverter();
//        converter.setJwtGrantedAuthoritiesConverter(new JwtGrantedAuthoritiesConverter());
//        return converter;
//    }
 }


