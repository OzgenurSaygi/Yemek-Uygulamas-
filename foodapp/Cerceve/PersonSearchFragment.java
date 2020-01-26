package com.example.foodapp.Cerceve;


import android.annotation.SuppressLint;
import android.app.DownloadManager;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.example.foodapp.Adapter.KullaniciAdapter;
import com.example.foodapp.Model.Kullanici;
import com.example.foodapp.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class PersonSearchFragment extends Fragment {
     RecyclerView recyclerView;
     public  List<Kullanici>kullaniciliste;
     EditText aramabar;


    public PersonSearchFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_person_search, container, false);
        aramabar=view.findViewById(R.id.editaramabar);
        recyclerView=view.findViewById(R.id.recyclerview_arama);
        recyclerView.setHasFixedSize(true);
        kullaniciliste=new ArrayList<>();
        kullanicilariOku();
        aramabar.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                       kullaniciara(charSequence.toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        return view;

    }
    public void kullanicilariOku(){
        final DatabaseReference kullancilaryolu=FirebaseDatabase.getInstance().getReference().child("Kullanicilar");
        kullancilaryolu.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(aramabar.getText().toString().equals("")){
                    kullaniciliste.clear();
                    for(DataSnapshot snapshot:dataSnapshot.getChildren()){
                        Kullanici kullanici=new Kullanici();
                        kullanici.setKullanici_id(snapshot.child("id").getValue().toString());
                        kullanici.setKullanici_ad(snapshot.child("ad").getValue().toString());
                        kullanici.setKullanici_email(snapshot.child("email").getValue().toString());
                        kullanici.setResimurl(snapshot.child("resimurl").getValue().toString());
                        kullaniciliste.add(kullanici);
                    }
                    if(kullaniciliste.size()>0){
                        addlist();
                    }
                    //kullaniciAdapter.notifyDataSetChanged();
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
    KullaniciAdapter kullaniciAdapter;
    @SuppressLint("WrongConstant")
    public void addlist(){
        kullaniciAdapter=new KullaniciAdapter(getContext(),kullaniciliste);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false));
        recyclerView.setAdapter(kullaniciAdapter);
    }
    public void kullaniciara(String s){
        Query sorgu= FirebaseDatabase.getInstance().getReference().child("Kullanicilar").orderByChild("email")
                .startAt(s).endAt(s+"\uf8ff");
        sorgu.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                kullaniciliste.clear();
                for(DataSnapshot snapshot2:dataSnapshot.getChildren()){
                    Kullanici kullanici=new Kullanici();
                    kullanici.setKullanici_id(snapshot2.child("id").getValue().toString());
                    kullanici.setKullanici_ad(snapshot2.child("ad").getValue().toString());
                    kullanici.setKullanici_email(snapshot2.child("email").getValue().toString());
                    kullanici.setResimurl(snapshot2.child("resimurl").getValue().toString());
                    kullaniciliste.add(kullanici);
                }
                if(kullaniciliste.size()>0){
                    addlist();
                }
                //kullaniciAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

}
