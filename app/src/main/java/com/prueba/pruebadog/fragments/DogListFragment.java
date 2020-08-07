package com.prueba.pruebadog.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.prueba.pruebadog.MainActivity;
import com.prueba.pruebadog.R;
import com.prueba.pruebadog.adapters.DogListAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class DogListFragment extends Fragment {
    //se declaran los datos que va a llevar el fragmento (como el fragment contiene un recycler view, se tiene que declarar todo lo que se declaro en el adapter como variable de clase
    private List<String> fdogLIst; //solo este esta declarado en el adapter
    private RecyclerView rViewList;



    public DogListFragment() {
        // Required empty public constructor
    }

    public static DogListFragment initFragment (ArrayList<String> list){ //este metodo va a recibir los datos en el main, por lo tanto aca se parte.

        DogListFragment fragment = new DogListFragment();
        Bundle args = new Bundle();
        args.putStringArrayList("DOGS", list);
        fragment.setArguments(args);
        return fragment;

    }

    @Override
    public void onCreate(Bundle savedInstanceState) { //cuando se crea el fragment, se le pasan los datos que va a tener la lista
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            fdogLIst = getArguments().getStringArrayList("DOGS");
        }
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_list, container, false); //se carga el layout donde esta el fragment
        ArrayList<String>list =getArguments().getStringArrayList("DOGS"); //se obtienen los datos del onCreate ("DOGS")

        rViewList=view.findViewById(R.id.listRecycler); //se inicializa nuestro recycler y su adapter
        DogListAdapter dogListAdapter = new DogListAdapter(list, getActivity());

        rViewList.setHasFixedSize(true);
        rViewList.setAdapter(dogListAdapter);

        rViewList.setLayoutManager(new LinearLayoutManager(getActivity()));
        return view;
    }
}
