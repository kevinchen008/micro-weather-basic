package com.weather.spring.cloud.microweatherbasic.service;

import com.weather.spring.cloud.microweatherbasic.util.XmlBuilder;
import com.weather.spring.cloud.microweatherbasic.vo.City;
import com.weather.spring.cloud.microweatherbasic.vo.CityList;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.List;

@Service
public class CityDataServiceImpl implements CityDataService {
    @Override
    public List<City> listCity() throws Exception {
        //读取xml文件
        Resource resource = new ClassPathResource("citylist.xml");
        BufferedReader bf = new BufferedReader(new InputStreamReader(resource.getInputStream(),"UTF-8"));
        StringBuffer buf = new StringBuffer();
        String line = "";
        while((line = bf.readLine())!=null){
            buf.append(line);
        }
        bf.close();
        //xml文件转java

        CityList cityList = (CityList) XmlBuilder.xmlStrToObject(CityList.class,buf.toString());
        return cityList.getCityList();
    }
}
