package com.weather.spring.cloud.microweatherbasic.service;

import com.weather.spring.cloud.microweatherbasic.vo.City;

import java.util.List;

public interface CityDataService {
    public List<City> listCity() throws  Exception;
}
