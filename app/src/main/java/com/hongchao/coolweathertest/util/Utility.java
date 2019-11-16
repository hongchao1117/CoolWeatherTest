package com.hongchao.coolweathertest.util;

import android.text.TextUtils;

import com.google.gson.Gson;
import com.hongchao.coolweathertest.db.City;
import com.hongchao.coolweathertest.db.County;
import com.hongchao.coolweathertest.db.Province;
import com.hongchao.coolweathertest.gson.Weather;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

//处理&分析json数据
public class Utility {
    //处理省级数据
    public static boolean handleProvinceResponse(String response){
        if (!TextUtils.isEmpty(response)){
            try {
                JSONArray allProvinces = new JSONArray(response);
                for (int i = 0; i < allProvinces.length(); i++) {
                    //取出每一个省份
                    JSONObject provinceObject = allProvinces.getJSONObject(i);
                    Province province = new Province();
                    //解析出省份的name并将其赋值给province对象
                    province.setProvinceName(provinceObject.getString("name"));
                    //解析出省份的id并将其赋值给province对象
                    province.setProvinceCode(provinceObject.getInt("id"));
                    //将这个省份保存到表中
                    province.save();
                }
                //处理成功返回 true
                return true;
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        //处理失败返回 false
        return false;
    }

    //处理市级数据
    public static boolean handleCityResponse(String response,int provinceId){
        if (!TextUtils.isEmpty(response)){
            try {
                JSONArray allCities = new JSONArray(response);
                for (int i = 0; i < allCities.length(); i++) {
                    JSONObject cityObject = allCities.getJSONObject(i);
                    City city = new City();
                    city.setCityName(cityObject.getString("name"));
                    city.setCityCode(cityObject.getInt("id"));
                    city.setProvinceId(provinceId);
                    city.save();
                }
                return true;
            } catch (JSONException e) {
                e.printStackTrace();
            }

        }
        return false;
    }

    //处理县级数据
    public static boolean handleCountyResponse(String response,int cityId){
        if (!TextUtils.isEmpty(response)){
            try {
                JSONArray allCounties = new JSONArray(response);
                for (int i = 0; i < allCounties.length(); i++) {
                    JSONObject countyObject = allCounties.getJSONObject(i);
                    County county = new County();
                    county.setCountyName(countyObject.getString("name"));
                    county.setWeatherId(countyObject.getString("weather_id"));
                    county.setCityId(cityId);
                    county.save();
                }
                return true;
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return false;
    }


    //将返回的JSON数据解析成Weather实体类
    public static Weather handleWeatherResponse(String response){
        try{
            JSONObject jsonObject = new JSONObject(response);
            JSONArray jsonArray = jsonObject.getJSONArray("HeWeather");
            String weatherContent = jsonArray.getJSONObject(0).toString();
            return new Gson().fromJson(weatherContent,Weather.class);
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return null;
    }

}
