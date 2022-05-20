package com.loan.loanapp;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

public class PettyLoan extends AppCompatActivity {
    Button pettyBTN;
    TextView loan;
    private FirebaseAuth fAuth;
    private FirebaseFirestore db;
    String userID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_petty_loan);

        getSupportActionBar().setTitle("Petty Loan");

        pettyBTN = findViewById(R.id.pettyBTN);


        fAuth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();

        userID=fAuth.getCurrentUser().getUid();

        DocumentReference documentReference= db.collection("petty").document(userID);
        documentReference.addSnapshotListener(this, new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {

            }
        });


        pettyBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(PettyLoan.this, Loans.class);
                startActivity(i);
                finish();
            }
        });
    }
}