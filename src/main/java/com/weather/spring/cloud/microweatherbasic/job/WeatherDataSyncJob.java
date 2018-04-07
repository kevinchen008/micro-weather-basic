package com.weather.spring.cloud.microweatherbasic.job;

import com.weather.spring.cloud.microweatherbasic.service.CityDataService;
import com.weather.spring.cloud.microweatherbasic.service.WeatherDataService;
import com.weather.spring.cloud.microweatherbasic.vo.City;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;

import java.util.List;

public class WeatherDataSyncJob extends QuartzJobBean {

    @Autowired
    private WeatherDataService weatherDataService;

    @Autowired
    private CityDataService cityDataService;

    @Override
    protected void executeInternal(JobExecutionContext context) throws JobExecutionException {

        List<City> cityList = null;
        try {
            cityList =  cityDataService.listCity();
        } catch (Exception e) {
            e.printStackTrace();
        }

        cityList.forEach(city -> {
            weatherDataService.weatherDataSync(city.getCityId());
        });
    }
}
