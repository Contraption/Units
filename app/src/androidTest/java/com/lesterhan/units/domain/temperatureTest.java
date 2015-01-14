package com.lesterhan.units.domain;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

@RunWith(RobolectricTestRunner.class)
public class TemperatureTest {

    @Test
    public void shouldReturnFahrenheit33point8when1degreesCelsius(){
        Temperature temperature = new Temperature(1f);

        float actualTemperatureInFahrenheit = temperature.getFahrenheit();
        float expectedTemperature = 33.8f;

        assertThat(actualTemperatureInFahrenheit, is(expectedTemperature));
    }


}