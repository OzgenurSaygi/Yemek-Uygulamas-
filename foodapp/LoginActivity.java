package com.example.foodapp;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;

public class LoginActivity extends AppCompatActivity {
    TabLayout tabs;
    ViewPager viewPager;
    Context context=this;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        tabs=findViewById(R.id.tabs);
        viewPager=findViewById(R.id.viewpage);
        viewPager.setAdapter(new Tapadadapter(getSupportFragmentManager(),context));
        tabs.setupWithViewPager(viewPager);
        tabs.getTabAt(0).setIcon(R.drawable.use);
        tabs.getTabAt(1).setIcon(R.drawable.use_signup);
    }

    public class Tapadadapter extends FragmentPagerAdapter {
        String[]tabtitle={"LOGİN","SİGN UP"};
       Context context;
        public Tapadadapter(FragmentManager fm, Context context) {
            super(fm);
            this.context=context;
        }

        @Override
        public Fragment getItem(int position) {

            if(position==0){
                UseLogin useLogin=new UseLogin();
                return useLogin;
            }
            else{
                UseSignUp useSignUp=new UseSignUp();
                return  useSignUp;
            }

        }

        @Override
        public int getCount() {
            return tabtitle.length;
        }


    }
}

