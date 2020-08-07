package com.prueba.pruebadog;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.prueba.pruebadog.fragments.DogCollectionFragment;
import com.prueba.pruebadog.fragments.DogImgFragment;
import com.prueba.pruebadog.fragments.DogListFragment;
import com.prueba.pruebadog.model.BreedImageResponse;
import com.prueba.pruebadog.model.BreedListResponse;
import com.prueba.pruebadog.retrofit.DogApi;
import com.prueba.pruebadog.retrofit.RetrofitClient;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private ArrayList<String> responseApi =new ArrayList<String>();
    private TextView textViewMain;
    private Button buttonCollection;
    private FirebaseFirestore db=FirebaseFirestore.getInstance(); //SE INSTANCIA FIREBASE PARA PODER USAR LA BASE DE DATOS

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //SE INICIAN LAS VISTAS DE BOTON, IMAGEN ESTATICA Y TEXTO ESTATICO
        initViews();

        //SE CARGA LA LISTA "carga" CON LOS DATOS DE FIREBASE



        //PRIMERA LLAMADA PARA OBTENER LISTA DE RAZAS
        DogApi service = RetrofitClient.getRetrofitInstance().create(DogApi.class);
        Call<BreedListResponse>call =service.getBreedListApi();
        call.enqueue(new Callback<BreedListResponse>() {
            @Override
            public void onResponse(Call<BreedListResponse> call, Response<BreedListResponse> response) {
                responseApi=response.body().getBreedList();
                initFragment();
            }
            @Override
            public void onFailure(Call<BreedListResponse> call, Throwable t) {
                failureToast();
            }
        });


        //CLICK LISTENER DEL BOTON, MUESTRA LOS FAVORITOS EN EL FRAMELAYOUT DEL MAIN
        buttonCollection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //sera posible actualizar de inmediato la lista de favoritos???
                Log.i("kev","llega al onclick despues de cargar");
                setMainTextView("Colección");
                getCollectionImg();


            }
        });
    }

    public ArrayList<String> getResponseListApi(){
        DogApi service = RetrofitClient.getRetrofitInstance().create(DogApi.class);
        Call<BreedListResponse>call =service.getBreedListApi();
        call.enqueue(new Callback<BreedListResponse>() {
            @Override
            public void onResponse(Call<BreedListResponse> call, Response<BreedListResponse> response) {
                responseApi=response.body().getBreedList();
                //initFragment();
            }
            @Override
            public void onFailure(Call<BreedListResponse> call, Throwable t) {
                failureToast();
            }
        });

        return responseApi;
    }

    //INICIA EL PRIMER FRAGMENTO CON LA LISTA DE LAS RAZAS, SE INICIA AL EMPEZAR LA ACTIVITY MAIN
    public void initFragment(){

        DogListFragment detailFragment = DogListFragment.initFragment(responseApi);

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.frameLayoutMain, detailFragment, "DetailFragment")
                .commit();
    }

    //TOAST PARA ONFAILURE
    public void failureToast(){
        Toast.makeText(MainActivity.this,"cagaste",Toast.LENGTH_SHORT).show();
    }

    //SEGUNDA LLAMADA A LA DOGAPI, PARA OBTENER LAS IMAGENES DE LA RAZA CLICKEADA
    public void onResponseImage(String breed){
        DogApi secondCall =RetrofitClient.getRetrofitInstance().create(DogApi.class);
        Call<BreedImageResponse> callImg = secondCall.getBreedImagesApi(breed);
        ProgressBar loadImg = findViewById(R.id.progressBar);
        loadImg.setVisibility(View.VISIBLE);
        callImg.enqueue(new Callback<BreedImageResponse>() {
            @Override
            public void onResponse(Call<BreedImageResponse> call, Response<BreedImageResponse> response) {

                //SE CARGA UNA LISTA CON LAS URL DE LAS IMAGENES DE LA RAZA SELECCIONADA
                ArrayList<String> imgUrl = response.body().getImageUrl();


                //SE INICIA EL FRAGMENTO DE IMAGENES CON ORIENTACION HORIZONTAL
                DogImgFragment imgFragment = DogImgFragment.initImgFragment(imgUrl);
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.frameLayoutMain, imgFragment)
                        .addToBackStack("Dog")
                        .commit();
                loadImg.setVisibility(View.INVISIBLE);

            }

            @Override
            public void onFailure(Call<BreedImageResponse> call, Throwable t) {
                failureToast();
                loadImg.setVisibility(View.INVISIBLE);

            }
        });

    }

    //ENLACE CON VISTAS DEL LAYOUT
    public void initViews (){
        textViewMain = findViewById(R.id.chosenBreed);

        buttonCollection =findViewById(R.id.coleccion);


        ImageView portrait = findViewById(R.id.dogPortrait);
        Glide.with(this).load("https://www.kindpng.com/picc/m/62-624510_dog-pet-clip-art-group-of-dogs-cartoon.png").into(portrait);

    }

    //SETEA EL TEXTO EN MAIN, DEPENDIENDO DE LA RAZA QUE CLIKEES
    public void setMainTextView(String choosenBreed){
        textViewMain.setText(choosenBreed);
    }

    //DEVUELVE LA LISTA DE IMAGENES FAVORITAS DE FIREBASE
    public void getCollectionImg (){
        Log.i("kev","se inicia el metodo getCollectionImg()");
        ArrayList<String> imgCollection=new ArrayList<>();
        db.collection("Collección").orderBy("timestamp", Query.Direction.DESCENDING).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) { //con todo esto, se carga nuestra lista con los datos de firebase
                Log.i("kev","el onComplete funca");
                if(task.isSuccessful()){
                    Log.i("kev","task succesful");
                    QuerySnapshot result =task.getResult();
                    List<DocumentSnapshot> documents =result.getDocuments();
                    for(QueryDocumentSnapshot e : task.getResult()){
                        imgCollection.add((String) e.getData().get("url"));
                        Log.i("kev","se cargan los datos con el for each a la lista");

                    }
                    DogCollectionFragment imgCollectionFragment = DogCollectionFragment.initCollectionFragment((ArrayList<String>) imgCollection);
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.frameLayoutMain, imgCollectionFragment)
                            .addToBackStack("Dog")
                            .commit();

                }
            }
        });
    }
}
