package com.alexanr.demin.test_app.feature;

public class WeatherItem {
    private String city;
    private String temperature;
    private String pressure;
    private String humidity;
    private String date;
/*    private boolean isPressureChecked;
    private boolean isHumidityChecked;*/

    public WeatherItem(String city, String temperature, String date) {
        this.city = city;
        this.temperature = temperature;
        this.date = date;
    }

    public String getCity() {
        return city;
    }

    public String getTemperature() {
        return temperature;
    }

    public String getPressure() {
        return pressure;
    }

    public String getHumidity() {
        return humidity;
    }

    public String getDate() {
        return date;
    }

    public void setPressure(String pressure) {
        this.pressure = pressure;
    }

    public void setHumidity(String humidity) {
        this.humidity = humidity;
    }
}
