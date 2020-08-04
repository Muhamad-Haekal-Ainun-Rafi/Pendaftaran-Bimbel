package com.lailatul.bimbel.Program;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import com.lailatul.bimbel.R;
public class ProgramActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_program);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Program Pembelajaran");
    }
}
