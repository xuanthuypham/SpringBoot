package com.xuanthuy.springbootdemo.dao;

import static org.assertj.core.api.Assertions.assertThatIllegalStateException;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.persistence.NoResultException;

import org.hibernate.query.Query;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionKey;
import org.springframework.social.connect.UserProfile;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.xuanthuy.springbootdemo.dao.interfaces.UserinfoDAO;
import com.xuanthuy.springbootdemo.entity.Role;
import com.xuanthuy.springbootdemo.entity.Userinfo;
import com.xuanthuy.springbootdemo.form.UserInfoForm;
import com.xuanthuy.springbootdemo.utils.EncrytedPasswordUtils;

@Repository
@Transactional
public class UserinfoDAOImp implements UserinfoDAO{

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	@Autowired
	private SessionFactory sessionFactory;
	
	@SuppressWarnings({ "deprecation", "unchecked" })
	@Override
	public List<Userinfo> getList() {
		// TODO Auto-generated method stub
		Session session = this.sessionFactory.getCurrentSession();
		Query<Userinfo> que = session.createQuery("from Userinfo");
		return que.getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public Userinfo findUsername(String username) {
		// TODO Auto-generated method stub
		Session session = this.sessionFactory.getCurrentSession();
		try {
		Query<Userinfo> que = session.createQuery("from Userinfo where username = '"+username+"'");
		return (Userinfo) que.uniqueResult();
		} catch (NoResultException e) {
			return null;
		}
	}

	

	@SuppressWarnings("unchecked")
	@Override
	public Userinfo findByEmail(String email) {
		// TODO Auto-generated method stub\
		Session session = this.sessionFactory.getCurrentSession();
		try {
			Query<Userinfo> que = session.createQuery("select ui from "+Userinfo.class.getName()+" ui where ui.email = '"+email+"'");
			return que.uniqueResult();
			
		} catch(NoResultException e) {
			return null;
		}
	}

	@Override
	public void createUser(UserInfoForm form) {
		// TODO Auto-generated method stub
		Session sessino = this.sessionFactory.getCurrentSession();
		String encrytedPassword = this.passwordEncoder.encode(form.getPassword());
		Userinfo user = new Userinfo(form.getUserName(), encrytedPassword, form.getEnable(), form.getFirstName(), form.getLastName(), form.getGender(), form.getEmail(), form.getCountryCode());
		try {
			sessino.persist(user);
		}catch (HibernateException e) {
			e.printStackTrace();
		}
	}

	// Tu dong tao ra user khi dang nhap lan dau vao social
	@Override
	public Userinfo createUserAuto(Connection<?> connection) {
		// TODO Auto-generated method stub
		Session session = this.sessionFactory.getCurrentSession();
		ConnectionKey key = connection.getKey();
		
		UserProfile userProfile = connection.fetchUserProfile();
		String email = userProfile.getEmail();
		Userinfo user = this.findByEmail(email);
		if(user != null) {
			return user;
		}
		String userName_prefix = userProfile.getFirstName().trim().toLowerCase()+"_"+userProfile.getLastName().trim().toLowerCase();
		//Kiem tra user name
		//Luoi khong gho nua
		
		String randomPass = UUID.randomUUID().toString().substring(0, 5);
		String encryptedPassword = EncrytedPasswordUtils.encrytePassword(randomPass);
		
		Userinfo userNew = new Userinfo();
		userNew.setUsername(userName_prefix);
		userNew.setEncryptedPass(encryptedPassword);
		userNew.setEnabled(1);
		userNew.setFirstname(userProfile.getFirstName());
		userNew.setLastname(userProfile.getLastName());
		userNew.setGender(null);
		userNew.setCountrycode(10);
		session.persist(userNew);
		
		//Create default role
		List<String> roleName = new ArrayList<>();
		roleName.add(Role.ROLE_USER);
		
		return null;
	}
	
}
