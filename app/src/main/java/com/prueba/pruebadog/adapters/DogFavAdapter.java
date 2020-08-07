package com.prueba.pruebadog.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.prueba.pruebadog.R;

import java.util.ArrayList;
import java.util.List;

public class DogFavAdapter extends RecyclerView.Adapter<DogFavAdapter.FavsHolder> {

    private List<String> mDataset;
    private Context mContext;
    FirebaseFirestore db;

    public DogFavAdapter(List<String> mDataset, Context mContext) {
        this.mDataset = mDataset;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public FavsHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.favs_adapter,parent,false);
        db=FirebaseFirestore.getInstance();
        return new FavsHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FavsHolder holder, int position) {
        //hay que cargar el dataset con los datos obtenidos desde firebase, no se si se hace aca o en otro lado
       /* db.collection("Colleción").orderBy("timestamp", Query.Direction.DESCENDING).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
    @Override
    public void onComplete(@NonNull Task<QuerySnapshot> task) { //con todo esto, se carga nuestra lista con los datos de firebase
        if(task.isSuccessful()){
         QuerySnapshot result =task.getResult();
         List<DocumentSnapshot> documents =result.getDocuments();
            for(QueryDocumentSnapshot e : result){
                mDataset.add((String) e.getData().get("url"));
            }
        }
    }
        });*/
        Glide.with(mContext).load(mDataset.get(position)).into(holder.favImgHolder);

    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }

    public class FavsHolder extends RecyclerView.ViewHolder{
        private ImageView favImgHolder;
        public FavsHolder(@NonNull View itemView) {
            super(itemView);
            favImgHolder=itemView.findViewById(R.id.imageViewFavs);
        }
    }
}
