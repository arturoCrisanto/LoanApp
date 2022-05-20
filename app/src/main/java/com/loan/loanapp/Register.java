package com.loan.loanapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.nfc.Tag;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import java.util.HashMap;
import java.util.Map;
import java.util.jar.Attributes;

public class Register extends AppCompatActivity {
    EditText lname,lemail,lpassword,laddress,lbirthdate,lcontact;
    TextView lbtn;
    Button lregister, lbackBtn;

    private FirebaseAuth fAuth;
    private FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        getSupportActionBar().setTitle("Register");

        lname = findViewById(R.id.name);
        lemail = findViewById(R.id.email);
        lpassword = findViewById(R.id.password);
        laddress = findViewById(R.id.address);
        lbirthdate = findViewById(R.id.birthdate);
        lcontact = findViewById(R.id.contact);

        lbackBtn = findViewById(R.id.backBtn);
        lbtn = findViewById(R.id.btn);
        lregister = findViewById(R.id.register);

        fAuth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();

        if (fAuth.getCurrentUser() !=null){
            startActivity(new Intent(getApplicationContext(),MainActivity.class));
            finish();
        }

        lbackBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Register.this, MainActivity.class);
                startActivity(i);
                finish();
            }
        });

        lbtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent i = new Intent(Register.this, MainActivity.class);
                startActivity(i);
                finish();
            }
        });

        lregister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = lname.getText().toString().trim();
                String email = lemail.getText().toString().trim();
                String password = lpassword.getText().toString().trim();
                String address = laddress.getText().toString().trim();
                String birth = lbirthdate.getText().toString().trim();
                String contact = lcontact.getText().toString().trim();

                if(TextUtils.isEmpty(name)){
                    lname.setError("Name is Required");
                    return;
                }
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
                if(TextUtils.isEmpty(address)){
                    laddress.setError("Address is Required");
                    return;
                }
                if(TextUtils.isEmpty(birth)){
                    lbirthdate.setError("Birth Date is Required");
                    return;
                }
                if(TextUtils.isEmpty(contact)){
                    lcontact.setError("Contact Number is Required");
                    return;
                }

                fAuth.createUserWithEmailAndPassword(email,password).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                    @Override
                    public void onSuccess(AuthResult authResult) {
                        Map<String, Object> user = new HashMap<>();
                        user.put("Name", name);
                        user.put("Email", email);
                        user.put("Password", password);
                        user.put("Address", address);
                        user.put("Birthdate", birth);
                        user.put("Contact", contact);

                        db.collection("users")
                                .add(user)
                                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                                    @Override
                                    public void onSuccess(DocumentReference documentReference) {
                                        Toast.makeText(Register.this, "Data Stored Successfully",Toast.LENGTH_LONG).show();
                                        Intent intent = new Intent(Register.this, MainActivity.class);
                                        startActivity(intent);
                                        finish();
                                    }
                                })
                                .addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {

                                        Toast.makeText(Register.this, "Error ! " + e.getMessage(),Toast.LENGTH_SHORT).show();

                                    }
                                });
                    }
                });
            }
        });

    }

    }
