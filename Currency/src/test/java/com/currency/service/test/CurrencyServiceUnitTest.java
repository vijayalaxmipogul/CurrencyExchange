package com.currency.service.test;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.time.LocalDate;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.web.client.RestTemplate;

import com.currency.constant.ConstantData;
import com.currency.model.RateModel;
import com.currency.service.CurrencyService;
import com.currency.test.data.TestData;

@RunWith(MockitoJUnitRunner.class)
public class CurrencyServiceUnitTest {

	@Mock
	private RestTemplate restTemplate;

	@InjectMocks
	private CurrencyService currencyService = new CurrencyService();

	/**
	 * This method test fetches current exchange data by calling rest endpoint
	 */
	@Test
	public void testGetTodayCurrency() {
		Mockito.when(restTemplate.getForObject(ConstantData.TODAY_EXCHANGE_RATE_URL, RateModel.class))
				.thenReturn(TestData.getRateData());

		RateModel rate = currencyService.getTodayCurrency();

		Assert.assertEquals(TestData.getRateData().getRates().getGbp(), rate.getRates().getGbp(), 0);
		Assert.assertEquals(TestData.getRateData().getRates().getUsd(), rate.getRates().getUsd(), 0);
		Assert.assertEquals(TestData.getRateData().getRates().getHkd(), rate.getRates().getHkd(), 0);
		verify(restTemplate, times(1)).getForObject(ConstantData.TODAY_EXCHANGE_RATE_URL, RateModel.class);
	}
	
	/**
	 * This method test exception occurred when calling rest endpoint
	 */
	@Test(expected = Exception.class)
	public void testGetTodayCurrencyOnException() {
		Mockito.doThrow(new Exception()).when(restTemplate).getForObject(ConstantData.TODAY_EXCHANGE_RATE_URL, RateModel.class);
		RateModel rate = currencyService.getTodayCurrency();
		Assert.assertEquals(null, rate);
		verify(restTemplate, times(1)).getForObject(ConstantData.TODAY_EXCHANGE_RATE_URL, RateModel.class);
	}

	/**
	 * This method test calling external rest endpoint for report generation
	 */
	@Test
	public void testGetReport() {
		LocalDate todayDate = LocalDate.now();
		Mockito.when(
				restTemplate.getForObject(ConstantData.EXCHANGE_RATE_URL + todayDate.minusMonths(1), RateModel.class))
				.thenReturn(TestData.getRateData());
		Mockito.when(
				restTemplate.getForObject(ConstantData.EXCHANGE_RATE_URL + todayDate.minusMonths(2), RateModel.class))
				.thenReturn(TestData.getRateData());
		Mockito.when(
				restTemplate.getForObject(ConstantData.EXCHANGE_RATE_URL + todayDate.minusMonths(3), RateModel.class))
				.thenReturn(TestData.getRateData());
		Mockito.when(
				restTemplate.getForObject(ConstantData.EXCHANGE_RATE_URL + todayDate.minusMonths(4), RateModel.class))
				.thenReturn(TestData.getRateData());
		Mockito.when(
				restTemplate.getForObject(ConstantData.EXCHANGE_RATE_URL + todayDate.minusMonths(5), RateModel.class))
				.thenReturn(TestData.getRateData());
		Mockito.when(
				restTemplate.getForObject(ConstantData.EXCHANGE_RATE_URL + todayDate.minusMonths(6), RateModel.class))
				.thenReturn(TestData.getRateData());

		List<RateModel> rateList = currencyService.getReport();
		Assert.assertEquals(6, rateList.size());
		verify(restTemplate, times(1)).getForObject(ConstantData.EXCHANGE_RATE_URL + todayDate.minusMonths(1),
				RateModel.class);
		verify(restTemplate, times(1)).getForObject(ConstantData.EXCHANGE_RATE_URL + todayDate.minusMonths(2),
				RateModel.class);
		verify(restTemplate, times(1)).getForObject(ConstantData.EXCHANGE_RATE_URL + todayDate.minusMonths(3),
				RateModel.class);
		verify(restTemplate, times(1)).getForObject(ConstantData.EXCHANGE_RATE_URL + todayDate.minusMonths(4),
				RateModel.class);
		verify(restTemplate, times(1)).getForObject(ConstantData.EXCHANGE_RATE_URL + todayDate.minusMonths(5),
				RateModel.class);
		verify(restTemplate, times(1)).getForObject(ConstantData.EXCHANGE_RATE_URL + todayDate.minusMonths(6),
				RateModel.class);
	}
	
	/**
	 * This method test exception occurred when calling rest endpoint for report generation
	 */
	@Test(expected = Exception.class)
	public void testGetREportOnException() {
		Mockito.doThrow(new Exception()).when(restTemplate).getForObject(ConstantData.EXCHANGE_RATE_URL +LocalDate.now().minusMonths(1), RateModel.class);
		List<RateModel> rate = currencyService.getReport();
		Assert.assertEquals(null, rate);
		verify(restTemplate, times(1)).getForObject(ConstantData.TODAY_EXCHANGE_RATE_URL+LocalDate.now().minusMonths(1), RateModel.class);
	}

}