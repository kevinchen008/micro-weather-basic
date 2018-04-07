package com.weather.spring.cloud.microweatherbasic;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//http://wthrcdn.etouch.cn/weather_mini?city=深圳
//http://wthrcdn.etouch.cn/weather_mini?citykey=101280601
//http://wthrcdn.etouch.cn/js/citylist.xml         101010100,101010200

@SpringBootApplication
public class MicroWeatherBasicApplication {

	public static void main(String[] args) {
		SpringApplication.run(MicroWeatherBasicApplication.class, args);
	}
}
