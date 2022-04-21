package com.test.a050034009utsgaa;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;
   private BottomNavigationView.OnNavigationItemReselectedListener navigation = new BottomNavigationView.OnNavigationItemReselectedListener() {
       @Override
       public void onNavigationItemReselected(MenuItem item) {
           Fragment f = null;
           switch (item.getItemId()){
               case R.id.home:
                   f = new BlankFragment();
                   break;
               case R.id.dasbord:
                   f = new MapsFragment();
                   break;
               case R.id.search:
                   f = new MapsFragment2();
                   break;
           }
           getSupportFragmentManager().beginTransaction().replace(R.id.container,f).commit();
           return ;
       }
   };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bottomNavigationView = findViewById(R.id.menu);
        bottomNavigationView.setOnNavigationItemReselectedListener(navigation);

    }
}