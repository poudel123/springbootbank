package com.capgemini.bankappcheck.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.capgemini.bankappcheck.entities.Customer;
import com.capgemini.bankappcheck.service.CustomerService;

@Controller
//@SessionAttributes("customer")

public class CustomerController {

	/*@ModelAttribute("customer")
	   public Customer setUpUserForm() {
	      return new Customer();
	   }*/
	
	
	@Autowired
	CustomerService customerService;

	@RequestMapping("/")
	public String index() {
		return "index";
	}

	@RequestMapping("/loginCustomer")
	public String getLoginCustomerPage(Model model) {
		model.addAttribute("customer", new Customer());
		return "loginCustomer";
	}

	@RequestMapping(value = "/login.do", method = RequestMethod.POST)
	public String displayDetails(HttpSession session, HttpServletRequest request, @ModelAttribute Customer customer, @RequestParam int customerid, @RequestParam String password) {
		session = request.getSession();
		System.out.println(customer);
		 Customer cust = new Customer(null, customerid, password, null, null,
		 null, null);
		Customer authenticatedCustomer = null;
		authenticatedCustomer = customerService.authenticate(customer);
		session.setAttribute("customer", authenticatedCustomer);
		return "displayDetails";
	}

	@RequestMapping("/changePassword")
	public String getChangePasswordPage() {
		return "changePassword";
	}

	@RequestMapping("/changePassword.do")
	public String changePassword(HttpSession session, HttpServletRequest request, @RequestParam String oldPassword,
			@RequestParam String newPassword, @RequestParam String confirmNewPassword) {
		session = request.getSession();
		if (newPassword.equals(confirmNewPassword)) {
			System.out.println("ghdxcfcdfvgrfb");
			if (customerService.updatePassword((Customer) session.getAttribute("customer"), oldPassword,
					confirmNewPassword)) {
				return "passwordSuccessfullyChanged";
			}
		}
		return "errorPage";
	}

	@RequestMapping("/editProfile")
	public String getEditProfilePage() {
		return "editProfile";
	}

	@RequestMapping("/editProfile.do")
	public String editProfile(HttpSession session, HttpServletRequest request, @RequestParam String emailId,
			@RequestParam String address) {
		session = request.getSession();
		Customer customer = null;
		Customer customer2 = (Customer) session.getAttribute("customer");
		customer = customer2;
		customer.setAddress(address);
		customer.setEmail(emailId);
		customer = customerService.updateProfile(customer);
		System.out.println(customer);
		session.setAttribute("customer", customer);
		return "profileUpdatedSuccesfully";
	}

	@RequestMapping("/logout.do")
	public String logout(HttpSession session, HttpServletRequest request) {
		session = request.getSession();
		session.invalidate();
		return "index";
	}

	@RequestMapping("/displayDetails")
	public String displayUser() {
		return "displayDetails";
	}

	@RequestMapping("/index")
	public String getIndex() {
		return "index";
	}
}
