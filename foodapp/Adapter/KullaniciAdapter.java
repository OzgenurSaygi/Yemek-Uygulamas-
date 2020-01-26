package com.example.foodapp.Adapter;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.foodapp.Cerceve.ProfileFragment;
import com.example.foodapp.Model.Kullanici;
import com.example.foodapp.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class KullaniciAdapter extends RecyclerView.Adapter<KullaniciAdapter.ViewHolder>{
    private Context mcontext;
    private List<Kullanici> kullaniciList;
    private FirebaseUser firebaseUser;

    public KullaniciAdapter(Context mcontext, List<Kullanici> kullaniciList) {
        this.mcontext = mcontext;//düzelt
        this.kullaniciList = kullaniciList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(mcontext).inflate(R.layout.kullaniciogesi,parent,false);
        return new KullaniciAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {
    firebaseUser= FirebaseAuth.getInstance().getCurrentUser();//mevcut kullanici alınıyor
    final  Kullanici kullanici=kullaniciList.get(position);
    holder.btntakipet.setVisibility(View.VISIBLE);
    holder.kullaniciadi.setText(kullanici.getKullanici_email());
    holder.adi.setText(kullanici.getKullanici_ad());
    Glide.with(mcontext).load(kullanici.getResimurl()).into(holder.profilresim);
    takip_ediliyor(kullanici.getKullanici_id(),holder.btntakipet);

    if(kullanici.getKullanici_id().equals(firebaseUser.getUid())){
        holder.btntakipet.setVisibility(View.GONE);
    }
    holder.itemView.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            SharedPreferences.Editor editor=mcontext.getSharedPreferences("PREFS",Context.MODE_PRIVATE).edit();
            editor.putString("profileid",kullanici.getKullanici_id());
            editor.apply();
            ((FragmentActivity)mcontext).getSupportFragmentManager().beginTransaction()
                    .replace(R.id.cerceve_kapsayici,new ProfileFragment()).commit();
        }
    });
    holder.btntakipet.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if(holder.btntakipet.getText().toString().equals("Takip Et")){
                //takip edeceğin  kişilerin id bilgisi takipedilenler listene ekleniyor
                FirebaseDatabase.getInstance().getReference().child("Takip").child(firebaseUser.getUid())
                        .child("takipedilenler").child(kullanici.getKullanici_id()).setValue(true);
                //takip ettiğin kişinin takipçiler listesine düşüyorsun
                FirebaseDatabase.getInstance().getReference().child("Takip").child(kullanici.getKullanici_id())
                        .child("takipciler").child(firebaseUser.getUid()).setValue(true);

            }
            else{
                FirebaseDatabase.getInstance().getReference().child("Takip").child(firebaseUser.getUid())
                        .child("takipedilenler").child(kullanici.getKullanici_id()).removeValue();
                //takip ettiğin kişinin takipçiler listesine kaldılıyot
                FirebaseDatabase.getInstance().getReference().child("Takip").child(kullanici.getKullanici_id())
                        .child("takipciler").child(firebaseUser.getUid()).removeValue();

            }
        }
    });
    }

    @Override
    public int getItemCount() {
        return kullaniciList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        public TextView kullaniciadi;
        public TextView adi;
        public CircleImageView profilresim;
        public Button btntakipet;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            kullaniciadi=itemView.findViewById(R.id.kullaniciadoge);
            adi=itemView.findViewById(R.id.txtadoge);
            profilresim=itemView.findViewById(R.id.profilresmi);
            btntakipet=itemView.findViewById(R.id.btntakip);
        }
    }
    private void takip_ediliyor(final String kullaniciId, final Button button){
        DatabaseReference takipyolu= FirebaseDatabase.getInstance().getReference().child("Takip")
                .child(firebaseUser.getUid()).child("takipedilenler");
        takipyolu.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                //takip eediyormu kontrol ediyoruz
                if(dataSnapshot.child(kullaniciId).exists()){
                         button.setText("Takip ediliyor");
                }
                else{
                    button.setText("Takip Et");
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
