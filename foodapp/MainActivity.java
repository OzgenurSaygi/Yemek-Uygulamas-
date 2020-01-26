package com.example.foodapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.example.foodapp.Cerceve.AddFragment;
import com.example.foodapp.Cerceve.FoodSearchFragment;
import com.example.foodapp.Cerceve.HomeFragment;
import com.example.foodapp.Cerceve.ProfileFragment;
import com.example.foodapp.Cerceve.RecipeFragment;
import com.example.foodapp.Cerceve.Search;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {
    BottomNavigationView bottomNavigationView;
    Fragment secili_cerceve=null;
    Context context=this;
     int tiklama=0;
     Button btnderne;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bottomNavigationView=findViewById(R.id.bottomnavigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(navigationItemSelectedListener);
        getSupportFragmentManager().beginTransaction().replace(R.id.cerceve_kapsayici,new HomeFragment()).commit();

    }


    private BottomNavigationView.OnNavigationItemSelectedListener navigationItemSelectedListener=
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                    switch (menuItem.getItemId()){
                        case R.id.profilecook:
                            secili_cerceve=new ProfileFragment();
                            SharedPreferences.Editor editor=getSharedPreferences("PREFS",MODE_PRIVATE).edit();
                            editor.putString("profileid", FirebaseAuth.getInstance().getCurrentUser().getUid());
                            editor.apply();
                            break;
                        case R.id.recipe:
                            secili_cerceve=new RecipeFragment();
                            break;
                        case R.id.search:
                            secili_cerceve=new Search();
                            break;
                        case R.id.homefriend:
                            secili_cerceve=new HomeFragment();
                            break;
                        case R.id.food_add:
                            secili_cerceve=new AddFragment();
                            break;


                    }
                    if(secili_cerceve!=null){

                        getSupportFragmentManager().beginTransaction().replace(R.id.cerceve_kapsayici,secili_cerceve).commit();
                    }
                    return true;
                }
            };
}
