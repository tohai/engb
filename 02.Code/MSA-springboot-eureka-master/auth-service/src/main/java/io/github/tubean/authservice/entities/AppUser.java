package io.github.tubean.authservice.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import io.github.tubean.authservice.common.AuthConst;

@Entity
@Table(name = "USERTBL")
public class AppUser {

	@Id
	@Column(name = "username", length = 30, nullable = false)
	private String username;

	@Column(name = "password", nullable = false)
	private String password;

	@Column(name = "role", nullable = false)
	private int role;

	public static enum ROLE {
		ROLE_ADMIN, ROLE_USER, ROLE_PBUSER
	};

	public AppUser() {
	}

	public AppUser(long id, String username, String password, int role) {
		//this.id = id;
		this.username = username;
		this.password = password;
		this.role = role;
	}

	/**
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * @param username the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @return the role
	 */
	public int getRole() {
		return role;
	}

	/**
	 * @param role the role to set
	 */
	public void setRole(int role) {
		this.role = role;
	}

	public String getUserRole() {
		if (role == ROLE.ROLE_ADMIN.ordinal()) {
			return AuthConst.AUTH_ROLE_ADMIN;
		}
		return AuthConst.AUTH_ROLE_USER;
	}

}
