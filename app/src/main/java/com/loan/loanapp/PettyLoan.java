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
    TextView name,Mpayment,total;
    private FirebaseAuth fAuth;
    private FirebaseFirestore db;
    String userID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_petty_loan);

        getSupportActionBar().setTitle("Petty Loan");

        pettyBTN = findViewById(R.id.pettyBTN);
        name = findViewById(R.id.Name);
        Mpayment = findViewById(R.id.mpayment);
        total = findViewById(R.id.total);

        fAuth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();

        userID=fAuth.getCurrentUser().getUid();

        DocumentReference documentReference= db.collection("petty").document(userID);
        documentReference.addSnapshotListener(this, new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot documentSnapshot,@Nullable FirebaseFirestoreException e) {
             name.setText(documentSnapshot.getString("name"));
             Mpayment.setText(documentSnapshot.getString("Monthly"));
             total.setText(documentSnapshot.getString("Total"));
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