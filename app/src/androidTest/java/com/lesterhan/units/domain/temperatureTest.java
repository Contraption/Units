package com.lesterhan.units.domain;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class temperatureTest {

    @Test
    public void shouldReturnFahrenheit33point8when1degreesCelsius(){
        Temperature temperature = new Temperature(1f);

        float actualTemperatureInFahrenheit = temperature.getFahrenheit();
        float expectedTemperature = 33.8f;

        assertThat(actualTemperatureInFahrenheit, is(expectedTemperature));
    }


}