package com.vankhai.weather.service.base;

import java.io.IOException;

public abstract class ApiService {
    public abstract void getForecastRequest(String paramRequest) throws IOException;

    public abstract void requestRecommendLocationNameWithPattern(String pattern);
}
