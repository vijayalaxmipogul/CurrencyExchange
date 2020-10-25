package com.currency.controller.test;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.currency.CurrencyExchangeApplication;
import com.currency.constant.ConstantData;
import com.currency.controller.CurrencyController;

/**
 * Integration Test cases for Currency Controller
 * 
 * @author vijayalaxmi.pogul
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = CurrencyExchangeApplication.class)
public class CurrencyControllerIntegrationTest {

	private MockMvc mockMvc;

	@Autowired
	CurrencyController currencyController;

	@Before
	public void setup() throws Exception {
		MockitoAnnotations.initMocks(this);
		this.mockMvc = MockMvcBuilders.standaloneSetup(this.currencyController).build();

	}

	/**
	 * This method test login and on success return welcome jsp
	 * 
	 * @throws Exception
	 */
	@Test
	public void testLoginSuccess() throws Exception {
		mockMvc.perform(get(ConstantData.BASE_URL + ConstantData.LOGIN_URL)).andExpect(status().isOk())
				.andExpect(view().name(ConstantData.WELCOME_VIEW));
	}

	/**
	 * This method test report and on success returns report view
	 * 
	 * @throws Exception
	 */
	@Test
	public void testReportOnSuccess() throws Exception {
		mockMvc.perform(get(ConstantData.BASE_URL + ConstantData.REPORT_URL)).andExpect(status().isOk())
				.andExpect(view().name(ConstantData.REPORT_VIEW));
	}

}
