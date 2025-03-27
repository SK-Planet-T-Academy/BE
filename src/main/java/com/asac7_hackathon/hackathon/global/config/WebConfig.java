package com.asac7_hackathon.hackathon.global.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
  @Override
  public void addCorsMappings(CorsRegistry registry) {
    registry.addMapping("/api/**")
        .allowedOrigins("http://localhost:3000", "http://10.10.140.210:3000") // 필요시 프론트 주소 추가
        .allowedMethods("GET", "POST", "PUT", "DELETE")
        .allowCredentials(true);
  }
}
