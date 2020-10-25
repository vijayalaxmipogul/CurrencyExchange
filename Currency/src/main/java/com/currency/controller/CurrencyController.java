package com.currency.controller;

import static com.currency.constant.ConstantData.BASE_URL;
import static com.currency.constant.ConstantData.RATE_MODEL;
import static com.currency.constant.ConstantData.REPORT_LIST_MODEL;
import static com.currency.constant.ConstantData.REPORT_URL;
import static com.currency.constant.ConstantData.REPORT_VIEW;
import static com.currency.constant.ConstantData.WELCOME_VIEW;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.currency.constant.ConstantData;
import com.currency.model.RateModel;
import com.currency.service.CurrencyService;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@RequestMapping(BASE_URL)
public class CurrencyController {

	@Autowired
	private CurrencyService currencyService;


	/**
	 * This endpoint is used for login
	 * 
	 * @return welcome jsp
	 */
	@GetMapping(ConstantData.LOGIN_URL)
	public ModelAndView login() {
		log.debug("Entering login() : {} ",this.getClass().getName());
		ModelAndView mv = new ModelAndView();
		RateModel rateModel = currencyService.getTodayCurrency();
		mv.addObject(RATE_MODEL, rateModel);
		mv.setViewName(WELCOME_VIEW);
		return mv;
	}

	/**
	 * This endpoint is used to generate past six months report
	 * 
	 * @return report view
	 * 
	 */
	@GetMapping(REPORT_URL)
	public ModelAndView getReport() {
		log.debug("Entering getReport() : {} ",this.getClass().getName());
		ModelAndView mv = new ModelAndView();
		List<RateModel> reportList = currencyService.getReport();
		mv.addObject(REPORT_LIST_MODEL, reportList);
		mv.setViewName(REPORT_VIEW);
		return mv;
	}

}
