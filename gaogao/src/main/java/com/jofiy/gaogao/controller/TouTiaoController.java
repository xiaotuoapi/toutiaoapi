package com.jofiy.gaogao.controller;

import java.net.HttpURLConnection;
import java.util.HashMap;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.jofiy.gaogao.config.PropertiesConfig;

@Controller
@RequestMapping(path="/toutiao/api")
public class TouTiaoController {
  
  @Autowired
  private PropertiesConfig propertiesConfig;
  /**
   * 获取头条信息
   * @return
   */
  @RequestMapping(value = "/getinfos",method = {RequestMethod.GET},produces ="application/json;chartset=UTF-8")
  public static ResponseEntity<JSONArray> getAccessControl(){
    String getUrl ="https://www.toutiao.com/api/pc/feed/?min_behot_time=0&category=__all__&utm_source=toutiao&widen=1&tadrequire=true&as=A1856D8C9C5C702&cp=5DCC0C27D0A2BE1&_signature=UST5XAAgEBNbnJcCB.45d1Ek-UAAAzp";
    JSONArray jsonObjectTargets = new JSONArray();
    HttpGet httpGet = new HttpGet(getUrl);
    httpGet.setHeader("Accept", "application/json");
    httpGet.setHeader("Content-Type", "application/json");
    httpGet.setHeader("user-agent", "Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/71.0.3573.0 Safari/537.36");
    //httpGet.setHeader("x-requested-with", "XMLHttpRequest");
    //httpGet.setHeader("cookie", "tt_webid=6701092128251495940; UM_distinctid=16b4455ec2c562-09c46c5ffe22a7-704e2342-15f900-16b4455ec2d2a7; csrftoken=7a560577bff326b33d4349396f2145be; _ga=GA1.2.542147748.1560219720; login_flag=fdd1744e60566ec715ad28b1aba3e378; sessionid=3bbec1d0177fa28c669e8dbc0373aa98; sid_tt=3bbec1d0177fa28c669e8dbc0373aa98; sid_guard='3bbec1d0177fa28c669e8dbc0373aa98|1564970063|15552000|Sat\054 01-Feb-2020 01:54:23 GMT'; __tea_sdk__ssid=undefined; CNZZDATA1259612802=1062718090-1560216529-https%253A%252F%252Flanding.toutiao.com%252F%7C1564983627; tt_webid=6701092128251495940; WEATHER_CITY=%E5%8C%97%E4%BA%AC; s_v_web_id=74948bfeab18dff93c5c89b7af960e78; __tasessionId=x52dp3olo1573712113581");
    HttpClient httpClient = new DefaultHttpClient();
    try {
      HttpResponse response = httpClient.execute(httpGet);
      if (response.getStatusLine().getStatusCode() == HttpURLConnection.HTTP_OK) {
        String strResp  = EntityUtils.toString(response.getEntity());
        JSONObject jsonObject = JSONObject.parseObject(strResp);
        JSONArray dataJsonObject = jsonObject.getJSONArray("data");
        //(dataJsonObject.toString());
        for(int i=0;i<dataJsonObject.size();i++){
          HashMap jsonObjectTarget = new HashMap();
          jsonObjectTarget.put("title", dataJsonObject.getJSONObject(i).get("title"));
          jsonObjectTarget.put("source", dataJsonObject.getJSONObject(i).get("source"));
          jsonObjectTarget.put("comments_count", dataJsonObject.getJSONObject(i).get("comments_count"));
          jsonObjectTarget.put("image_url", "https:"+dataJsonObject.getJSONObject(i).get("image_url"));
          jsonObjectTarget.put("source_url", "https://www.toutiao.com"+dataJsonObject.getJSONObject(i).get("source_url"));
          jsonObjectTarget.put("abstract", dataJsonObject.getJSONObject(i).get("abstract"));
          jsonObjectTarget.put("media_avatar_url","https:"+dataJsonObject.getJSONObject(i).get("media_avatar_url"));
          jsonObjectTarget.put("chinese_tag", dataJsonObject.getJSONObject(i).get("chinese_tag"));
          jsonObjectTarget.put("tag_url", dataJsonObject.getJSONObject(i).get("tag_url"));
          jsonObjectTargets.add(jsonObjectTarget);
        }
        System.err.println(dataJsonObject.size());
        //System.err.println(dataJsonObject);
      }
    } catch (Exception e) {
      System.err.println(e.getMessage());
    }
    return ResponseEntity.ok().body(jsonObjectTargets);
  }
  
  @RequestMapping(method=RequestMethod.GET,value={"/get"},produces ="application/json;chartset=UTF-8")
  public void  testGet() {
    
  }
}
