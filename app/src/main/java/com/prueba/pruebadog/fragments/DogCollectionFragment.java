package com.prueba.pruebadog.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.prueba.pruebadog.R;
import com.prueba.pruebadog.adapters.DogFavAdapter;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class DogCollectionFragment extends Fragment {

    private ArrayList<String> ImgFavsList=new ArrayList<>();
    private RecyclerView rViewFavs;



    public DogCollectionFragment() {
        // Required empty public constructor
    }
    public static DogCollectionFragment initCollectionFragment(ArrayList<String> imgList) {
        DogCollectionFragment fragment = new DogCollectionFragment();
        Bundle args = new Bundle();
        args.putStringArrayList("DOG_FAV",imgList);
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            ImgFavsList=getArguments().getStringArrayList("DOG_FAV");


        }
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =inflater.inflate(R.layout.fragment_dog_collection, container, false);
        ImgFavsList=getArguments().getStringArrayList("DOG_FAV");
        rViewFavs=view.findViewById(R.id.recyclerCollection);
        DogFavAdapter adapterFav = new DogFavAdapter(ImgFavsList,getActivity());//aca creo que estoy metiendo otra lista, y en el adapter ya tiene una lista llena
        rViewFavs.setAdapter(adapterFav);
        StaggeredGridLayoutManager gridStagLayoutManager = new StaggeredGridLayoutManager(3,StaggeredGridLayoutManager.VERTICAL);
        rViewFavs.setLayoutManager(gridStagLayoutManager);
        return view;
    }
}
