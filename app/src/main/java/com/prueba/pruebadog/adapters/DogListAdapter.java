package com.prueba.pruebadog.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.prueba.pruebadog.MainActivity;
import com.prueba.pruebadog.R;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class DogListAdapter extends RecyclerView.Adapter<DogListAdapter.ListHolder>{

    private List<String> mBreedList;
    private Context mContext;

    public DogListAdapter(List<String> mBreedList, Context mContext) {
        this.mBreedList = mBreedList;
        this.mContext = mContext;
    }


    @NonNull
    @Override
    public ListHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_adapter,parent,false);
        return new ListHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ListHolder holder, int position) {

        holder.singleTextBreed.setText(mBreedList.get(position));//aca se asigna cada valor que se va recibiendo en la lista a nuestro texto que esta en el xml list_adapter
        holder.singleTextBreed.setOnClickListener(v -> {

            Toast.makeText(mContext,"Mostrando " + mBreedList.get(position),Toast.LENGTH_SHORT).show();
            ((MainActivity)mContext).setMainTextView(mBreedList.get(position));
            ((MainActivity)mContext).onResponseImage(mBreedList.get(position)); //se hace la llamda, pero no se muestran las imagenes, creo que hay un problema ccon el fragment o adapter, fragment mas.

        });

    }

    @Override
    public int getItemCount() {
        return mBreedList.size();
    }

    public class ListHolder extends RecyclerView.ViewHolder{
        private TextView singleTextBreed;

        public ListHolder(@NonNull View itemView) {
            super(itemView);
            singleTextBreed=itemView.findViewById(R.id.singleListAdapterXML);
        }
    }
}
