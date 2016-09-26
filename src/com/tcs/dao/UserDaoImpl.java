package com.tcs.dao;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.tcs.model.User;

@Repository
public class UserDaoImpl implements UserDaoInterface {
	
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public User findUserByUsername(String username) {
		
		Session session = sessionFactory.getCurrentSession();
		Transaction t = session.beginTransaction();
		Criteria cr = session.createCriteria(User.class);
		cr.add(Restrictions.ilike("username", username));
		
		System.out.println("First Query is fired.");
		User user = (User) cr.uniqueResult();
		
		//System.out.println("User is : " +user);
		
		System.out.println("Second Query is fired.");
		user.getUserRoles().iterator();
		t.commit();
		
		return user;
	}

}
