package com.example.foodapp.Cerceve;


import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.foodapp.Adapter.KategoriAdapter;
import com.example.foodapp.Model.YemekKategori;
import com.example.foodapp.R;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class AddFragment extends Fragment {
    RecyclerView recyclerView;
    List<YemekKategori> kategoriliste=new ArrayList<YemekKategori>();
    LinearLayoutManager layoutManager=new LinearLayoutManager(getContext());
    Button btndeneme;
    public AddFragment() {
        // Required empty public constructor
    }


    @SuppressLint("WrongConstant")
    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_add, container, false);


        recyclerView=view.findViewById(R.id.recyclerkategori);
       // layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        //layoutManager.scrollToPosition(0);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setHasFixedSize(true);
        kategoriliste.add(new YemekKategori("Çorba Çeşitleri","Sofralarmızın olmazsa olmaz başlangıçlarından" +
                " olan tadı damakta iz bırakacak çorba tariflerinizi bu kategoriden ekleyebilirsiniz ",R.drawable.kategoricorba));
        kategoriliste.add(new YemekKategori("Sebzeler","Sağlıklı leziz tatlar",R.drawable.kategorisebze));
        kategoriliste.add(new YemekKategori("Et Yemekleri","Nefis Etler",R.drawable.kategorimeat));
        kategoriliste.add(new YemekKategori("Hamur İşleri","Leziz Hamur İşleri",R.drawable.kategorihamur));
        kategoriliste.add(new YemekKategori("Tatlılar","Leziz Tatlılar",R.drawable.kategoritatli));
        kategoriliste.add(new YemekKategori("Apertifler","Apertddfsdf",R.drawable.kategoriaperatif));
        kategoriliste.add(new YemekKategori("Salata","Salata çeşitleri",R.drawable.kategorisalata));
        kategoriliste.add(new YemekKategori("Pilav Çeşitleri","Pilav çeşitleri",R.drawable.kategoripilav));

        //sıkıntı olursa ui kütüphanesini eklemeyi unutma
        KategoriAdapter kategoriAdapter=new KategoriAdapter(kategoriliste, getContext());
        recyclerView.setAdapter(kategoriAdapter);


        return view;
    }



}
