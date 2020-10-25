package com.currency.test.data;

import java.util.List;

import com.currency.model.RateModel;
import com.currency.model.Rates;

public class TestData {

	public static RateModel getRateData() {
		return RateModel.builder().rates(Rates.builder().gbp(1.1).usd(2.2).hkd(3.3).build()).build();
	}
	
	public static List<RateModel> getRateListData() {
		return List.of(RateModel.builder().rates(Rates.builder().gbp(1.1).usd(2.2).hkd(3.3).build()).build());
	}
}
