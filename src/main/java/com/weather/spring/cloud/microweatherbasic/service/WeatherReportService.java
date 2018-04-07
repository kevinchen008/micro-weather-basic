package com.weather.spring.cloud.microweatherbasic.service;

import com.weather.spring.cloud.microweatherbasic.vo.Weather;

public interface WeatherReportService {
    Weather getDataByCityId(String cityId);
}
