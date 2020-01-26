package com.example.foodapp.Cerceve;


import android.content.Context;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.foodapp.R;
import com.google.android.material.tabs.TabLayout;

/**
 * A simple {@link Fragment} subclass.
 */
public class Search extends Fragment {
    String[] tabtitle2={"FOOD SEARCH","PERSON SEARCH"};
     TabLayout tabLayout;
     ViewPager viewPager;
     Context mcontext;
    public Search() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_search, container, false);
        viewPager=view.findViewById(R.id.viewpagesearch);
        tabLayout=view.findViewById(R.id.searchtabs);
        viewPager.setAdapter(new Tapadadapter2(getChildFragmentManager(),getContext()));
        tabLayout.setupWithViewPager(viewPager);
        return view;
    }
    public  class Tapadadapter2 extends FragmentPagerAdapter{
        Context context;
        public Tapadadapter2(FragmentManager fm, Context context) {
            super(fm);
            this.context=context;
        }

        @Override
        public Fragment getItem(int position) {
            if(position==0){
             FoodSearchFragment foodSearchFragment=new FoodSearchFragment();
             return foodSearchFragment;
            }

            else if(position==1){
              PersonSearchFragment personSearchFragment=new PersonSearchFragment();
              return personSearchFragment;
            }
            else{
                FoodSearchFragment foodSearchFragment=new FoodSearchFragment();
                return foodSearchFragment;
            }

        }

        @Override
        public int getCount() {
            return tabtitle2.length;
        }

        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {
            return tabtitle2[position];
        }
    }

}
