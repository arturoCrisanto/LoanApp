package com.loan.loanapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;
import android.widget.EditText;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

public class MainActivity extends AppCompatActivity {
    EditText lemail, lpassword;
    Button llogin;
    TextView lregBtn;
    FirebaseAuth fAuth;
    FirebaseFirestore fStore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().setTitle("Login");

        lemail = findViewById(R.id.email);
        lpassword = findViewById(R.id.password);
        llogin = findViewById(R.id.login);
        lregBtn = findViewById(R.id.regBtn);
        fAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();

        llogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = lemail.getText().toString().trim();
                String password = lpassword.getText().toString().trim();

                if(TextUtils.isEmpty(email)){
                    lemail.setError("Email is Required");
                    return;
                }
                if(TextUtils.isEmpty(password)){
                    lpassword.setError("Password is Required");
                    return;
                }
                if(password.length() < 5){
                    lpassword.setError("Password must have at least 8 Characters");
                    return;
                }
                fAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(MainActivity.this, "Logged in Successfully",Toast.LENGTH_LONG).show();
                            startActivity(new Intent(getApplicationContext(), MainPage.class));
                        }else {
                            Toast.makeText(MainActivity.this, "Error ! " + task.getException().getMessage(),Toast.LENGTH_LONG).show();
                        }
                    }
                });
            }
        });

        lregBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, Register.class);
                startActivity(i);
                finish();
            }
        });
    }
}
