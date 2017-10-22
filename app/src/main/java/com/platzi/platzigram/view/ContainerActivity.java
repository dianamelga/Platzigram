package com.platzi.platzigram.view;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;

import com.platzi.platzigram.R;
import com.platzi.platzigram.post.view.HomeFragment;
import com.platzi.platzigram.view.fragment.ProfileFragment;
import com.platzi.platzigram.view.fragment.SearchFragment;

public class ContainerActivity extends AppCompatActivity {

    private BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_container);

        /* declaro mi bottombar (o nav.View) */

        bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottom_navigation_view);


        /*coloco que mi tab de home este seleccionado por default*/

        HomeFragment homeFragment = new HomeFragment();
        callFragment(homeFragment);
        //bottomNavigationView.getMenu().findItem(R.id.item_home).setChecked(true);


        /* indico que accion tomar al tocar cada pestanha o tab*/

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                int tab = item.getItemId();
                switch (tab) {
                    case (R.id.item_home):
                        HomeFragment homeFragment = new HomeFragment();
                        callFragment(homeFragment);
                        break;

                    case (R.id.item_search):
                        SearchFragment searchFragment = new SearchFragment();
                        callFragment(searchFragment);
                        break;

                    case (R.id.item_profile):
                        ProfileFragment profileFragment = new ProfileFragment();
                        callFragment(profileFragment);
                        break;

                    default:
                        break;

                }
                return false;
            }
        });
    }

    public void callFragment(Fragment fragment){

        getSupportFragmentManager().beginTransaction().replace(R.id.container,fragment)
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                .addToBackStack(null).commit();

    }

}
