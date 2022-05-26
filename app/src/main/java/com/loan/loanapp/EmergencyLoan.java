package com.loan.loanapp;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

public class EmergencyLoan extends AppCompatActivity {
    Button emergencyBTN;
    private FirebaseAuth fAuth;
    private FirebaseFirestore db;
    String userID;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emergency_loan);

        getSupportActionBar().setTitle("Emergency Loan");

        emergencyBTN = findViewById(R.id.emergencyBTN);
        fAuth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();

        userID=fAuth.getCurrentUser().getUid();

        emergencyBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(EmergencyLoan.this, Loans.class);
                startActivity(i);
                finish();
            }
        });

        DocumentReference documentReference= db.collection("emergency").document(userID);
        documentReference.addSnapshotListener(this, new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException e) {

            }
        });



    }
}