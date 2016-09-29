package com.ilya.securityservice;

import com.ilya.model.Client;
import com.ilya.model.enums_utils.Role;
import com.ilya.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by ilya on 25.09.2016.
 */
@Service("customUserDetailsService")
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    ClientService service;

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
            Client client = service.getByEmail(s);
            Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
            for (Role role : client.getRoles()) {
                grantedAuthorities.add(new SimpleGrantedAuthority(role.name()));
            }
            return new org.springframework.security.core.userdetails.User(client.getEmail(), client.getPassword(), grantedAuthorities);


    }



}
