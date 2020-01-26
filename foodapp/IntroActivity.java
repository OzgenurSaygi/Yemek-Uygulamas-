package com.example.foodapp;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.Html;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

public class IntroActivity extends AppCompatActivity {
    ViewPager viewPager;
    MyViewPageAdapter myViewPageAdapter;
    LinearLayout dotslayout,bilgi;
    TextView[]dots;
    TextView txt1,txt2;
    CountDownTimer countDownTimer;
    int layouts[];
    Context context=this;
    Button btnskip,btnnext;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.intro_activity);
        viewPager=findViewById(R.id.viewpage);
        dotslayout=findViewById(R.id.layoutDots);
        btnnext=findViewById(R.id.btn_next);
        btnskip=findViewById(R.id.btn_skip);

        if (Build.VERSION.SDK_INT >= 21) {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
        }
        //ekranlarımızı çekiyoruz
        layouts=new int[]{R.layout.ekran1,R.layout.ekran2,R.layout.ekran3,R.layout.ekran4,R.layout.ekran5};
        addBottomDots(0);
        changeStatusBarColor();
        myViewPageAdapter=new MyViewPageAdapter();
        viewPager.setAdapter(myViewPageAdapter);
        viewPager.addOnPageChangeListener(viewPagerPageChangeListener);
        btnskip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int current=getItemback(1);
                if(current>=0)
                {
                    if(current==0){
                        btnskip.setText("GEÇ");
                    }
                    else{
                        btnskip.setText("GERİ");
                    }
                    viewPager.setCurrentItem(current);
                }
                 else {
                    launchHomeScreen();
                }


            }
        });
        btnnext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                txt2= findViewById(R.id.txt1);
                int current=getItem(1);
                if(current<layouts.length){
                    viewPager.setCurrentItem(current);
                   if(current==0){
                       txt2= findViewById(R.id.txt1);
                       bilgi=findViewById(R.id.lnrhamur);
                      countDownTimer=new CountDownTimer(3000,1000) {
                          @Override
                          public void onTick(long l) {
                              txt2.setText("Hamur İşleri");
                          }

                          @Override
                          public void onFinish() {
                              bilgi.setVisibility(View.GONE);

                          }
                      }.start();


                   }
                   if(current==1){
                       btnskip.setText("GERİ");
                       txt2=findViewById(R.id.txt2);
                       bilgi=findViewById(R.id.lnrtatli);
                       txt2.setText("Tatlılar");
                   }
                   if(current==2){
                       btnskip.setText("GERİ");
                       txt2=findViewById(R.id.txt3);
                       bilgi=findViewById(R.id.lnrcorba);
                       txt2.setText("Çorbalar");
                   }

                }
                else {
                    launchHomeScreen();
                }
            }
        });


    }

    private int getItem(int i) {
        return viewPager.getCurrentItem() + i;
    }
    private int getItemback(int i) {
        return viewPager.getCurrentItem() - i;
    }
    private void launchHomeScreen() {

        startActivity(new Intent(IntroActivity.this, LoginActivity.class));
        finish();
    }
    private void addBottomDots(int currentPage){
        //noktaları oluşturyoruz
        dots = new TextView[layouts.length];
        int[] colorsActive = getResources().getIntArray(R.array.array_dot_active);
        int[] colorsInactive = getResources().getIntArray(R.array.array_dot_inactive);
        dotslayout.removeAllViews();
        for (int i = 0; i < dots.length; i++) {
            dots[i] = new TextView(this);
            dots[i].setText(Html.fromHtml("&#8226;"));
            dots[i].setTextSize(35);
            dots[i].setTextColor(colorsInactive[currentPage]);
            dotslayout.addView(dots[i]);
        }

        if (dots.length > 0)
            dots[currentPage].setTextColor(colorsActive[currentPage]);
    }
    ViewPager.OnPageChangeListener viewPagerPageChangeListener=new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            addBottomDots(position);

            // Eğer son sayfa ise..
            if (position == layouts.length - 1) {
                // İleri butonu başla olarak değişecek
                btnnext.setText("BAŞLA");

                btnskip.setVisibility(View.GONE);
            } else {
               btnnext.setText("İLERİ");
                btnskip.setVisibility(View.VISIBLE);
            }
        }

        @Override
        public void onPageSelected(int position) {

        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };
    private void changeStatusBarColor() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.TRANSPARENT);
        }
    }
    public class MyViewPageAdapter extends PagerAdapter{
        LayoutInflater layoutInflater;
        public MyViewPageAdapter() {
        }

        @Override
        public int getCount() {
            return layouts.length;
        }

        @Override
        public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
            return view == object;
        }

        @Override
        public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
            View view = (View) object;
            container.removeView(view);
        }

        @NonNull
        @Override
        public Object instantiateItem(@NonNull ViewGroup container, int position) {
            layoutInflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            View view = layoutInflater.inflate(layouts[position], container, false);
            container.addView(view);

            return view;
        }
    }
}
