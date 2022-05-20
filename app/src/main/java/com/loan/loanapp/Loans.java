package com.loan.loanapp;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Loans extends AppCompatActivity {
    Button emergency;
    Button petty;
    Button back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loans);

        getSupportActionBar().setTitle("Loans");

        emergency = findViewById(R.id.emergency);
        petty = findViewById(R.id.petty);
        back = findViewById(R.id.back);

        emergency.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Loans.this, EmergencyLoan.class));
                finish();
            }
        });

        petty.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Loans.this,PettyApp.class));
                finish();
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Loans.this,MainPage.class));
                finish();
            }
        });

    }
}