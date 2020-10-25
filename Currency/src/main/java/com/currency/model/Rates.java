package com.currency.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * This is the model Rate class with following properties:
 * USD, GBP, HKD
 * @author vijayalaxmi.pogul
 *
 */

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Rates {
	
	@JsonProperty("USD")
	private double usd;
	
	@JsonProperty("GBP")
	private double gbp;
	
	@JsonProperty("HKD")
	private double hkd;
	
}