package com.system.dto;

import lombok.Data;

import java.util.Date;
@Data
public class BiddingInfo {

	private Integer id ;
	
	private String symbol ;
	
	private String name ;
	
	private Double price;
	
	private Double volume ;
	
	private Double amount ;
	
	private String kind;
	
	private Date bidding_time;
	
	
}
