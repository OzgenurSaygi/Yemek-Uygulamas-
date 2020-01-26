package com.example.foodapp;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.example.foodapp.Adapter.KategoriAdapter;
import com.example.foodapp.Adapter.YemekKayitAdapter;
import com.example.foodapp.Model.YemekKategori;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.List;
import java.util.UUID;

import static android.app.Activity.RESULT_OK;

public class CreateDialogView extends DialogFragment  {
    public static final int pick_image_request=71;//resim seçme
    Spinner spnturu,spnkisisayisi,spnsure;
    EditText edt_eat_ad,edt_materials,edt_recipe,edt_trick;
    Button btnyukle,btnekle;
    String yemektur;
    TextView txtlink,txtvazgecbtn,txteklebtn;
    Intent intent;
    ArrayAdapter<String>dataadaptertur;
    ArrayAdapter<String>dataadapterkisisayisi;
    Uri selectimage;
    Bundle bundle;
    String[]spnkisisayi={"1","2","3","4","5","6","7","8","9","10+"};
    String[]pissure={"10dk" ,"15dk","20dk,","25dk","30dk","35dk","40dk","45dk","50dk","55dk","60dk","70dk","90dk"};
    FirebaseDatabase database;
    DatabaseReference tarifyol;
    FirebaseStorage storage;
    StorageReference resimyol;
    YemekKayitAdapter yemekKayitAdapter;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.tarifekle,container,false);
        getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE);
        btnyukle=view.findViewById(R.id.btnyukle);
        edt_eat_ad=view.findViewById(R.id.edt_eat_name);
        edt_materials=view.findViewById(R.id.edt_materials);
        edt_recipe=view.findViewById(R.id.edt_recipe);
        edt_trick=view.findViewById(R.id.edt_tricks);
        btnekle=view.findViewById(R.id.btnekle);
        txtlink=view.findViewById(R.id.txtizlemelink);
        txteklebtn=view.findViewById(R.id.txteklebtn);
        txtvazgecbtn=view.findViewById(R.id.txtvazgecbtn);
        spnkisisayisi=view.findViewById(R.id.kisisayisi);
        spnturu=view.findViewById(R.id.spntur);
        spnsure=view.findViewById(R.id.pisirmesuresi);

        spinnerdoldur(spnkisisayi,spnkisisayisi);
        spinnerdoldur(pissure,spnsure);

        database=FirebaseDatabase.getInstance();
        tarifyol=database.getReference("YEMEKLER");
        storage=FirebaseStorage.getInstance();
        resimyol=storage.getReference();
        bundle=getArguments();
        if(bundle!=null){
           yemektur=bundle.getString("turu");
           turspinnerdoldur(yemektur,spnturu);
        }
        btnekle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                resimsec();
            }
        });
        btnyukle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               resimyukle();
            }
        });
        txteklebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                     tarifyol.push().setValue(yemekKayitAdapter);
                     Toast.makeText(getContext(),"Tarifiniz başarıyla eklendi",Toast.LENGTH_LONG).show();
            }
        });

        return view;

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==pick_image_request && resultCode==RESULT_OK && data!=null && data.getData()!=null){
            selectimage=data.getData();
            btnekle.setText("Seçildi");
        }

    }

    @Override
    public void onResume() {
        super.onResume();
        Window window=getDialog().getWindow();
        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT,WindowManager.LayoutParams.WRAP_CONTENT);
        window.setGravity(Gravity.CENTER);
    }
    public void spinnerdoldur(String[] dize,Spinner spn){
        dataadapterkisisayisi=new ArrayAdapter<String>(getContext(),android.R.layout.simple_spinner_dropdown_item,dize);
        dataadapterkisisayisi.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spn.setAdapter(dataadapterkisisayisi);
    }
    public void turspinnerdoldur(String tur,Spinner spnturu){
        String []yemek_tur;

        if(tur=="Çorba Çeşitleri") {
            String[] yemek_tur1 = {"Tavuklu Çorbalar", "Etli Çorbalar", "Yoğurtlu Çorbalar", "Sütlü Çorbalar", "Sebzeli Çorbalar", "Hamurlu Çorbalar", "Bakliyatlı Çorbalar"};
            dataadaptertur = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_dropdown_item, yemek_tur1);
            dataadaptertur.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spnturu.setAdapter(dataadaptertur);
        }
        else if(tur=="Sebzeler"){
            yemek_tur= new String[]{"Dolma Tarifleri","Kızartma Tarifleri","Sulu Yemek Tarifleri","Zeytinyağlı Tarifler"};
            dataadaptertur=new ArrayAdapter<String>(getContext(),android.R.layout.simple_spinner_dropdown_item,yemek_tur);
            dataadaptertur.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spnturu.setAdapter(dataadaptertur);
        }
        else if(tur=="Et Yemekleri"){
            yemek_tur= new String[]{"Balık Ve Deniz Ürünleri","Kırmızı Et Tarifleri","Sakatat Tarifleri","Tavuk Tarifleri"};
            dataadapterkisisayisi=new ArrayAdapter<String>(getContext(),android.R.layout.simple_spinner_dropdown_item,yemek_tur);
            dataadapterkisisayisi.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spnturu.setAdapter(dataadapterkisisayisi);
        }
        else if(tur=="Hamur İşleri"){
            yemek_tur= new String[]{"Börek Tarifleri","Çörek Tarifleri","Ekmek Tarifleri","Krep Tarifleri","Pide Tarifleri","Poğaça Tarifleri","Pizza Tarifleri","Mantı Tarifleri"};
            dataadapterkisisayisi=new ArrayAdapter<String>(getContext(),android.R.layout.simple_spinner_dropdown_item,yemek_tur);
            dataadapterkisisayisi.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spnturu.setAdapter(dataadapterkisisayisi);
        }
        else if(tur=="Tatlılar"){
            yemek_tur= new String[]{"Pasta Tarifleri","Kek Tarifleri","Sütlü Tarifler","Şerbetli Tarifler","Çikolatalı Tarifler","Tart Tarifleri","Helva Tarifleri"};
            dataadapterkisisayisi=new ArrayAdapter<String>(getContext(),android.R.layout.simple_spinner_dropdown_item,yemek_tur);
            dataadapterkisisayisi.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spnturu.setAdapter(dataadapterkisisayisi);
        }
        else if(tur=="Apertifler"){
            yemek_tur= new String[]{"Çiğ Köfte Tarifleri","Sandviç Tarifleri","Kanepe Tarifi","Meze Tarifleri"};
            dataadapterkisisayisi=new ArrayAdapter<String>(getContext(),android.R.layout.simple_spinner_dropdown_item,yemek_tur);
            dataadapterkisisayisi.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spnturu.setAdapter(dataadapterkisisayisi);
        }
        else if(tur=="Pilav Çeşitleri"){
            yemek_tur= new String[]{"Şehriyeli Pilav Tarifleri","Bulgurlu Pilav Tarifleri","İç Pilav Tarifleri","Sebzeli Pilav Tarifleri","Pirinç Pilavı Tarifleri"};
            dataadapterkisisayisi=new ArrayAdapter<String>(getContext(),android.R.layout.simple_spinner_dropdown_item,yemek_tur);
            dataadapterkisisayisi.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spnturu.setAdapter(dataadapterkisisayisi);
        }
        else if(tur=="Salata"){
            yemek_tur= new String[]{"Sebze Salataları","Makarna Salataları","Közlenmiş Sebze Salataları","Etli Salatalar"};
            dataadapterkisisayisi=new ArrayAdapter<String>(getContext(),android.R.layout.simple_spinner_dropdown_item,yemek_tur);
            dataadapterkisisayisi.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spnturu.setAdapter(dataadapterkisisayisi);
        }
    }
    public void tarif_kayit(String uri){


    }
    public void resimsec(){
        intent=new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent,"Resim seç"),pick_image_request);
    }
    public void resimyukle(){
        if(selectimage!=null)
        {
            final ProgressDialog mdialog=new ProgressDialog(getContext());
            mdialog.setMessage("Yükleniyor..");
            mdialog.show();
            String resimadi= UUID.randomUUID().toString();//rastgele id belirdledik
            final StorageReference resimdosyasi=resimyol.child("resimler/"+resimadi);
            resimdosyasi.putFile(selectimage).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                   mdialog.dismiss();
                   Toast.makeText(getContext(),"Resim Yüklendi",Toast.LENGTH_LONG).show();
                   resimdosyasi.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                       @Override
                       public void onSuccess(Uri uri) {
                           String eat_ad=edt_eat_ad.getText().toString();
                           String materials=edt_materials.getText().toString();
                           String recipe=edt_recipe.getText().toString();
                           String trick=edt_trick.getText().toString();
                           String link=txtlink.getText().toString();
                           String tur=spnturu.getSelectedItem().toString();
                           String kisisayisi=spnkisisayisi.getSelectedItem().toString();
                           String pissuresi=spnsure.getSelectedItem().toString();
                           String kategori=bundle.getString("turu");
                           String kullanici= FirebaseAuth.getInstance().getCurrentUser().getUid();
                           yemekKayitAdapter=new YemekKayitAdapter(eat_ad,materials,recipe,trick,link,tur,kisisayisi,pissuresi,uri.toString(),kullanici,kategori);
                           //resmi veritabanına aktarma işlemi yapılcak
                       }
                   });
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    mdialog.dismiss();
                    Toast.makeText(getContext(),e.getMessage(),Toast.LENGTH_LONG).show();
                }
            }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                    double progress=(100.0*taskSnapshot.getBytesTransferred()/taskSnapshot.getTotalByteCount());
                    mdialog.setMessage("% "+progress+" Yüklendi");
                }
            });
        }
    }
}
