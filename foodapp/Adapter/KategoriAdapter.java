package com.example.foodapp.Adapter;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.icu.text.UnicodeSetSpanner;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodapp.Cerceve.AddFragment;
import com.example.foodapp.CreateDialogView;
import com.example.foodapp.Model.YemekKategori;
import com.example.foodapp.R;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class KategoriAdapter extends RecyclerView.Adapter<KategoriAdapter.ViewHolder> {

    List<YemekKategori>kategoriliste;
    Context mycontext,context2;
    String titletur;
    public KategoriAdapter(List<YemekKategori> kategoriliste, Context mycontext) {
        this.kategoriliste = kategoriliste;
        this.mycontext = mycontext;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.yemek_kategori_ogeleri,parent,false);
        ViewHolder vholder=new ViewHolder(view);
        context2=parent.getContext();
        return vholder;


    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {
     holder.txtkattitle.setText(kategoriliste.get(position).getKategori_title());
     holder.txtkaticerik.setText(kategoriliste.get(position).getKategori_icerik());
     holder.katimg.setImageResource(kategoriliste.get(position).getImg_src());
      titletur=holder.txtkattitle.getText().toString();
      holder.btnimg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                titletur=holder.txtkattitle.getText().toString();
                CreateDialogView dialogView=new CreateDialogView();
                Bundle bundle=new Bundle();
                bundle.putString("turu",titletur);
                dialogView.setArguments(bundle);
                FragmentManager manager=((AppCompatActivity)context2).getSupportFragmentManager();
                dialogView.show(manager,"Tarifdiyalog");

            }
        });

    }

    @Override
    public int getItemCount() {
        return kategoriliste.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder{
        public TextView txtkattitle,txtkaticerik;
        public CircleImageView btnimg;
        public ImageView katimg;
        public ViewHolder(@NonNull View itemView)
        {
            super(itemView);
            txtkattitle=(TextView) itemView.findViewById(R.id.txtkattitle);
            txtkaticerik=(TextView) itemView.findViewById(R.id.txtkaticerik);
            btnimg=(CircleImageView) itemView.findViewById(R.id.btnimg);
            katimg=(ImageView) itemView.findViewById(R.id.kategori_img);


        }

    }



}
