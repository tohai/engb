package io.github.tubean.authservice.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import io.github.tubean.authservice.entities.AppUser;
import io.github.tubean.authservice.respository.UserRepository;

@Service // It has to be annotated with @Service.
public class UserDetailsServiceImpl implements UserDetailsService {

//	@Autowired
//	private BCryptPasswordEncoder encoder;

	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//		userRepository.save(new AppUser(2, "admin", encoder.encode("12345"), 0));

		AppUser appUser = userRepository.findByUsername(username);

		if (appUser != null) {

			// Remember that Spring needs roles to be in this format: "ROLE_" + userRole
			// (i.e. "ROLE_ADMIN")
			// So, we need to set it to that format, so we can verify and compare roles
			// (i.e. hasRole("ADMIN")).
			List<GrantedAuthority> grantedAuthorities = AuthorityUtils
					.commaSeparatedStringToAuthorityList(appUser.getUserRole());

			// The "User" class is provided by Spring and represents a model class for user
			// to be returned by UserDetailsService
			// And used by auth manager to verify and check user authentication.
			return new User(appUser.getUsername(), appUser.getPassword(), grantedAuthorities);
		}

		throw new UsernameNotFoundException("Username: " + username + " not found");
	}

}