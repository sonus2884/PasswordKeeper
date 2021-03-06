package com.example.passwordkeeper.sevice.security;

import com.example.passwordkeeper.model.User;
import com.example.passwordkeeper.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CustomUserDetailService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {

        User user = userRepository.findUserByEmail(s);

//        System.out.println(user.getId());

        if (user == null) {
            throw new UsernameNotFoundException("User Name not found");
        }

        List<GrantedAuthority> authorities =
                new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority("ADMIN"));


        return new org.springframework.security.core.userdetails.User(
                user.getEmail(),
                user.getPassword(),
                authorities

        );
    }
}
