package com.vankhai.weather.model;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.xml.sax.Locator;

import java.util.LinkedList;
import java.util.List;

public class LocationRecommend {
    private final String name;
    private final String country;

    public LocationRecommend(String name, String country) {
        this.name = name;
        this.country = country;
    }

    public String getName() {
        return name;
    }

    public String getCountry() {
        return country;
    }

    public static List<LocationRecommend> listFromJson(String json) throws JSONException {
        LinkedList<LocationRecommend> locationRecommends = new LinkedList<>();

        JSONArray locationsJsonArray = new JSONArray(json);
        for (int i = 0; i < locationsJsonArray.length(); i++) {
            JSONObject locationObject = locationsJsonArray.getJSONObject(i);
            String locationName = locationObject.getString("name");
            String countryName = locationObject.getString("country");

            locationRecommends.add(new LocationRecommend(locationName, countryName));
        }


        return locationRecommends;
    }

    public static List<String> mapRecommend(List<LocationRecommend> recommends) {
        LinkedList<String> results = new LinkedList<>();

        for (int i = 0; i < recommends.size(); i++) {
            results.add(recommends.get(i).getConcatNameAndCountry());
        }

        return results;
    }

    public String getConcatNameAndCountry() {
        return name + " - " + country;
    }
}
