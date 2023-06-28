package com.caremoa.authority.domain.service;

import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.caremoa.authority.adapter.MemberFeign;
import com.caremoa.authority.domain.dto.LoginDto;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component("userDetailsService")
@RequiredArgsConstructor
@Slf4j
public class CustomUserDetailsService implements UserDetailsService {
	private final PasswordEncoder passwordEncoder;
	private final MemberFeign serviceController;
	
	@Override
    public UserDetails loadUserByUsername(final String username) {
		LoginDto loginDto =  serviceController.findUserId(username);
		
		if(loginDto != null) {
			 log.info(loginDto.toString());
			 return new User(loginDto.getUserId(), passwordEncoder.encode(loginDto.getPassword()), AuthorityUtils.createAuthorityList(loginDto.getRole().split(",")));
			// return new User("lbg","lbg111", AuthorityUtils.createAuthorityList("ADMIN", "MEMBER", "HELPER"));
        } else {
            throw new UsernameNotFoundException(username + " -> 데이터베이스에서 찾을 수 없습니다.");
        }
    } 
}