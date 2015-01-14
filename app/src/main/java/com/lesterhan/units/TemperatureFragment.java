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

public class TemperatureFragment extends Fragment {

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
    public static TemperatureFragment newInstance(int sectionNumber) {
        TemperatureFragment fragment = new TemperatureFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }

    public TemperatureFragment() {
    }

    private void initializeContentWidgets(View rootview){

        temperatureFahrenheitTextView = (TextView) rootview.findViewById(R.id.temperatureFahrenheitTextView);
        temperatureFahrenheitSeekBar = (SeekBar) rootview.findViewById(R.id.temperatureFahrenheitSeekbar);
        temperatureCelsiusTextView = (TextView) rootview.findViewById(R.id.temperatureCelsiusTextView);
        temperatureCelsiusSeekBar = (SeekBar) rootview.findViewById(R.id.temperatureCelsiusSeekBar);

        temperature = new Temperature(0f);
    }

    private String getTemperatureText(float temperature, char unit){
        return String.format("%.1f \u00b0 %c", temperature, unit);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_units, container, false);

        initializeContentWidgets(rootView);

        temperatureFahrenheitTextView.setText(getTemperatureText(temperature.getFahrenheit(), 'F'));
        temperatureCelsiusTextView.setText(getTemperatureText(temperature.getCelsius(), 'C'));

        temperatureFahrenheitSeekBar.setProgress((int)temperature.getFahrenheit()+40);
        temperatureCelsiusSeekBar.setProgress((int)temperature.getCelsius()+40);

        temperatureFahrenheitSeekBar.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if(isInteractingWithFahrenheit) {
                    int temperatureFahrenheit = progress - 40;
                    temperature.setTemperatureFahrenheit((float) temperatureFahrenheit);
                    temperatureFahrenheitTextView.setText(getTemperatureText(temperature.getFahrenheit(), 'F'));
                    temperatureCelsiusTextView.setText(getTemperatureText(temperature.getCelsius(), 'C'));
                }

                if(!isInteractingWithCelsius) {
                    temperatureCelsiusSeekBar.setProgress((int) temperature.getCelsius() + 40);
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
                if(isInteractingWithCelsius){
                    int temperatureCelsius = progress - 40;

                    temperature.setTemperatureCelsius((float)temperatureCelsius);
                    temperatureCelsiusTextView.setText(getTemperatureText(temperature.getCelsius(), 'C'));
                    temperatureFahrenheitTextView.setText(getTemperatureText(temperature.getFahrenheit(),'F'));
                }

                if(!isInteractingWithFahrenheit) {
                    temperatureFahrenheitSeekBar.setProgress((int) temperature.getFahrenheit() + 40);
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
