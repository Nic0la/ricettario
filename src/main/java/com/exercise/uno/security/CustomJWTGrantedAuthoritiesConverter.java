package com.exercise.uno.security;

import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;

import java.util.Collection;
import java.util.stream.Collectors;

//public class CustomJWTGrantedAuthoritiesConverter implements JwtGrantedAuthoritiesConverter {

//    @Override
//    public Collection<SimpleGrantedAuthority> convert(Jwt jwt) {
//        return jwt.getClaimAsStringList("roles").stream()  // "roles" è un esempio di claim
//                .map(SimpleGrantedAuthority::new)
//                .collect(Collectors.toList());
//    }
//}
