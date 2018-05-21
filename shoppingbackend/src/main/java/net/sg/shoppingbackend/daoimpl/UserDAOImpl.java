package net.sg.shoppingbackend.daoimpl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import net.sg.shoppingbackend.dao.UserDAO;
import net.sg.shoppingbackend.dto.Address;
import net.sg.shoppingbackend.dto.Cart;
import net.sg.shoppingbackend.dto.User;

@Repository("userDAO")
@Transactional
public class UserDAOImpl implements UserDAO{
	@Autowired
	SessionFactory sessionFactory;
	
	@Override
	public boolean addUser(User user) {
		try {
			
			sessionFactory.getCurrentSession().persist(user);
			return true;
		} catch (Exception ex) {
			ex.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean addAddress(Address address) {
		try {
			
			sessionFactory.getCurrentSession().persist(address);
			return true;
		} catch (Exception ex) {
			ex.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean updateCart(Cart cart) {
		try {
			
			sessionFactory.getCurrentSession().update(cart);
			return true;
		} catch (Exception ex) {
			ex.printStackTrace();
			return false;
		}
	}

	@Override
	public User getByEmail(String email) {
		String selectQuery="FROM User where email= :email";
		try {
			return sessionFactory.getCurrentSession().createQuery(selectQuery, User.class).setParameter("email",email).getSingleResult();
		}catch(Exception ex) {
			//ex.printStackTrace();
		return null;
		}
	}

	@Override
	public Address getBilingAddress(User user) {
		String selectQuery="FROM Address where user = :user AND billing=:billing";
		try {
			return sessionFactory.getCurrentSession().createQuery(selectQuery, Address.class).setParameter("user",user).setParameter("billing",true).getSingleResult();
		}catch(Exception ex) {
			ex.printStackTrace();
		return null;
		}
	}

	@Override
	public List<Address> listShippingAddress(User user) {
		String selectQuery="FROM Address where user = :user AND shipping=:shipping";
		try {
			return sessionFactory.getCurrentSession().createQuery(selectQuery, Address.class).setParameter("user",user).setParameter("shipping",true).getResultList();
		}catch(Exception ex) {
			ex.printStackTrace();
		return null;
		}
	}

}
