package io.github.tubean.authservice.respository;

import org.springframework.data.repository.CrudRepository;

import io.github.tubean.authservice.entities.AppUser;

public interface UserRepository extends CrudRepository<AppUser, String> {
	
	AppUser findByUsername(String username);
	
	AppUser findByUsernameAndPassword(String username,String password);
	
	
	

}
