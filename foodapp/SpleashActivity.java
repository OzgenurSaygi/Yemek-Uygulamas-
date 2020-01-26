package com.example.foodapp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class SpleashActivity extends AppCompatActivity {
    Context context=this;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.splesh_activity);
        new SayfaGecis().start();

    }
    private class SayfaGecis extends Thread{
        @Override
        public void run() {
            try {
                   Thread.sleep(3000);
            }catch (Exception e){
                e.printStackTrace();
            }
            finally {
                Intent intent=new Intent(context,IntroActivity.class);
                startActivity(intent);
                finish();
            }
        }
    }
}
