package com.lesterhan.units;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SeekBar;
import android.widget.TextView;

import static android.widget.SeekBar.*;

public class temperatureFragment extends Fragment {

    private static final String ARG_SECTION_NUMBER = "section_number";
    private TextView temperatureFahrenheitTextView;
    private SeekBar temperatureFahrenheitSeekBar;

    private TextView temperatureCelsiusTextView;
    private SeekBar temperatureCelsiusSeekBar;

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

    }

    private String getTemperatureText(float temperature, char unit){
        return temperature+"\u00b0 "+unit;
    }

    private float getCelsiusFromFahrenheit(float fahrenheitTemperature) {
        float celsiusTemperature = (fahrenheitTemperature - 32) * 5 / 9;
        return celsiusTemperature;
    }

    private float getFahrenheitFromCelsius(float celsiusTemperature) {
        float fahrenheitTemperature = celsiusTemperature * 9 / 5 + 32;
        return fahrenheitTemperature;
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
                float temperatureInFahrenheit = getCelsiusFromFahrenheit((float) progress);

                temperatureFahrenheitTextView.setText(getTemperatureText((float) progress, 'F'));
                //temperatureCelsiusSeekBar.setProgress((int) temperatureInFahrenheit);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });

        temperatureCelsiusSeekBar.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                float temperatureInCelsius = getFahrenheitFromCelsius((float) progress);

                temperatureCelsiusTextView.setText(getTemperatureText((float) progress, 'C'));
                temperatureFahrenheitSeekBar.setProgress((int) temperatureInCelsius);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
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
