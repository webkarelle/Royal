package karelle.env.royal.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TimePicker;

import karelle.env.royal.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class TimeFragment extends Fragment {
    String hourOfDayString;
    String minuteString;


    public TimeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        TimePicker mTimePicker;
        Button btnSaveTime;
        View v = inflater.inflate(R.layout.fragment_time, container, false);
        btnSaveTime=(Button)v.findViewById(R.id.btnSaveTime);
        btnSaveTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().getSupportFragmentManager().beginTransaction().remove(getParentFragment());
            }
        });

        mTimePicker = (TimePicker) v.findViewById(R.id.timePicker);
        mTimePicker.setIs24HourView(true);
        mTimePicker.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {
            @Override
            public void onTimeChanged(TimePicker view, int hourOfDay, int minute) {
                hourOfDayString= String.valueOf(hourOfDay);
                minuteString=String.valueOf(minute);
            }
        });

        return v;
    }

}
