package com.xuanthuy.springbootdemo.entity;
// Generated Aug 30, 2018 3:57:55 AM by Hibernate Tools 5.0.6.Final

import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

/**
 * Userinfo generated by hbm2java
 */
@Entity
@Table(name = "userinfo", catalog = "bank", uniqueConstraints = @UniqueConstraint(columnNames = "username"))
public class Userinfo implements java.io.Serializable {

	private Long id;
	private String username;
	private String encryptedPass;
	private int enabled;
	private String firstname;
	private String lastname;
	private Byte gender;
	private String email;
	private Integer countrycode;
	private Set<UserRole> userRoles = new HashSet<UserRole>(0);

	public Userinfo() {
	}

	public Userinfo(String username, String encryptedPass, int enabled) {
		this.username = username;
		this.encryptedPass = encryptedPass;
		this.enabled = enabled;
	}

	public Userinfo(String username, String encryptedPass, int enabled, String firstname, String lastname, Byte gender,
			String email, Integer countrycode) {
		this.username = username;
		this.encryptedPass = encryptedPass;
		this.enabled = enabled;
		this.firstname = firstname;
		this.lastname = lastname;
		this.gender = gender;
		this.email = email;
		this.countrycode = countrycode;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)

	@Column(name = "id", unique = true, nullable = false)
	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name = "username", unique = true, nullable = false, length = 20)
	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@Column(name = "encrypted_pass", nullable = false, length = 128)
	public String getEncryptedPass() {
		return this.encryptedPass;
	}

	public void setEncryptedPass(String encryptedPass) {
		this.encryptedPass = encryptedPass;
	}

	@Column(name = "enabled", nullable = false)
	public int getEnabled() {
		return this.enabled;
	}

	public void setEnabled(int enabled) {
		this.enabled = enabled;
	}

	@Column(name = "firstname", length = 50)
	public String getFirstname() {
		return this.firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	@Column(name = "lastname", length = 20)
	public String getLastname() {
		return this.lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	@Column(name = "gender")
	public Byte getGender() {
		return this.gender;
	}

	public void setGender(Byte gender) {
		this.gender = gender;
	}

	@Column(name = "email", length = 50)
	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Column(name = "countrycode")
	public Integer getCountrycode() {
		return this.countrycode;
	}

	public void setCountrycode(Integer countrycode) {
		this.countrycode = countrycode;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "userinfo")
	public Set<UserRole> getUserRoles() {
		return this.userRoles;
	}

	public void setUserRoles(Set<UserRole> userRoles) {
		this.userRoles = userRoles;
	}

}
