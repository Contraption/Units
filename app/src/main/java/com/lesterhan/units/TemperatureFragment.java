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
    private TextView temperatureUnitFromTextView;
    private SeekBar temperatureFromSeekBar;

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

        temperatureUnitFromTextView = (TextView) rootview.findViewById(R.id.temperatureUnitFromTextView);
        temperatureFromSeekBar = (SeekBar) rootview.findViewById(R.id.temperatureFromSeekBar);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_units, container, false);

        initializeContentWidgets(rootView);

        temperatureFromSeekBar.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {
            int seekBarProgress = 0;

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                seekBarProgress = progress;

                String temperatureText = seekBarProgress+"\u00b0 F";

                temperatureUnitFromTextView.setText(temperatureText);

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
