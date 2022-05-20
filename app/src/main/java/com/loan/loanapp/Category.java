package com.loan.loanapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Category extends AppCompatActivity {
    Button back,emergency,petty;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);

        back = findViewById(R.id.back);
        emergency = findViewById(R.id.emergency);
        petty = findViewById(R.id.petty);


        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Category.this, MainPage.class);
                startActivity(i);
                finish();
            }
        });
        emergency.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Category.this, EmergencyApp.class);
                startActivity(i);
                finish();
            }
        });
        petty.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Category.this, PettyApp.class);
                startActivity(i);
                finish();
            }
        });

    }
}