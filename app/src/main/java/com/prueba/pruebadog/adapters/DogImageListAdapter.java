package com.prueba.pruebadog.adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.firebase.Timestamp;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreSettings;
import com.prueba.pruebadog.R;
import com.prueba.pruebadog.fragments.DogCollectionFragment;
import com.prueba.pruebadog.model.CollectionImageResponse;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;

public class DogImageListAdapter extends RecyclerView.Adapter<DogImageListAdapter.ImageHolder> {

    private List<String> mDataset;
    private Context mContext;
    private FirebaseFirestore db;

    public DogImageListAdapter(List<String> mDataset, Context mContext) {
        this.mDataset = mDataset;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public ImageHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view =LayoutInflater.from(parent.getContext()).inflate(R.layout.imagelist_adapter,parent,false);
        db=FirebaseFirestore.getInstance(); //aca se inicia firebase
        return new ImageHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ImageHolder holder, int position) {
        Glide.with(mContext).load(mDataset.get(position)).into(holder.imgDogHolder);
        holder.imgDogHolder.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                FirebaseFirestoreSettings settings = new FirebaseFirestoreSettings.Builder()
                        .setTimestampsInSnapshotsEnabled(true)
                        .build();
                db.setFirestoreSettings(settings);
                CollectionImageResponse collectionDog=new CollectionImageResponse(mDataset.get(position),Timestamp.now());
                db.collection("Collección").add(collectionDog);
                //HashMap<String,String> fav = new HashMap<>();
                //fav.put("timeStamp", Timestamp.now().toDate().toString());
                //fav.put("url",mDataset.get(position));
                //db.collection("favoritos").add(fav);//pojo va aca con list.get(position)))
                Toast.makeText(mContext, "Agregado a Favoritos", Toast.LENGTH_SHORT).show();
                return false;
            }
        });

        //longclik


        //mostrar lo del firebase
        //crear pojo de subida (con timestamp)
        //layout con fragment nuevo


        //crear firebase en o (2lineas de codigo en adapter), se llama de todos lados donde seusa
        //add url a lista de firebase (listafirebase.add())
        // instanciar firebase agregar onlick y dentro poner firebase



    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }



    public class ImageHolder extends RecyclerView.ViewHolder{

        ImageView imgDogHolder;

        public ImageHolder(@NonNull View itemView) {
            super(itemView);
            imgDogHolder=itemView.findViewById(R.id.imgDogXML);
        }
    }
}
