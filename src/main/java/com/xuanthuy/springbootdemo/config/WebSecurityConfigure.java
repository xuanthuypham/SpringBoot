package com.xuanthuy.springbootdemo.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.annotation.Persistent;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.rememberme.InMemoryTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.social.security.SpringSocialConfigurer;

@Configuration
@EnableWebSecurity
public class WebSecurityConfigure extends WebSecurityConfigurerAdapter {

	@Autowired
	private DataSource dataSource;
	
	@Autowired
	private UserDetailsService userDetailService;
	
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
		return bCryptPasswordEncoder;
	}
	
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		//Cai dat dich vu tim kiem trong database
		//Thiet lap PasswordEndcoder
		auth.userDetailsService(userDetailService); //.passwordEncoder(passwordEncoder());
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// TODO Auto-generated method stub
		http.csrf().disable();
		
		//Cac trang khong yeu cau login
		http.authorizeRequests().antMatchers("/security/","/security/login","/security/logout").permitAll();
		
		//Cac trang co url /userinfo/ yeu cau login voi role ROLE_USER va ROLE_ADMIN
		//Neu chua login, no se redirect to trang /login
		http.authorizeRequests().antMatchers("/security/userinfo").access("hasAnyRole('ROLE_ADMIN','ROLE_USER')");
		
		//Trang danh cho ROLE_ADMIN
		http.authorizeRequests().antMatchers("/security/admin").access("hasRole('ROLE_ADMIN')");
		
		//Denied Access permission
		http.authorizeRequests().and().exceptionHandling().accessDeniedPage("/security/403");
		
		//Cau hinh login Form
		http.authorizeRequests().and().formLogin()
			.loginProcessingUrl("/security/checking") //After submit URL
			.loginPage("/security/login")
			.defaultSuccessUrl("/security/userinfo")
			.failureUrl("/security/login?error=true")
			.usernameParameter("username")
			.passwordParameter("password")
			//Cau hinh logout page
			.and().logout()
			.logoutUrl("/security/logout")
			.logoutSuccessUrl("/security/logoutsuccessful");
		

		//Cau hinh Remember me
		http.authorizeRequests().and().rememberMe().tokenRepository(this.persistentTokenRepository());
		
		//Them vao khi su dung Spring Social login roi sigup
		//http.apply(new SpringSocialConfigurer()).signupUrl("/signup");
	}	
	
	
	//Luu Token vao table trong database
	@Bean
	public PersistentTokenRepository persistentTokenRepository() {
		JdbcTokenRepositoryImpl db = new JdbcTokenRepositoryImpl();
		db.setDataSource(dataSource);
		return db;
	}
	
	//Luu Token vao bo nho cua Web Server
/*	@Bean
	public PersistentTokenRepository persistentTokenRepository() {
		InMemoryTokenRepositoryImpl memory = new InMemoryTokenRepositoryImpl();
		return memory;
	} */
}
