package com.system.dto;

import com.alibaba.fastjson.JSONObject;
import lombok.Data;

@Data
public class YesterDayParamsVo {

	private String api_name ;

	private String token = "df5ae8d5dafad5c600d68f067ec2a8c62e2664d8aeb81c95778c9cd8";

	private JSONObject params = new JSONObject();
}
