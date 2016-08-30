package karelle.env.projetfinanneetreize.fragments;


import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import karelle.env.royal.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentShowOD extends DialogFragment {


    public FragmentShowOD() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        TextView tvIDO;
        TextView tvIDOd;
        TextView tvNameOD;
        Button btnQuitFragment;

        View v= inflater.inflate(R.layout.fragment_fragment_show_od, container, false);
        tvIDO = (TextView)v.findViewById(R.id.tvIDO);
        tvIDOd = (TextView)v.findViewById(R.id.tvIDOd);
        tvNameOD = (TextView)v.findViewById(R.id.tvNameOD);
        tvIDO.setText("salut");
        tvIDOd.setText("les amis");
        tvNameOD.setText("coucou");

        btnQuitFragment = (Button)v.findViewById(R.id.btnQuitFragment);
        btnQuitFragment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getContext(), "je suis dans le fragment", Toast.LENGTH_SHORT).show();
            }
        });


        Toast.makeText(getContext(), "je suis dans le fragment", Toast.LENGTH_SHORT).show();
        return v;
    }

}

