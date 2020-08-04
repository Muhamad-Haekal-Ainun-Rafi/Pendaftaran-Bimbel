package com.lailatul.bimbel.Pendaftaran;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.lailatul.bimbel.R;

import java.lang.reflect.Member;

import static java.lang.String.*;

public class PendaftaranActivity extends AppCompatActivity {

        private DatabaseReference database;
        int i = 0;
        Member member;
        RadioButton LK,PR;
        private EditText etNama_lengkap,etNama_panggilan,etJenis_kelamin,etTanggal_lahir,etNama_ayah,etNo_telpon,etAlamat,etKabupaten,etProvinsi,etAsal_sekolah,etPaket_bimbel;
        private ProgressDialog loading;
        private Button btnSave,btnCancel;
        private String Uid,Date,jk,m1,m2;
        private String sId,sNama_lengkap,sNama_panggilan,sJenis_kelamin,sTanggal_lahir,sNama_ayah,sNo_telpon,sAlamat,sKabupaten,sProvinsi,sAsal_sekolah,sPaket_bimbel;

        @Override
        protected void onCreate (Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_pendaftaran);
            ActionBar actionBar = getSupportActionBar();
            actionBar.hide();

            database = FirebaseDatabase.getInstance().getReference().child("Pendaftaran");
            Uid = getIntent().getStringExtra("Uid");
            Date = getIntent().getStringExtra("date");


            sId = getIntent().getStringExtra("id");
            sNama_lengkap = getIntent().getStringExtra("nama_lengkap");
            sNama_panggilan = getIntent().getStringExtra("nama_panggilan");
            sJenis_kelamin = getIntent().getStringExtra("jenis_kelamin");
            sTanggal_lahir = getIntent().getStringExtra("tanggal_lahir");
            sNama_ayah = getIntent().getStringExtra("nama_ayah");
            sNo_telpon = getIntent().getStringExtra("no_telpon");
            sAlamat = getIntent().getStringExtra("alamat");
            sKabupaten = getIntent().getStringExtra("kabupaten");
            sProvinsi = getIntent().getStringExtra("provinsi");
            sAsal_sekolah = getIntent().getStringExtra("asal_sekolah");
            sPaket_bimbel = getIntent().getStringExtra("paket_bimbel");

            etNama_lengkap = findViewById(R.id.et_namalkp);
            etNama_panggilan = findViewById(R.id.et_namapgl);
            etTanggal_lahir = findViewById(R.id.et_tgl);
            etNama_ayah = findViewById(R.id.et_ayah);
            etNo_telpon = findViewById(R.id.et_telpon);
            etAlamat = findViewById(R.id.et_alamat);
            etKabupaten = findViewById(R.id.et_kab);
            etProvinsi = findViewById(R.id.et_prov);
            etAsal_sekolah = findViewById(R.id.et_sekolah);
            etPaket_bimbel = findViewById(R.id.et_paket);
            LK=findViewById(R.id.rb_lk);
            PR=findViewById(R.id.rb_pr);
            btnSave = findViewById(R.id.btn_save_lab);
            btnCancel = findViewById(R.id.btn_cancel_lab);

            etNama_lengkap.setText(sNama_lengkap);
            etNama_panggilan.setText(sNama_panggilan);
            etTanggal_lahir.setText(sTanggal_lahir);
            etNama_ayah.setText(sNama_ayah);
            etNo_telpon.setText(sNo_telpon);
            etAlamat.setText(sAlamat);
            etKabupaten.setText(sKabupaten);
            etProvinsi.setText(sProvinsi);
            etAsal_sekolah.setText(sAsal_sekolah);
            etPaket_bimbel.setText(sPaket_bimbel);


            if (sId.equals("")){
                btnSave.setText("Save");
                btnCancel.setText("Cancel");

            }else {
                btnSave.setText("Edit");
                btnCancel.setText("Delete");

            }


            btnCancel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (btnCancel.getText().equals("Cancel")){
                        //tutup
                        finish();
                    }else {
                        //hapus
                        database.child(Uid)
                                .child("Paket Yang Diambil")
                                .child(Date)
                                .child(sId)
                                .removeValue()
                                .addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void aVoid) {
                                        Toast.makeText(PendaftaranActivity.this,"Data Profil Dihapus!",Toast.LENGTH_SHORT).show();
                                        finish();
                                        return;
                                    }
                                });

                    }
                }
            });

            btnSave.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                 m1 = LK.getText().toString();
                 m2 = PR.getText().toString();
                if (LK.isChecked()){
                    jk ="Laki-Laki";
                }else{
                    jk ="Perempuan";
                }
                    DatabaseReference connectedRef = FirebaseDatabase.getInstance().getReference(".info/connected");
                    connectedRef.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot snapshot) {
                            boolean connected = snapshot.getValue(Boolean.class);
                            if (connected) {
                                String Snama_lengkap = etNama_lengkap.getText().toString();
                                String Snama_panggilan = etNama_panggilan.getText().toString();
                                String Sjenis_kelamin = jk;
                                String Stanggal_lahir = etTanggal_lahir.getText().toString();
                                String Snama_ayah = etNama_ayah.getText().toString();
                                String Sno_telpon = etNo_telpon.getText().toString();
                                String Salamat = etAlamat.getText().toString();
                                String Skabupaten = etKabupaten.getText().toString();
                                String Sprovinsi = etProvinsi.getText().toString();
                                String Sasal_sekolah = etAsal_sekolah.getText().toString();
                                String Spaket_bimbel = etPaket_bimbel.getText().toString();

                                //printah tambah
                                if (btnSave.getText().equals("Save")){
                                    if (Snama_lengkap.equals("")){
                                        etNama_lengkap.setError("Masukkan nama terlebih dahulu!");
                                        etNama_lengkap.requestFocus();
                                    }
                                    else {
                                        loading = ProgressDialog.show(PendaftaranActivity.this,
                                                "",
                                                "Please wait...",
                                                true,
                                                false);
                                        submitUser(new PendaftaranReq(Snama_lengkap,Snama_panggilan,Sjenis_kelamin,Stanggal_lahir,Snama_ayah,Sno_telpon,Salamat,Skabupaten,Sprovinsi,Sasal_sekolah,Spaket_bimbel));
                                    }
                                }
                                //perintah edit
                                else {
                                    if (Snama_lengkap.equals("")){
                                        etNama_lengkap.setError("Masukkan nama terlebih dahulu!");
                                        etNama_lengkap.requestFocus();
                                    }
                                    else {
                                        loading = ProgressDialog.show(PendaftaranActivity.this,
                                                "",
                                                "Please wait...",
                                                true,
                                                false);
                                        editUser(new PendaftaranReq(Snama_lengkap,Snama_panggilan,Sjenis_kelamin,Stanggal_lahir,Snama_ayah,Sno_telpon,Salamat,Skabupaten,Sprovinsi,Sasal_sekolah,Spaket_bimbel),sId);
                                    }
                                }


                            } else {
                                Toast.makeText(PendaftaranActivity.this, "Koneksi gagal! \n Mohon periksa Internet anda.", Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onCancelled(DatabaseError error) {
                            System.err.println("Listener was cancelled");
                        }
                    });
                }
            });
        }

        private void submitUser(PendaftaranReq kegiatanReq) {
            database.child(Uid)
                    .child("Paket Yang Diambil")
                    .child(Date)
                    .push()
                    .setValue(kegiatanReq)
                    .addOnSuccessListener(this, new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                            loading.dismiss();

                            etNama_lengkap.setText("");
                            etNama_panggilan.setText("");
                            etTanggal_lahir.setText("");
                            etNama_ayah.setText("");
                            etNo_telpon.setText("");
                            etAlamat.setText("");
                            etKabupaten.setText("");
                            etProvinsi.setText("");
                            etAsal_sekolah.setText("");
                            etPaket_bimbel.setText("");

                            Toast.makeText(PendaftaranActivity.this, "Data di tambah!", Toast.LENGTH_SHORT).show();
                            finish();
                            return;

                        }
                    });
        }

        private void editUser(PendaftaranReq pendaftaranReq, String id) {
            database.child(Uid)
                    .child("Paket Yang Diambil")
                    .child(Date)
                    .child(id)
                    .setValue(pendaftaranReq)
                    .addOnSuccessListener(this, new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                            loading.dismiss();

                            etNama_lengkap.setText("");
                            etNama_panggilan.setText("");
                            etTanggal_lahir.setText("");
                            etNama_ayah.setText("");
                            etNo_telpon.setText("");
                            etAlamat.setText("");
                            etKabupaten.setText("");
                            etProvinsi.setText("");
                            etAsal_sekolah.setText("");
                            etPaket_bimbel.setText("");

                            Toast.makeText(PendaftaranActivity.this, "Profil di update!", Toast.LENGTH_SHORT).show();
                            finish();
                            return;
                        }
                    });
        }
}
