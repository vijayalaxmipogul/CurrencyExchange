package com.currency.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * This is the RateModel class with properties:rates
 *  
 */

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RateModel {
	
	private Rates rates;
	
}
