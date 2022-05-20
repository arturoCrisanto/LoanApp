package com.loan.loanapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class EmergencyLoan extends AppCompatActivity {
    Button emergencyBTN;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emergency_loan);

        getSupportActionBar().setTitle("Emergency Loan");

        emergencyBTN = findViewById(R.id.emergencyBTN);

        emergencyBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(EmergencyLoan.this, Loans.class);
                startActivity(i);
                finish();
            }
        });
    }
}