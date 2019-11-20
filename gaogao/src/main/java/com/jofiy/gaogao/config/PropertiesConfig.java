package com.jofiy.gaogao.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@PropertySource("classpath:application.properties")
public class PropertiesConfig {
  
  @Value("${toutiao.infourl}")
  public String toutaoUrl;

  
  public String getToutaoUrl() {
    return toutaoUrl;
  }

  
  public void setToutaoUrl(String toutaoUrl) {
    this.toutaoUrl = toutaoUrl;
  }


  public PropertiesConfig() {
    super();
  }


  @Override
  public String toString() {
    return "PropertiesConfig [toutaoUrl=" + toutaoUrl + "]";
  }
  
  
}
