package com.example.otp_exampke;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

public class Verification_Example extends AppCompatActivity {

    EditText edverify;
    TextView tvotp;
    Button btnotp;
    String backendotp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verification_example);

        edverify = findViewById(R.id.ed_verify_otp);
        tvotp = findViewById(R.id.tv_verify_otp);
        btnotp = findViewById(R.id.btn_otp);

        backendotp = getIntent().getStringExtra("backendotp");


        btnotp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                PhoneAuthCredential phoneAuthCredential = PhoneAuthProvider.getCredential(backendotp,edverify.getText().toString());
                FirebaseAuth.getInstance().signInWithCredential(phoneAuthCredential)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful())
                                {
                                    Intent i =new Intent(Verification_Example.this,Dashboard.class);
                                    i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                    startActivity(i);
                                }
                            }
                        });
            }
        });
    }
}