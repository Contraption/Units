package com.lesterhan.units;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SeekBar;
import android.widget.TextView;

import com.lesterhan.units.domain.Temperature;

import static android.widget.SeekBar.*;

public class temperatureFragment extends Fragment {

    private static final String ARG_SECTION_NUMBER = "section_number";
    private TextView temperatureFahrenheitTextView;
    private SeekBar temperatureFahrenheitSeekBar;

    private TextView temperatureCelsiusTextView;
    private SeekBar temperatureCelsiusSeekBar;

    private Temperature temperature;

    private boolean isInteractingWithFahrenheit = false;
    private boolean isInteractingWithCelsius = false;

    /**
     * Returns a new instance of this fragment for the given section
     * number.
     */
    public static temperatureFragment newInstance(int sectionNumber) {
        temperatureFragment fragment = new temperatureFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }

    public temperatureFragment() {
    }

    private void initializeContentWidgets(View rootview){

        temperatureFahrenheitTextView = (TextView) rootview.findViewById(R.id.temperatureUnitFromTextView);
        temperatureFahrenheitSeekBar = (SeekBar) rootview.findViewById(R.id.temperatureFromSeekBar);
        temperatureCelsiusTextView = (TextView) rootview.findViewById(R.id.temperatureUnitToTextView);
        temperatureCelsiusSeekBar = (SeekBar) rootview.findViewById(R.id.temperatureToSeekBar);

        temperature = new Temperature(0f);
    }

    private String getTemperatureText(float temperature, char unit){
        return temperature+"\u00b0 "+unit;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_units, container, false);

        initializeContentWidgets(rootView);

        temperatureFahrenheitTextView.setText(getTemperatureText(0, 'F'));
        temperatureCelsiusTextView.setText(getTemperatureText(0, 'C'));

        temperatureFahrenheitSeekBar.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                temperature.setTemperatureFahrenheit(progress);
                temperatureFahrenheitTextView.setText(getTemperatureText((float) progress, 'F'));

                if(!isInteractingWithCelsius) {
                    temperatureCelsiusSeekBar.setProgress((int) temperature.getCelsius());
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                isInteractingWithFahrenheit = true;
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                isInteractingWithFahrenheit = false;
            }
        });

        temperatureCelsiusSeekBar.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                temperature.setTemperatureCelsius((float)progress);
                temperatureCelsiusTextView.setText(getTemperatureText((float) progress, 'C'));

                if(!isInteractingWithFahrenheit) {
                    temperatureFahrenheitSeekBar.setProgress((int) temperature.getFahrenheit());
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                isInteractingWithCelsius = true;
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                isInteractingWithCelsius = false;
            }
        });

        return rootView;
    }


    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        ((UnitsActivity) activity).onSectionAttached(
                getArguments().getInt(ARG_SECTION_NUMBER));
    }
}
