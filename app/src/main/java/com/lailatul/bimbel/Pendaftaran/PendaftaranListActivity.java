package com.lailatul.bimbel.Pendaftaran;
import android.app.ProgressDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.lailatul.bimbel.Pendaftaran.Adapter_Pendaftaran.AdapterPendaftaran;
import com.lailatul.bimbel.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class PendaftaranListActivity extends AppCompatActivity {

    private DatabaseReference database;

    private ArrayList<PendaftaranReq> pendaftaranReq;
    private AdapterPendaftaran Adapterpendaftaran;
    private String Uid,Date;
    private RecyclerView jadwal_list;
    private ProgressDialog loading;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pendaftaran_list);

        database = FirebaseDatabase.getInstance().getReference().child("Pendaftaran");
        Uid = getIntent().getStringExtra("Uid");
        Date = getIntent().getStringExtra("date");



        DatabaseReference offline = database;
        offline.keepSynced(true);

        jadwal_list = findViewById(R.id.jadwal_lab_list);

        RecyclerView.LayoutManager mLayoutManager  = new LinearLayoutManager(getApplicationContext());
        jadwal_list.setLayoutManager(mLayoutManager);
        jadwal_list.setItemAnimator(new DefaultItemAnimator());

        loading = ProgressDialog.show(PendaftaranListActivity.this,
                null,
                "Please wait...",
                true,
                false);

        database.child(Uid).child("Paket Yang Diambil").child(Date).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                pendaftaranReq = new ArrayList<>();

                for (DataSnapshot noteDataSnapshot : dataSnapshot.getChildren()) {
                    PendaftaranReq kegiatan =noteDataSnapshot.getValue( PendaftaranReq.class);
                    kegiatan.setDate(Date);
                    kegiatan.setKey(noteDataSnapshot.getKey());
                    kegiatan.setUserId(Uid);
                    pendaftaranReq.add(kegiatan);
                }

                Adapterpendaftaran = new AdapterPendaftaran(pendaftaranReq, PendaftaranListActivity.this);
                jadwal_list.setAdapter(Adapterpendaftaran);
                loading.dismiss();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                System.out.println(databaseError.getDetails()+" "+databaseError.getMessage());
                loading.dismiss();
            }
        });

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("List Pendaftaran");

        findViewById(R.id.fab_add_lab).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(PendaftaranListActivity.this, PendaftaranActivity.class)
                        .putExtra("Uid",Uid)
                        .putExtra("date",Date)
                        .putExtra("id","")
                        .putExtra("nama_lengkap","")
                        .putExtra("jenis_kelamin","")
                        .putExtra("paket_bimbel",Date)
                        .putExtra("asal_sekolah","")
                        .putExtra("tanggal_lahir",""));
            }
        });
    }


}
