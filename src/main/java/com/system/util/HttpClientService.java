package com.system.util;

import com.alibaba.fastjson.JSONObject;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.httpclient.DefaultHttpMethodRetryHandler;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.RequestEntity;
import org.apache.commons.httpclient.methods.StringRequestEntity;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.*;
import java.util.Map.Entry;

@Slf4j
public class HttpClientService {

    public static String cookies = "";
    public static JSONObject sendPostJson(JSONObject json, String httpUrl,String charsetName) {
        HttpClient client = new HttpClient();
        httpUrl = httpUrl.trim().replaceAll("\\s*|\t|\r|\n", "");
        PostMethod method = new PostMethod(httpUrl);
        method.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET, charsetName);
        method.getParams().setParameter(HttpMethodParams.RETRY_HANDLER, new DefaultHttpMethodRetryHandler());
        method.getParams().setParameter(HttpMethodParams.SO_TIMEOUT, new Integer(5000));
        String response = "";
        JSONObject retJson = new JSONObject();

        try {
            RequestEntity entity = new StringRequestEntity(json.toJSONString(),"application/json",charsetName);
            method.setRequestEntity(entity);
            method.setRequestHeader("Content-Type","application/json;charset="+charsetName);
            int rescode = client.executeMethod(method);

            if (rescode == HttpStatus.SC_OK) {
                BufferedReader reader = new BufferedReader(new InputStreamReader(method.getResponseBodyAsStream(), charsetName));
                String curline = "";
                while ((curline = reader.readLine()) != null) {
                    response += curline;
                }
               retJson = JSONObject.parseObject(response);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            method.releaseConnection();
        }
        return retJson;
    }
    
    
    /**
     * 通用测试方法
     * @param strUrl
     * @return
     * @throws Exception
     */
	public static String sendGetRequest(String strUrl,String charsetName){
	    HttpURLConnection conn = null;
        BufferedReader reader = null;
        String rs = null;
        try {
        	strUrl = strUrl.trim().replaceAll("\\s*|\t|\r|\n", "");
        	HttpsUrlValidator.retrieveResponseFromServer(strUrl); 
            StringBuffer sb = new StringBuffer();
            URL url = new URL(strUrl);
            conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("User-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/68.0.3440.106 Safari/537.36");
            conn.setUseCaches(false);
            if(!"".equals(cookies)){
                conn.setRequestProperty("Cookie", cookies);
            }
            conn.setConnectTimeout(10000);
            conn.setReadTimeout(10000);
            conn.setInstanceFollowRedirects(false);
            conn.connect();
            cookies = conn.getHeaderField("Set-Cookie");
            InputStream is = conn.getInputStream();
            reader = new BufferedReader(new InputStreamReader(is, charsetName));
            String strRead = null;
            while ((strRead = reader.readLine()) != null) {
                sb.append(strRead);
            }
            rs = sb.toString();
        } catch (IOException e) {
        	log.error("出错==========》", e);
            throw new RuntimeException() ;
            
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    log.error("释放资源异常：{}", e);
                }
            }
            if (conn != null) {
                conn.disconnect();
            }
        }
        return rs;
    }
 
	 private  static HttpResponse doPost(String url, List<NameValuePair> list, String charset, boolean isSetCookie, String cookieValue,Map<String,String> headers) throws Exception {
    	DefaultHttpClient  httpClient = new DefaultHttpClient ();
        HttpPost httpPost = new HttpPost(url);
        if (isSetCookie) {
            httpPost.addHeader("Cookie",cookieValue);
            httpPost.addHeader("User-Agent","User-Agent: Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/70.0.3538.102 Safari/537.36 Edge/18.18362");
            if(headers!=null && !headers.isEmpty()) {
        		Set<Entry<String, String>> entrySet = headers.entrySet();
        		for (Entry<String, String> entry : entrySet) {
        			httpPost.addHeader(entry.getKey(),entry.getValue());
				}
        	}
        }
        httpPost.setEntity(new UrlEncodedFormEntity(list, charset));
        HttpResponse response = httpClient.execute(httpPost);
        return response;
    }

	 private  static HttpResponse doGet(String url,String charset, boolean isSetCookie, String cookieValue,Map<String,String> headers) throws Exception {
    	DefaultHttpClient  httpClient = new DefaultHttpClient ();
        HttpGet httpGet= new HttpGet(url);
        if (isSetCookie) {
        	httpGet.addHeader("Cookie",cookieValue);
            httpGet.addHeader("User-Agent","User-Agent: Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/70.0.3538.102 Safari/537.36 Edge/18.18362");
        	if(headers!=null && !headers.isEmpty()) {
        		Set<Entry<String, String>> entrySet = headers.entrySet();
        		for (Entry<String, String> entry : entrySet) {
        			httpGet.addHeader(entry.getKey(),entry.getValue());
				}
        	}
        }
        HttpResponse response = httpClient.execute(httpGet);
        return response;
    }
	
	private static String getCookieFromResponse(@NonNull HttpResponse response) {
        Header[] responseHeader = response.getHeaders("Set-Cookie");
        int length = responseHeader.length;
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < length; i++) {
            if (responseHeader[i] != null) {
                if ("Set-Cookie".equals(responseHeader[i].getName())) {
                    int index = responseHeader[i].getValue().indexOf(";");
                    if (i == length - 1) {
                        stringBuilder.append(responseHeader[i].getValue().substring(0, index));
                    } else {
                        stringBuilder.append(responseHeader[i].getValue().substring(0, index) + "; ");
                    }
                }
            }
        }
        return stringBuilder.toString();
    }
    
    private static List<NameValuePair> getParams(JSONObject json){
    	List<NameValuePair> params = new ArrayList<NameValuePair>();
    	Set<String> keys = json.keySet();
    	for (String key : keys) {
    		NameValuePair pair = new BasicNameValuePair(key,json.getString(key));
    		params.add(pair);
		}
    	return params;
    }

	
	public static Map<String,String> getCookiesMap(String cookiesStr){
		Map<String,String>  cookies = new HashMap<String,String> ();
		String[] cookiesarray = cookiesStr.split(";");
		for (int i = 0; i < cookiesarray.length; i++) {
			if(cookiesarray[i].split("=").length==2) {
				cookies.put(cookiesarray[i].split("=")[0].trim(),cookiesarray[i].split("=")[1].trim());
			}
			else {
				cookies.put(cookiesarray[i].split("=")[0].trim(),"");
			}
		}
		return cookies;
	}

}