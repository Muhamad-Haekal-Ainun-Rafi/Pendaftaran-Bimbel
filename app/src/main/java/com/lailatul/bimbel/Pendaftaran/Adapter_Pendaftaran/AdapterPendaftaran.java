package com.lailatul.bimbel.Pendaftaran.Adapter_Pendaftaran;
import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.lailatul.bimbel.Pendaftaran.PendaftaranActivity;

import com.lailatul.bimbel.R;
import com.lailatul.bimbel.Pendaftaran.PendaftaranReq;

import java.util.ArrayList;
import java.util.List;

public class AdapterPendaftaran extends RecyclerView.Adapter<AdapterPendaftaran.MyViewHolder> {

    private List<PendaftaranReq> LabList;
    private Activity mActivity;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public LinearLayout rl_layout;
        public TextView tv_nama,tv_paket,tv_tl,tv_jk,tv_sekolah;

        public MyViewHolder(View view){
            super(view);
            rl_layout = view.findViewById(R.id.lab_layout);
            tv_paket = view.findViewById(R.id.lab_judul);
            tv_nama = view.findViewById(R.id.lab_nama);
            tv_tl= view.findViewById(R.id.lab_lahir);
            tv_jk = view.findViewById(R.id.lab_jk);
            tv_sekolah = view.findViewById(R.id.lab_sekolah);


        }
    }

    public AdapterPendaftaran (ArrayList<PendaftaranReq> LabList, Activity activity){
        this.mActivity = activity;
        this.LabList = LabList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_pendaftaran,parent,false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        final PendaftaranReq lab = LabList.get(position);
        holder.tv_paket.setText(lab.getPaket_bimbel());
        holder.tv_nama.setText(lab.getNama_lengkap());
        holder.tv_tl.setText(lab.getTanggal_lahir());
        holder.tv_jk.setText(lab.getJenis_kelamin());
        holder.tv_sekolah.setText(lab.getAsal_sekolah());


        holder.rl_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(v.getContext(), "Tahan Profile Untuk Mengedit ", Toast.LENGTH_SHORT).show();
            }
        });

        holder.rl_layout.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Intent getDetail = new Intent(mActivity, PendaftaranActivity.class);

                getDetail.putExtra("Uid",lab.getUserId());
                getDetail.putExtra("date",lab.getDate());
                getDetail.putExtra("id",lab.getKey());
                getDetail.putExtra("paket_bimbel",lab.getPaket_bimbel());
                getDetail.putExtra("nama_lengkap",lab.getNama_lengkap());
                getDetail.putExtra("tanggal_lahir",lab.getTanggal_lahir());
                getDetail.putExtra("jenis_kelamin",lab.getJenis_kelamin());
                getDetail.putExtra("asal_sekolah",lab.getAsal_sekolah());
                getDetail.putExtra("nama_panggilan",lab.getNama_panggilan());
                getDetail.putExtra("nama_ayah",lab.getNama_ayah());
                getDetail.putExtra("alamat",lab.getAlamat());
                getDetail.putExtra("kabupaten",lab.getKabupaten());
                getDetail.putExtra("provinsi",lab.getProvinsi());
                getDetail.putExtra("no_telpon",lab.getNo_telpon());

                mActivity.startActivity(getDetail);
                return false;
            }
        });
    }

    @Override
    public int getItemCount() {
        return LabList.size();
    }
}

