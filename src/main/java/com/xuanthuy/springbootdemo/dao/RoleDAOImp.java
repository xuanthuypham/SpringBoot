package com.xuanthuy.springbootdemo.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.xuanthuy.springbootdemo.dao.interfaces.RoleDAO;
import com.xuanthuy.springbootdemo.entity.Role;
import com.xuanthuy.springbootdemo.entity.UserRole;
import com.xuanthuy.springbootdemo.entity.Userinfo;

@Repository
@Transactional
public class RoleDAOImp implements RoleDAO {

	@Autowired
	private SessionFactory sessionFactory;
	@Override
	public List<Role> getList() {
		// TODO Auto-generated method stub
		return null;
	}
	
	//viet nham Dao cai nay ben userroledao
	@SuppressWarnings("unchecked")
	@Override
	public List<String> findById(long userid) {
		// TODO Auto-generated method stub
		Session session = this.sessionFactory.getCurrentSession();
		Query<String> que = session.createQuery("select ur.role.rolename from "+UserRole.class.getName()+" ur "//
				+"where ur.userinfo.id = "+userid);
		return que.getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public Role findRoleByName(String roleName) {
		// TODO Auto-generated method stub
		Session session = this.sessionFactory.getCurrentSession();
		Query<Role> que = session.createQuery("select role from "+Role.class.getName()//
				+"where rolename = '"+roleName+"'");
		return que.getSingleResult();
	}
	@Override
	public void insert(Userinfo user, List<String> roleNameList) {
		// TODO Auto-generated method stub
		Session session = this.sessionFactory.getCurrentSession();
		for(String roleName:roleNameList) {
			Role role = this.findRoleByName(roleName);
			if(role==null) {
			 role = new Role();
			 role.setRolename(Role.ROLE_USER);
			 session.persist(role);
			}
			UserRole ur = new UserRole();
			ur.setRole(role);
			ur.setUserinfo(user);
			session.persist(ur);
		}
	}


}
