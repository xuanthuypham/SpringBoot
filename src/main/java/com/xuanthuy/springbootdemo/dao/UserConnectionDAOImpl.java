package com.xuanthuy.springbootdemo.dao;

import java.util.List;

import javax.persistence.NoResultException;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.xuanthuy.springbootdemo.dao.interfaces.UserConnectionDAO;
import com.xuanthuy.springbootdemo.entity.UserConnection;

@Repository
@Transactional
public class UserConnectionDAOImpl implements UserConnectionDAO {

	@Autowired
	private SessionFactory sessionFactory;
	
	@SuppressWarnings("unchecked")
	@Override
	public UserConnection findUserConnectionByUserProviderId(String userProviderId) {
		// TODO Auto-generated method stub
		Session session = this.sessionFactory.getCurrentSession();
		try {
			Query<UserConnection> que = session.createQuery("Select e from "+UserConnection.class.getName()+" e "
					+"where e.user.providerUserId = '"+userProviderId+"'");
			return que.uniqueResult();
			}catch(NoResultException e) {
				return null;
		}
	}

}
