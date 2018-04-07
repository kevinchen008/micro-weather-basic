package com.weather.spring.cloud.microweatherbasic.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.weather.spring.cloud.microweatherbasic.vo.WeatherResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

@Service
public class WeatherDataServiceImpl implements WeatherDataService {

    Logger logger = LoggerFactory.getLogger(WeatherDataServiceImpl.class);

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Override
    public WeatherResponse getDataByCityId(String cityId) {
        String uri="http://wthrcdn.etouch.cn/weather_mini?citykey="+cityId;
        return  doWeather(uri);
    }

    @Override
    public WeatherResponse getDataByCityName(String cityName) {
        String uri="http://wthrcdn.etouch.cn/weather_mini?city="+cityName;
        return  doWeather(uri);
    }

    public WeatherResponse doWeather(String uri){
        String body ="";
        ObjectMapper mapper = new ObjectMapper();
        WeatherResponse resp = null;

        ValueOperations<String,String> ops =  stringRedisTemplate.opsForValue();

        //先查缓存
        if(stringRedisTemplate.hasKey(uri)){
            logger.info(" 查询缓存");
            body = ops.get(uri);
        }else{
            ResponseEntity<String> responseString =  restTemplate.getForEntity(uri,String.class);
            if(responseString.getStatusCodeValue()==200) {
                body = responseString.getBody();
            }

            logger.info("写入缓存");
            ops.set(uri,body,10, TimeUnit.SECONDS);
        }

        try {
            resp =  mapper.readValue(body,WeatherResponse.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return resp;
    }

    @Override
    public void weatherDataSync(String cityId) {
        String uri="http://wthrcdn.etouch.cn/weather_mini?citykey="+cityId;
        saveData(uri);
    }


    private void saveData(String uri){
        ValueOperations<String,String> ops =  stringRedisTemplate.opsForValue();
        String body ="";
        ResponseEntity<String> responseString =  restTemplate.getForEntity(uri,String.class);
        if(responseString.getStatusCodeValue()==200) {
            body = responseString.getBody();
        }

        logger.info("写入缓存");
        ops.set(uri,body,10, TimeUnit.SECONDS);
    }
}
