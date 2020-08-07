package com.prueba.pruebadog.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.prueba.pruebadog.R;
import com.prueba.pruebadog.adapters.DogImageListAdapter;

import java.util.ArrayList;
import java.util.List;


public class DogImgFragment extends Fragment {
    private List<String> urlImg=new ArrayList<>();
    private RecyclerView rViewImgList;

    public DogImgFragment() {
        // Required empty public constructor
    }


    public static DogImgFragment initImgFragment(ArrayList<String> imgList) {
        DogImgFragment fragment = new DogImgFragment();
        Bundle args = new Bundle();
        args.putStringArrayList("DOG_IMG",imgList);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            urlImg=getArguments().getStringArrayList("DOG_IMG");


        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_dog_img, container, false);
        urlImg=getArguments().getStringArrayList("DOG_IMG");
        rViewImgList=view.findViewById(R.id.imgRecycler); //se enlaza nuestro recycler
        DogImageListAdapter dogListAdapter = new DogImageListAdapter(urlImg, getActivity());

        rViewImgList.setHasFixedSize(true);
        rViewImgList.setAdapter(dogListAdapter);
        RecyclerView.LayoutManager a =new LinearLayoutManager(getActivity(),LinearLayoutManager.HORIZONTAL,false);
        rViewImgList.setLayoutManager(a);


        //aca se inicia el adapter y luego se inserta en el recycler
        //luego el linear layour es LinearLayoutManager layoutManage = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        return view;
    }
}
