package com.currency.service;

import static com.currency.constant.ConstantData.EXCHANGE_RATE_URL;
import static com.currency.constant.ConstantData.TODAY_EXCHANGE_RATE_URL;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.currency.model.RateModel;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@AllArgsConstructor
@NoArgsConstructor
@Slf4j
public class CurrencyService {

	Logger logger = LoggerFactory.getLogger(CurrencyService.class);

	@Autowired
	RestTemplate restTemplate;

	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}

	/**
	 * This method fetches the current currency exchange by calling the rest
	 * endpoint
	 * 
	 * @return RateModel
	 */
	public RateModel getTodayCurrency() {
		RateModel rateModel = null;
		try {
			rateModel = restTemplate.getForObject(TODAY_EXCHANGE_RATE_URL, RateModel.class);
		} catch (Exception e) {
			log.error("Exception occurred during retrieving data from {} : {}", TODAY_EXCHANGE_RATE_URL, e);
		}
		return Objects.nonNull(rateModel) ? rateModel : null;
	}

	/**
	 * This method fetches the past six months data from rest endpoint based on
	 * current date
	 * 
	 * @return List<RateModel>
	 */
	public List<RateModel> getReport() {
		List<RateModel> reportList = null;
		try {
			LocalDate todayDate = LocalDate.now();
			reportList = new ArrayList<RateModel>();
			RateModel rateData = null;
			for (int i = 0; i < 6; i++) {
				todayDate = todayDate.minusMonths(1);
				rateData = restTemplate.getForObject(EXCHANGE_RATE_URL + todayDate, RateModel.class);
				reportList.add(rateData);
			}
		} catch (Exception e) {
			log.error("Exception occurred during retrieving report data : {}", e);
		}
		return Objects.nonNull(reportList) ? reportList : null;
	}

}
