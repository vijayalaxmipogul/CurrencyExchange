package com.currency.controller.test;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.currency.CurrencyExchangeApplication;
import com.currency.constant.ConstantData;
import com.currency.controller.CurrencyController;
import com.currency.service.CurrencyService;
import com.currency.test.data.TestData;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = CurrencyExchangeApplication.class)
public class CurrencyControllerUnitTest {

	private MockMvc mockMvc; 
	
	@InjectMocks
	private CurrencyController currencyController;
	@Mock
	private CurrencyService currencyService;
	
	
	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
		mockMvc=MockMvcBuilders.standaloneSetup(currencyController).build();
	}
	
	/**
	 * This method test login and on success return welcome jsp
	 * @throws Exception
	 */
	@Test
	public void testLoginSuccess() throws Exception {
		when(currencyService.getTodayCurrency()).thenReturn(TestData.getRateData());
		mockMvc.perform(get(ConstantData.BASE_URL +ConstantData.LOGIN_URL)).andExpect(status().isOk()).andExpect(view().name(ConstantData.WELCOME_VIEW));
		verify(currencyService).getTodayCurrency();
	}

	
	/**
	 * This method test report and on success  returns report view 
	 * @throws Exception
	 */
	@Test
	public void testReportOnSuccess() throws Exception {
		when(currencyService.getReport()).thenReturn(TestData.getRateListData());
		mockMvc.perform(get(ConstantData.BASE_URL +ConstantData.REPORT_URL)).andExpect(status().isOk()).andExpect(view().name(ConstantData.REPORT_VIEW));
		verify(currencyService).getReport();
	}

	
}
