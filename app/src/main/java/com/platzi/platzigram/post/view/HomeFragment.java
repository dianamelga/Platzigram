package com.platzi.platzigram.post.view;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.content.FileProvider;
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

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {

    private static final int REQUEST_CAMERA = 1;
    private FloatingActionButton fabCamera;
    private String photoPathTemp = "";

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

            //creo un espacio en memoria para la foto
            File photoFile = null;

            try {
                photoFile = createImageFile();
            }catch(Exception e){
                e.printStackTrace();
            }

            if (photoFile != null) {
                //obtiene lo que tenemos en file_path.xml
                Uri photoUri = FileProvider.getUriForFile(getActivity(),"com.platzi.platzigram",  photoFile);

                intentTakePicture.putExtra(MediaStore.EXTRA_OUTPUT, photoUri);
                //llama a la camara
                startActivityForResult(intentTakePicture, REQUEST_CAMERA);
            }
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_CAMERA && resultCode == getActivity().RESULT_OK){
            Log.d("HomeFragment", "CAMERA OK!!");
            Intent intent = new Intent(getActivity(), NewPostActivity.class);
            intent.putExtra("PHOTO_PATH_TEMP", photoPathTemp);
            startActivity(intent);
        }else{
            Log.d("HomeFragment", "CAMERA NO OK :(");
        }

    }

    private File createImageFile() throws IOException {
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HH-mm-ss").format(new Date());
        String imageFileName = "JPEG_"+timeStamp+"_";
        File storageDir = getActivity().getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        
        File photo = File.createTempFile(imageFileName, ".jpg", storageDir);
        
        photoPathTemp = "file://"+ photo.getAbsolutePath();
        return photo;
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
