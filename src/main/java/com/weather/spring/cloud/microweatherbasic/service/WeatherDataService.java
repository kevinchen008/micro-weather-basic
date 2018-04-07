package com.weather.spring.cloud.microweatherbasic.service;

import com.weather.spring.cloud.microweatherbasic.vo.WeatherResponse;
import org.springframework.stereotype.Service;

public interface WeatherDataService {

    WeatherResponse getDataByCityId(String cityId);


    WeatherResponse getDataByCityName(String cityName);

    void weatherDataSync(String cityId);
}
