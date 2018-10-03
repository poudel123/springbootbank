package com.capgemini.bankappcheck;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.time.LocalDate;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.capgemini.bankappcheck.controller.CustomerController;
import com.capgemini.bankappcheck.entities.BankAccount;
import com.capgemini.bankappcheck.entities.Customer;
import com.capgemini.bankappcheck.service.CustomerService;
@RunWith(MockitoJUnitRunner.class)
public class CustomerControllerTest {

	@Mock
	private CustomerService customerService;
	
	@InjectMocks
	private CustomerController customerController;
	
	private MockMvc mockMvc;
	
	@Before
	public void init() {
		 {
			MockitoAnnotations.initMocks(this);
			mockMvc = MockMvcBuilders.standaloneSetup(customerController).build();
	}
	
	}
	@Test
	public void testIndex() throws Exception{
		mockMvc.perform(get("/")).andExpect(view().name("index"));
	}
	@Test
	public void testAuthenticate() throws Exception {
		Customer customer = new Customer();
		customer.setCustomerId(1234);
		customer.setPassword("123");
		
		Customer customer1 = new Customer("poudel", 1234, "123", "a@b", "hyd", LocalDate.now(), new BankAccount());
		Customer customer2 = new Customer(null, 1234, "12", null, null, null, null);
		
		/*when(customerService.authenticate(customer)).thenReturn(customer1);
		mockMvc.perform(post("/login.do").flashAttr("customer", customer)).andExpect(view().name("displayDetails"));*/

		when(customerService.authenticate(customer)).thenReturn(customer2);
		mockMvc.perform(post("/login.do").flashAttr("customer", customer)).andExpect(view().name("displayDetails"));

}
	@Test
	public void testLogout() throws Exception {
		mockMvc.perform(post("/logout.do")).andExpect(view().name("index"));
	}
}