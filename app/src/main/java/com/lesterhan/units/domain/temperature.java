package com.lesterhan.units.domain;

public class Temperature {

    private float celsius;

    public Temperature(float celsius) {
        this.celsius = celsius;
    }

    public float getFahrenheit() {
        return celsius * 9f / 5f + 32f;
    }

    public void setTemperatureCelsius(float temperatureCelsius) {
        this.celsius = temperatureCelsius;
    }

    public void setTemperatureFahrenheit(float temperatureFahrenheit) {
        this.celsius = (temperatureFahrenheit - 32f) * 5f / 9f;
    }

    public float getCelsius() {
        return this.celsius;
    }
}
