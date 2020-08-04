package com.lailatul.bimbel.Menu;

import androidx.annotation.DrawableRes;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.content.Intent;
import android.os.Bundle;
import android.text.Layout;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.lailatul.bimbel.Login.LoginActivity;
import com.lailatul.bimbel.Mentor.MentorActivity;
import com.lailatul.bimbel.Pendaftaran.HariListActivity;
import com.lailatul.bimbel.Program.ProgramActivity;
import com.lailatul.bimbel.R;
import com.lailatul.bimbel.Setting.SettingActivity;

public class MenuActivity extends AppCompatActivity {
    private String Uid;
    private ImageButton BTN_DAFTAR, BTN_PROGRAM, BTN_MENTOR, BTN_SETTING;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_activity);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        DatabaseReference database = FirebaseDatabase.getInstance().getReference();
        Uid = getIntent().getStringExtra("Uid");

        BTN_DAFTAR = findViewById(R.id.logo_mentor2);
        BTN_PROGRAM = findViewById(R.id.btn_program);
        BTN_MENTOR = findViewById(R.id.btn_mentor);
        BTN_SETTING = findViewById(R.id.btn_setting);
        registerForContextMenu(BTN_SETTING);

        DatabaseReference offline = FirebaseDatabase.getInstance().getReference().child("Users");
        offline.keepSynced(true);

        menu();

        database.child("Users").child(Uid).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v,ContextMenu.ContextMenuInfo menuInfo){
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.context_setting,menu);
    }
    @Override
    public boolean onContextItemSelected(MenuItem item){

        switch (item.getItemId())
        {
            case R.id.option_1:
                Intent intent = new Intent(MenuActivity.this, SettingActivity.class);
                intent.putExtra("Uid", Uid);
                startActivity(intent);
                return true;

            case R.id.option_2:
                FirebaseAuth.getInstance().signOut();
                Intent intent2 = new Intent(MenuActivity.this, LoginActivity.class);
                startActivity(intent2);
                finish();

                return true;
            default:
            return super.onContextItemSelected(item);



        }

    }

    void menu() {

        findViewById(R.id.logo_mentor2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuActivity.this, HariListActivity.class);
                intent.putExtra("Uid", Uid);
                startActivity(intent);
            }
        });

        findViewById(R.id.btn_program).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuActivity.this, ProgramActivity.class);
                intent.putExtra("Uid", Uid);
                startActivity(intent);
            }
        });

        findViewById(R.id.btn_mentor).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuActivity.this, MentorActivity.class);
                intent.putExtra("Uid", Uid);
                startActivity(intent);
            }
        });






    }
}

