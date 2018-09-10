package com.xuanthuy.springbootdemo.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.security.crypto.encrypt.Encryptors;
import org.springframework.social.UserIdSource;
import org.springframework.social.config.annotation.ConnectionFactoryConfigurer;
import org.springframework.social.config.annotation.EnableSocial;
import org.springframework.social.config.annotation.SocialConfigurer;
import org.springframework.social.config.annotation.SocialConfigurerAdapter;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionFactoryLocator;
import org.springframework.social.connect.ConnectionRepository;
import org.springframework.social.connect.ConnectionSignUp;
import org.springframework.social.connect.UsersConnectionRepository;
import org.springframework.social.connect.jdbc.JdbcUsersConnectionRepository;
import org.springframework.social.connect.web.ConnectController;
import org.springframework.social.facebook.connect.FacebookConnectionFactory;
import org.springframework.social.google.connect.GoogleConnectionFactory;
import org.springframework.social.security.AuthenticationNameUserIdSource;

import com.xuanthuy.springbootdemo.dao.interfaces.UserinfoDAO;
import com.xuanthuy.springbootdemo.social.ConnectionSignUpImpl;

@Configuration
@EnableSocial
@PropertySource("classpath:social-cfg.properties")
public class SocialConfig implements SocialConfigurer{

	private boolean autoSignUp = true; //true khi dang tu dong tao tai khoan/ false khi chuyen den trang dang ky
	
	@Autowired
	private UserinfoDAO dao;
	
	@Autowired
	private DataSource dataSource;
	// Doc file social-cfg.properties
	@Override
	public void addConnectionFactories(ConnectionFactoryConfigurer cfc, Environment environment) {
		// TODO Auto-generated method stub
		try {
			this.autoSignUp = Boolean.parseBoolean(environment.getProperty("social.auto-signup"));
			
		}catch (Exception e) {
			this.autoSignUp = false;
		}
		//Facebook
		FacebookConnectionFactory ffactory = new FacebookConnectionFactory(environment.getProperty("facebook.app.id"),environment.getProperty("facebook.app.secret"));
		ffactory.setScope(environment.getProperty("facebook.scope"));
		cfc.addConnectionFactory(ffactory);
		
		//Google
		GoogleConnectionFactory gfactory = new GoogleConnectionFactory(environment.getProperty("google.client.id"), environment.getProperty("google.client.secret"));
		gfactory.setScope(environment.getProperty("google.scope"));
		cfc.addConnectionFactory(gfactory);
		System.out.println("==============================================");
		System.out.println(environment.getProperty("google.scope"));
		System.out.println("================================================");
	}

	//UseridSource xac dinh userId cua user
	@Override
	public UserIdSource getUserIdSource() {
		// TODO Auto-generated method stub
		return new AuthenticationNameUserIdSource();
	}

	//Su dung connection khai bao ben trong
	@Override
	public UsersConnectionRepository getUsersConnectionRepository(ConnectionFactoryLocator connectionFactoryLocator) {
		// TODO Auto-generated method stub
		JdbcUsersConnectionRepository usersConnectionRepository = new JdbcUsersConnectionRepository(dataSource, connectionFactoryLocator, Encryptors.noOpText());
		if(autoSignUp) {
			//Sau khi login toi social tu dong tao ra User tuong ung neu user khong ton tai
			ConnectionSignUp connectionSignUp = new ConnectionSignUpImpl(dao);
			usersConnectionRepository.setConnectionSignUp(connectionSignUp);
		}else {
			//Sau khi login social
			//if user record khong tim thay
			//Chuyen den trang dang ky
			usersConnectionRepository.setConnectionSignUp(null);
		}
		return usersConnectionRepository;
	}
	
	//Bean nay quan ly chuoi ket noi giua tai khoan mang xa hoi (account provider) va ung dung web hien tai
	@Bean
	public ConnectController connectController(ConnectionFactoryLocator connectionFactoryLocator,ConnectionRepository connectionRepository) {
		return new ConnectController(connectionFactoryLocator, connectionRepository);
	}

}
