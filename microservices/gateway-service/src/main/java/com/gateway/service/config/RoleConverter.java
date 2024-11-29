package com.gateway.service.config;

import org.springframework.core.convert.converter.Converter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class RoleConverter implements Converter<Jwt, Collection<GrantedAuthority>> {
    @Override
    public Collection<GrantedAuthority> convert(Jwt source) {
        Map<String, Object> realAccess = (Map<String, Object>) source.getClaims().get("realm_access");
        if (realAccess == null || realAccess.isEmpty()) {
            return new ArrayList<>();
        }
        List<String> roles = (List<String>) realAccess.get("roles");
        Collection<GrantedAuthority> collect = roles.stream().map(role -> "ROLE_" + role)
                .map(role -> new SimpleGrantedAuthority(role))
                .collect(Collectors.toList());
        return collect;
    }
}
