package com.lesterhan.units.domain;

public class Temperature {

    private float celsius;

    public Temperature(float celsius) {
        this.celsius = celsius;
    }

    public float getFahrenheit() {
        return celsius * 9 / 5 + 32;
    }

    public void setTemperatureCelsius(float temperatureCelsius) {
        this.celsius = temperatureCelsius;
    }

    public void setTemperatureFahrenheit(float temperatureFahrenheit) {
        this.celsius = (temperatureFahrenheit - 32) * 5 / 9;
    }

    public float getCelsius() {
        return this.celsius;
    }
}
