package com.system.dto;

import lombok.Data;
import java.util.Date;


@Data
public class DragonInfo {

	private Integer id ;
	
	private Date time ;
	
	private String type ;
	
	private String dragon1_code;
	
	private String dragon1_name ;
	
	private String pchange1 ;
	
	private String bidding_price1;
	
	private String dragon2_code;
	
	private String dragon2_name ;
	
	private String pchange2 ;
	
	private String bidding_price2;
	
	private String dragon3_code;
	
	private String dragon3_name ;
	
	private String pchange3 ;
	
	private String bidding_price3;
}
