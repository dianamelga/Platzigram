package com.platzi.platzigram.post.view;


import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.platzi.platzigram.R;
import com.platzi.platzigram.adapter.PictureAdapterRecyclerView;
import com.platzi.platzigram.model.Picture;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {

    private static final int REQUEST_CAMERA = 1;
    private FloatingActionButton fabCamera;

    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container,false);

        showToolbar(getResources().getString(R.string.home), false, view);
        RecyclerView picturesRecycler = (RecyclerView) view.findViewById(R.id.picture_recycler);

        fabCamera = (FloatingActionButton) view.findViewById(R.id.fabCamera);
        /*darle forma*/

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);

        picturesRecycler.setLayoutManager(linearLayoutManager);

        PictureAdapterRecyclerView pictureAdapterRecyclerView = new PictureAdapterRecyclerView(
                buildPictures(),R.layout.cardview_picture,getActivity());

        picturesRecycler.setAdapter(pictureAdapterRecyclerView);


        fabCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                takePicture();
            }
        });

        return view;
    }

    private void takePicture() {

        Intent intentTakePicture = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        //controlo si tiene camara
        if (intentTakePicture.resolveActivity(getActivity().getPackageManager()) != null){
            startActivityForResult(intentTakePicture, REQUEST_CAMERA);
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_CAMERA && resultCode == getActivity().RESULT_OK){
            Log.d("HomeFragment", "CAMERA OK!!");
        }else{
            Log.d("HomeFragment", "CAMERA NO OK :(");
        }

    }

    public void showToolbar(String title, boolean upButton, View view){
        Toolbar toolbar = (Toolbar) view.findViewById(R.id.toolbar);
         /*para que se vea en versiones anteriores*/
        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle(title);
        /* en caso de que tenga boton de regreso hacemos que sea visible*/
        ((AppCompatActivity) getActivity()).getSupportActionBar()
                .setDisplayHomeAsUpEnabled(upButton);



    }

    public ArrayList<Picture> buildPictures(){
        ArrayList<Picture> pictures = new ArrayList<>();
        pictures.add(new Picture("http://androcode.es/2014/11/entendiendo-material-design/","Juan Perez","10 dias", "60 Me gusta"));
        pictures.add(new Picture("http://www.novalandtours.com/images/guide/guilin.jpg","David Diaz","4 dias", "7 Me gusta"));
        pictures.add(new Picture("http://www.enjoyart.com/library/landscapes/tuscanlandscapes/large/Tuscan-Bridge--by-Art-Fronckowiak-.jpg","Uriel Ramos","4 dias", "3 Me gusta"));
        pictures.add(new Picture("http://www.educationquizzes.com/library/KS3-Geography/river-1-1.jpg","Diana Ramos","4 dias", "3 Me gusta"));
        pictures.add(new Picture("http://androcode.es/2014/11/entendiendo-material-design/","Ivan Ramos","4 dias", "3 Me gusta"));
        pictures.add(new Picture("http://androcode.es/2014/11/entendiendo-material-design/","Luis Ramos","4 dias", "3 Me gusta  "));

        return pictures;
    }
}
