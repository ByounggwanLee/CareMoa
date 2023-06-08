package com.caremoa.authority;

import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

@Component("userDetailsService")
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {
	private final PasswordEncoder passwordEncoder;

	/* private final MUsrInfoRepository mUsrInfoRepository;
    private final JUsrRoleRepository jUsrRoleRepository;
    private final PasswordEncoder passwordEncoder;

    public CustomUserDetailsService(MUsrInfoRepository mUsrInfoRepository, JUsrRoleRepository jUsrRoleRepository,
            PasswordEncoder passwordEncoder) {
        this.mUsrInfoRepository = mUsrInfoRepository;
        this.jUsrRoleRepository = jUsrRoleRepository;
        this.passwordEncoder = passwordEncoder;
    } 

    @Override
    @Transactional
    public UserDetails loadUserByUsername(final String username) {
        Optional<MUsrInfo> mUsrInfo = mUsrInfoRepository.findById(username);

        if (mUsrInfo.isPresent()) {
            List<GrantedAuthority> grantedAuthorities = jUsrRoleRepository.findByUsrIdAndRoleKnd(username, "M_USR_GRP")
                    .stream().map(data -> new SimpleGrantedAuthority(data.getRoleCode())).collect(Collectors.toList());
            return new org.springframework.security.core.userdetails.User(mUsrInfo.get().getUsrId(),
                    passwordEncoder.encode(mUsrInfo.get().getPwd()), grantedAuthorities);
        } else {
            throw new UsernameNotFoundException(username + " -> 데이터베이스에서 찾을 수 없습니다.");
        }
    } 
    */
	
	@Override
    public UserDetails loadUserByUsername(final String username) {
		if("lbg".equals(username)) {
            return new User("lbg", passwordEncoder.encode("lbg111"), AuthorityUtils.createAuthorityList("ADMIN", "MEMBER", "HELPER"));
        } else {
            throw new UsernameNotFoundException(username + " -> 데이터베이스에서 찾을 수 없습니다.");
        }
    } 
}