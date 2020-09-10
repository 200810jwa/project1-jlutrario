package com.revature;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.revature.dao.IUserDAO;
import com.revature.models.User;
import com.revature.services.UserService;
import com.revature.templates.LoginTemplate;
import com.revature.templates.RegisterTemplate;

public class UserTest {
	
	@Mock
	private IUserDAO mockedDAO;
	
	private UserService testInstance = new UserService(mockedDAO);
	private User jayson;
	private LoginTemplate jaysonTemp;
	private LoginTemplate wrongTemp1;
	private LoginTemplate wrongTemp2;
	private RegisterTemplate jaysonReg;
	private User jaysonNew;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		testInstance = new UserService(mockedDAO);
		
		jayson = new User(1, "jlutrario", Integer.toString("password".hashCode()), "Jayson", "Lutrario", "jaysonlutrario@gmail.com", 1);
		jaysonTemp = new LoginTemplate("jlutrario", "password");
		wrongTemp1 = new LoginTemplate("jlutrario", "wrong");
		wrongTemp2 = new LoginTemplate("jayson", "password");
		jaysonReg = new RegisterTemplate("jlutrario", "password", "Jayson", "Lutrario", "jaysonlutrario@gmail.com", 1);
		jaysonNew = new User(0, "jlutrario", Integer.toString("password".hashCode()), "Jayson", "Lutrario", "jaysonlutrario@gmail.com", 1);
		
		when(mockedDAO.findByUsername("jlutrario")).thenReturn(jayson);
		when(mockedDAO.findByUsername("jayson")).thenReturn(null);
		when(mockedDAO.insert(jaysonNew)).thenReturn(1);
		when(mockedDAO.findByID(1)).thenReturn(jayson);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testLoginSuccessful() {
		assertEquals(testInstance.login(jaysonTemp), jayson);
	}
	
	@Test
	public void testLoginPasswordFailure() {
		assertEquals(testInstance.login(wrongTemp1), null);
	}
	
	@Test
	public void testLoginUserFailure() {
		assertEquals(testInstance.login(wrongTemp2), null);
	}
	
	@Test
	public void testRegisterSuccessful() {
		assertEquals(testInstance.register(jaysonReg), jayson);
	}

	@Test
	public void testAuthorNameSuccessful() {
		assertEquals(testInstance.authorName(1), "Jayson Lutrario");
	}
	
}
