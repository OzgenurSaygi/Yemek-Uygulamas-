package com.example.foodapp;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class UseLogin extends Fragment {
    EditText edtuser_name,edtuserpassword;
    Button btnlogin;
    String sifre;
    CheckBox remember;
    String kulad,kulsifre;
    FirebaseAuth girisyetkisi;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v2=inflater.inflate(R.layout.use_login, container, false);
        edtuser_name=v2.findViewById(R.id.useedtmail);
        edtuserpassword=v2.findViewById(R.id.useedtpas);
        btnlogin=v2.findViewById(R.id.btnlogin);
        remember=v2.findViewById(R.id.chkremember);
        girisyetkisi=FirebaseAuth.getInstance();
        btnlogin.setOnClickListener(new View.OnClickListener() {@Override
            public void onClick(View view) {
                kulad=edtuser_name.getText().toString();
                kulsifre=edtuserpassword.getText().toString();
                if(TextUtils.isEmpty(kulad) || TextUtils.isEmpty(kulsifre)){
                    Toast.makeText(getContext(),"Boş alanları doldurunuz",Toast.LENGTH_LONG).show(); }
                else {
                    girisyetkisi.signInWithEmailAndPassword(kulad, kulsifre)
                            .addOnCompleteListener(getActivity(),
                            new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                DatabaseReference yolgiris= FirebaseDatabase.getInstance().getReference().child("Kullanicilar")
                                        .child(girisyetkisi.getCurrentUser().getUid());
                                   yolgiris.addValueEventListener(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                        Intent intent2 = new Intent(getActivity(), MainActivity.class);
                                        startActivity(intent2);
                                    }

                                    @Override
                                    public void onCancelled(@NonNull DatabaseError databaseError) {

                                    }
                                });
                            }
                        } }); } }
        });
        return v2;


    }



}
