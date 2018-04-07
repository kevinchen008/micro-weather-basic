package com.weather.spring.cloud.microweatherbasic.Controller;

import com.weather.spring.cloud.microweatherbasic.service.CityDataService;
import com.weather.spring.cloud.microweatherbasic.service.WeatherDataService;
import com.weather.spring.cloud.microweatherbasic.service.WeatherReportService;
import com.weather.spring.cloud.microweatherbasic.vo.Weather;
import com.weather.spring.cloud.microweatherbasic.vo.WeatherResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping("/report")
public class WeahterReportController {

    @Autowired
    private WeatherReportService weatherReportService;

    @Autowired
    private CityDataService cityDataService;

    @GetMapping("/cityId/{cityId}")
    public ModelAndView getWeatherByCityId(@PathVariable("cityId") String cityId, Model model) throws Exception {
        weatherReportService.getDataByCityId(cityId);
        model.addAttribute("title","天气预报");
        model.addAttribute("cityId",cityId);
        model.addAttribute("cityList",cityDataService.listCity());
        model.addAttribute("report",weatherReportService.getDataByCityId(cityId));
        return new ModelAndView("weather/report","reportModel",model);
    }

}
