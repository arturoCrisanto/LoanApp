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
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import java.util.HashMap;
import java.util.Map;

public class PettyApp extends AppCompatActivity {
    Button back;
    Button submit;
    EditText Hname, Hloan, Hmonthly, Hmonths, Hinterest, Htotal;
    String userID;
    private FirebaseAuth fAuth;
    private FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_petty_app);


        submit = findViewById(R.id.submit);
        back = findViewById(R.id.back);

        Hname = findViewById(R.id.name);
        Hloan = findViewById(R.id.loan);
        Hmonthly = findViewById(R.id.monthly);
        Hmonths = findViewById(R.id.months);
        Hinterest = findViewById(R.id.interest);
        Htotal = findViewById(R.id.total);


        fAuth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(PettyApp.this, Category.class);
                startActivity(i);
                finish();
            }
        });

        submit.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent i = new Intent(PettyApp.this, MainActivity.class);
                startActivity(i);
                finish();
            }
        });
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = Hname.getText().toString();
                String loan = Hloan.getText().toString();
                String monthly = Hmonthly.getText().toString();
                String months = Hmonths.getText().toString();
                String interest = Hinterest.getText().toString();
                String total = Htotal.getText().toString();

                //computation
                int mnt=Integer.parseInt(months);
                int ln=Integer.parseInt(loan);


                int num = 5000;
                int mont;
                int sum ;
                int monthlyP;
                int itr;
                int pty;
                int amortization;

                    mont= (mnt / ln);// Pag kuha an montly payment w/o interest
                    sum = (int) (ln * 0.01); //pag kuha interest
                    monthlyP= sum +mont; // pag compute an monthly with interest

                    itr=sum*mnt; //pag kuha an overall interest
                    pty=mnt*ln; // pag compute han overall without interest

                    amortization= itr+pty;// total loan

                String amor = Integer.toString(amortization);
                String interes = Integer.toString(itr);
                String montP = Integer.toString(monthlyP);

                if(TextUtils.isEmpty(name)){
                    Hname.setError("Name is Required");
                    return;

                }
                if(ln>=num){
                    Hloan.setError("Loan must be bellow 5000");
                    return;
                }
                if(TextUtils.isEmpty(loan)){
                    Hloan.setError("Loan Is required");
                    return;
                }

                if(TextUtils.isEmpty(monthly)){
                    Hmonthly.setText(montP);
                    return;
                }
                if(TextUtils.isEmpty(months)){
                    Hmonths.setError("Input how many Months");
                    return;
                }
                if(mnt>12){
                    Hmonths.setError("Maximum is 12 months");
                    return;
                }
                if(TextUtils.isEmpty(interest)){
                    Htotal.setText(interes);
                    return;
                }
                if(TextUtils.isEmpty(total)){
                    Htotal.setText(amor);
                    return;
                }




                Map<String, Object> petty = new HashMap<>();
                petty.put("Name", name);
                petty.put("Loan", ln);
                petty.put("Montlhy", montP);
                petty.put("Months", months);
                petty.put("Interest", interes);
                petty.put("Total", amor);

                // Add a new document with a generated ID
                db.collection("petty")
                        .add(petty)
                        .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {


                            @Override
                            public void onSuccess(DocumentReference documentReference) {

                                Toast.makeText(PettyApp.this, "Data Stored Successfully",Toast.LENGTH_SHORT).show();
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(PettyApp.this, "Data Not Stored",Toast.LENGTH_SHORT).show();
                            }
                        });



            }

        });

    }

}