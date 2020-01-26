package com.example.foodapp;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.InputType;
import android.text.TextUtils;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.concurrent.Executor;

public class UseSignUp extends Fragment  {
    EditText edtusename,edtusepass,edtusemail;
    Button btnsignup;
    ImageView showicon;
    FirebaseAuth yetki;
    DatabaseReference yol;
    int btnimg=0;
    @Nullable
    @Override

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v= inflater.inflate(R.layout.use_signup, container, false);
        edtusename=v.findViewById(R.id.edtuser_sign);
        edtusemail=v.findViewById(R.id.edtmail_sign);
        edtusepass=v.findViewById(R.id.edtpassword_sign);
        btnsignup=v.findViewById(R.id.btnsignup);
        showicon=v.findViewById(R.id.showicon);
        yetki=FirebaseAuth.getInstance();
        showicon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(btnimg==0){
                    edtusepass.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    btnimg=1;
                    showicon.setBackgroundResource(R.drawable.hide_icon);
                }
                else{
                    edtusepass.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    showicon.setBackgroundResource(R.drawable.show_icon);
                    btnimg=0;
                }
            }
        });

        btnsignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                  String edtpass=edtusepass.getText().toString();
                  String edtname=edtusename.getText().toString();
                  String edtmail=edtusemail.getText().toString();
                  if(TextUtils.isEmpty(edtpass)|| TextUtils.isEmpty(edtname) ||TextUtils.isEmpty(edtmail) ){
                      Toast.makeText(getContext(),"Lütfen bütün alanları doldurunuz..",Toast.LENGTH_LONG).show();
                  }
                  else{
                      if(edtusepass.length()<5){
                          Toast.makeText(getContext(),"Şifreniz minumum 5 karakterli olmalıdır..",Toast.LENGTH_LONG).show();
                      }
                      else{

                             kaydet(edtname,edtpass,edtmail);
                      }

                  }
            }
        });
        return v;
    }
    public void kaydet(final String kullanciadi, String sifre, final String mail){
        yetki=FirebaseAuth.getInstance();
        yetki.createUserWithEmailAndPassword(mail,sifre).addOnCompleteListener(getActivity(), new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    FirebaseUser kullanici=yetki.getCurrentUser();
                    String kullanciid=kullanici.getUid();
                    yol=FirebaseDatabase.getInstance().getReference().child("Kullanicilar").child(kullanciid);
                    HashMap<String,Object>hashMap=new HashMap<>();
                    hashMap.put("id",kullanciid);
                    hashMap.put("email",mail);
                    hashMap.put("ad",kullanciadi);
                    hashMap.put("resimurl","https://firebasestorage.googleapis.com/v0/b/foodapp-3ddc9.appspot.com/o/personplaceholder.jpg?alt=media&token=59e5b352-e1ac-4db7-b4b1-d436d44d06f6");
                    yol.setValue(hashMap).addOnCompleteListener(getActivity(), new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if(task.isSuccessful()){
                                Toast.makeText(getContext(),"Başarıyla kaydedildi", Toast.LENGTH_LONG).show();
                            }
                             else {
                                Toast.makeText(getContext(),"Kaydedilmedi", Toast.LENGTH_LONG).show();
                            }
                        }
                    }); }
            }
        }); }

}

