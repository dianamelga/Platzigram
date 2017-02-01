package com.platzi.platzigram.view.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.platzi.platzigram.R;
import com.platzi.platzigram.adapter.PictureAdapterRecyclerView;
import com.platzi.platzigram.model.Picture;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class SearchFragment extends Fragment {


    public SearchFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_search, container, false);

        RecyclerView searchRecycler = (RecyclerView) view.findViewById(R.id.search_recycler);

        /*darle forma*/

        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 2, GridLayoutManager.VERTICAL, false);
/*
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);*/

        searchRecycler.setLayoutManager(gridLayoutManager);

        PictureAdapterRecyclerView pictureAdapterRecyclerView = new PictureAdapterRecyclerView(
                buildPictures(),R.layout.cardview_picture,getActivity());

        searchRecycler.setAdapter(pictureAdapterRecyclerView);


        return view;

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
