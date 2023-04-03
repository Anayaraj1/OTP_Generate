package com.example.otp_exampke;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.firebase.FirebaseException;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {

    EditText ednumber;
    Button btnotp;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ednumber = findViewById(R.id.ed_number);
        btnotp = findViewById(R.id.btn_otp_send);
        progressBar = findViewById(R.id.progress_otp_bar);

        btnotp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progressBar.setVisibility(View.VISIBLE);
                btnotp.setVisibility(View.INVISIBLE);

                PhoneAuthProvider.getInstance().verifyPhoneNumber("+91" + ednumber.getText().toString(), 60, TimeUnit.SECONDS, MainActivity.this,
                        new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
                            @Override
                            public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {
                                progressBar.setVisibility(View.GONE);
                                btnotp.setVisibility(View.VISIBLE);
                            }

                            @Override
                            public void onVerificationFailed(@NonNull FirebaseException e) {
                                progressBar.setVisibility(View.GONE);
                                btnotp.setVisibility(View.VISIBLE);
                                Log.d("mydata","-----"+e.getMessage());
                                Toast.makeText(MainActivity.this, "Error: "+ e.getMessage(), Toast.LENGTH_SHORT).show();
                            }

                            @Override
                            public void onCodeSent(@NonNull String backendotp, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
//                                super.onCodeSent(backendotp, forceResendingToken);
                                progressBar.setVisibility(View.GONE);
                                btnotp.setVisibility(View.VISIBLE);

                                Intent i = new Intent(getApplicationContext(),Verification_Example.class);
                                i.putExtra("mobile",ednumber.getText().toString());
                                i.putExtra("backendotp",backendotp);
                                startActivity(i);
                            }
                        });
            }
        });
    }
}