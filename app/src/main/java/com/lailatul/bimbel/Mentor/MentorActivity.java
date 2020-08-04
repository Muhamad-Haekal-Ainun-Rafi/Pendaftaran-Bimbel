package com.lailatul.bimbel.Mentor;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import com.lailatul.bimbel.R;
public class MentorActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mentor);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Mentor Pembelajaran");
    }
}
