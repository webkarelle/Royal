package karelle.env.royal.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

import karelle.env.royal.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class ChooseStoreFragment extends Fragment {


    public ChooseStoreFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Spinner spWhere;
        // Inflate the layout for this fragment
        View v=  inflater.inflate(R.layout.fragment_choose_store, container, false);



        spWhere = (Spinner) v.findViewById(R.id.spWhere);
        List<String> whereStore = new ArrayList<String>();
        whereStore.add("Store1 : \n"+"lQJEFHqlisgfLQSFGLQSKBLQsskB");
        whereStore.add("Store2 : lQJEFHqlisgfLQSFGLQSKBLQsskB");
        whereStore.add("Store3 : lQJEFHqlisgfLQSFGLQSKBLQsskB");



        ArrayAdapter<String> adapterWhere = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, whereStore);
        //Le layout par défaut est android.R.layout.simple_spinner_dropdown_item
        adapterWhere.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spWhere.setAdapter(adapterWhere);
        return v;
    }

}
