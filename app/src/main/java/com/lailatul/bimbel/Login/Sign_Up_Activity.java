package com.lailatul.bimbel.Login;

        import androidx.annotation.NonNull;
        import androidx.appcompat.app.ActionBar;
        import androidx.appcompat.app.AppCompatActivity;

        import android.app.ProgressDialog;
        import android.content.Intent;
        import android.os.Bundle;
        import android.text.method.HideReturnsTransformationMethod;
        import android.text.method.PasswordTransformationMethod;
        import android.view.View;
        import android.widget.Button;
        import android.widget.CheckBox;
        import android.widget.CompoundButton;
        import android.widget.EditText;
        import android.widget.TextView;
        import android.widget.Toast;

        import com.google.android.gms.tasks.OnCompleteListener;
        import com.google.android.gms.tasks.OnSuccessListener;
        import com.google.android.gms.tasks.Task;
        import com.google.firebase.auth.AuthResult;
        import com.google.firebase.auth.FirebaseAuth;
        import com.google.firebase.auth.FirebaseUser;
        import com.google.firebase.database.DatabaseReference;
        import com.google.firebase.database.FirebaseDatabase;
        import com.lailatul.bimbel.R;
        import com.lailatul.bimbel.model.Request;

public class Sign_Up_Activity extends AppCompatActivity {

    private EditText mEmail, mPassword,mTLahir,mSekolah,mNama,mAlamat;
    private Button mRegistration;
    private TextView tLogin;
    private CheckBox checkBox;

    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener firebaseAuthStateListener;
    private ProgressDialog loading;
    private DatabaseReference database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_up_activity);

        getWindow().setStatusBarColor(getResources().getColor(R.color.ijo));
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        database = FirebaseDatabase.getInstance().getReference();
        mAuth = FirebaseAuth.getInstance();

        firebaseAuthStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {

                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                if ((user!=null)){
                    Intent intent = new Intent(Sign_Up_Activity.this, LoginActivity.class);
                    startActivity(intent);
                    finish();
                    return;
                }
            }
        };

        mNama = (EditText) findViewById(R.id.txt_nama);
        mAlamat = (EditText) findViewById(R.id.txt_alamat);
        mTLahir = (EditText) findViewById(R.id.txt_lahir);
        mSekolah = (EditText) findViewById(R.id.txt_sekolah);
        mEmail = (EditText) findViewById(R.id.txt_email);
        mPassword = (EditText) findViewById(R.id.txt_password);
        tLogin = (TextView) findViewById(R.id.txtLogin);
        mRegistration = (Button) findViewById(R.id.btn_regist);

        checkBox = findViewById(R.id.checkbox);

        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (!isChecked){
                    mPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
                }else {
                    mPassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());

                }
            }
        });

        mRegistration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String Snama = mNama.getText().toString();
                final String Salamat = mAlamat.getText().toString();
                final String Ssekolah = mSekolah.getText().toString();
                final String Stanggal_lahir = mTLahir.getText().toString();

                final String Sfoto = "";

                final String Semail = mEmail.getText().toString();
                final String Spassword = mPassword.getText().toString();

                if (Snama.equals("")){
                    mNama.setError("Masukkan nama terlebih dahulu !");
                    mNama.requestFocus();
                }
                else if (Salamat.equals("")){
                    mAlamat.setError("Masukkan alamat rumahmu !");
                    mAlamat.requestFocus();
                }
                else if (Ssekolah.equals("")){
                    mSekolah.setError("Masukkan asal sekolahmu saat ini !");
                    mSekolah.requestFocus();
                }
                else if (Stanggal_lahir.equals("")){
                    mTLahir.setError("Masukkan tanggal lahirmu !");
                    mTLahir.requestFocus();
                }
                else if (Semail.equals("")){
                    mEmail.setError("Masukkan email terlebih dahulu!");
                    mEmail.requestFocus();
                }

                else if (Spassword.equals("")){
                    mPassword.setError("Masukkan password terlebih dahulu!");
                    mPassword.requestFocus();
                }

                else {
                    loading = ProgressDialog.show(Sign_Up_Activity.this,
                            "",
                            "Please wait...",
                            true,
                            false);

                    mAuth.createUserWithEmailAndPassword(Semail, Spassword).addOnCompleteListener(Sign_Up_Activity.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {

                            if (!task.isSuccessful()){
                                loading.dismiss();
                                Toast.makeText(Sign_Up_Activity.this, "Sign Up error!", Toast.LENGTH_SHORT).show();
                            }else {
                                submitUser(new Request(
                                        Snama,
                                        Salamat,
                                        Ssekolah,
                                        Stanggal_lahir,
                                        Semail,
                                        Sfoto
                                ));
                            }
                        }
                    });


                }
            }
        });


        tLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Sign_Up_Activity.this, LoginActivity.class);
                startActivity(intent);
                finish();
                return;
            }
        });
    }

    private void submitUser(Request request) {
        String user_id = mAuth.getCurrentUser().getUid();
        database.child("Users")
                .child(user_id)
                .setValue(request)
                .addOnSuccessListener(this, new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        loading.dismiss();

                        mNama.setText("");
                        mNama.setText("");
                        mSekolah.setText("");
                        mTLahir.setText("");
                        mAlamat.setText("");
                        mEmail.setText("");
                        mPassword.setText("");

                        Toast.makeText(Sign_Up_Activity.this, "daftar berhasil!", Toast.LENGTH_SHORT).show();
                        finish();
                        return;

                    }
                });
    }

    @Override
    protected void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(firebaseAuthStateListener);
    }

    @Override
    protected void onStop() {
        super.onStop();
        mAuth.removeAuthStateListener(firebaseAuthStateListener);
    }
}
