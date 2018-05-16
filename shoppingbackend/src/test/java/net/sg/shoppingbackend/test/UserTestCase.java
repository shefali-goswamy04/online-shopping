package net.sg.shoppingbackend.test;

import static org.junit.Assert.assertEquals;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import net.sg.shoppingbackend.dao.UserDAO;
import net.sg.shoppingbackend.dto.Address;
import net.sg.shoppingbackend.dto.Cart;
import net.sg.shoppingbackend.dto.User;


public class UserTestCase {
	private static AnnotationConfigApplicationContext context;
	private static UserDAO userDAO;
	private User user=null;
	private Address  address=null;
	private Cart cart=null;
	
	@BeforeClass
	public static void init()
	{
		context=new AnnotationConfigApplicationContext();
		context.scan("net.sg.shoppingbackend");
		context.refresh();
		userDAO=(UserDAO)context.getBean("userDAO");
	}
	@Test
	public void testAdd()
	{
		user =new User();
		user.setFirstName("hrithik");
		user.setLastName("roshan");
		user.setEmail("hr@gmail.com");
		user.setContactNumber("12456");
		user.setRole("USER");
		user.setPassword("123456");
		//add user
		assertEquals("Failed to add user!",true,userDAO.addUser(user));
		
		
		address=new Address();
		address.setAddressLineOne("101/B Jadoo society,Krissh nagar");
		address.setAddressLineTwo("near kabil store");
		address.setCity("Mumbai");
		address.setState("Maharashtra");
		address.setCountry("India");
		address.setPostalCode("400001");
		address.setBilling(true);
		
		address.setUserId(user.getId());
		
		//add address
		assertEquals("Failed to add address!",true,userDAO.addAddress(address));
		
		
		
		if(user.getRole().equals("USER"))
		{
			//create cart 
			cart=new Cart();
			cart.setUser(user);
			
			assertEquals("Failed to add cart!",true,userDAO.addCart(cart));
			//add shipping address
			address=new Address();
			address.setAddressLineOne("201/B Jadoo society,Krissh nagar");
			address.setAddressLineTwo("near kabil store");
			address.setCity("Mumbai");
			address.setState("Maharashtra");
			address.setCountry("India");
			address.setPostalCode("400001");
			//set shiiping to true
			address.setShipping(true);
			address.setUserId(user.getId());
			
			//add address
			assertEquals("Failed to add address!",true,userDAO.addAddress(address));
			
		}
	}
}
