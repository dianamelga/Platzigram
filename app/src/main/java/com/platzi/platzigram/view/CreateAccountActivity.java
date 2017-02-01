package com.platzi.platzigram.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.platzi.platzigram.R;

public class CreateAccountActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);
        /*true or false que va a tener un boton de up*/
        showToolbar(getResources().getString(R.string.toolbar_title_createaccount),
                true);
    }

    public void showToolbar(String title, boolean upButton){
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
         /*para que se vea en versiones anteriores*/
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(title);
        /* en caso de que tenga boton de regreso hacemos que sea visible*/
        getSupportActionBar().setDisplayHomeAsUpEnabled(upButton);



    }
}
