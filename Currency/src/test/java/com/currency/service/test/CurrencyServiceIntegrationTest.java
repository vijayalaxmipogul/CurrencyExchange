package com.currency.service.test;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.currency.CurrencyExchangeApplication;
import com.currency.model.RateModel;
import com.currency.service.CurrencyService;
/**
 * This is the Integration Test cases for Currency Service 
 * @author vijayalaxmi.pogul
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = CurrencyExchangeApplication.class)
public class CurrencyServiceIntegrationTest {
 
    @Autowired
    private CurrencyService currencyService;
 
    /**
     * This method test external rest endpoint through rest template and fetches current data
     */
    @Test
    public void testGetTodayCurrency() {
        RateModel rate = currencyService.getTodayCurrency();
        assertThat(rate).isNotNull();
    }
    
    /**
     * This method test external rest endpoint through rest template and generate report
     */
    @Test
    public void testGetReport() {
        List<RateModel> rateList = currencyService.getReport();
        assertThat(rateList).isNotNull();
    }
}