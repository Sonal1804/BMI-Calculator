package com.example.sayali.bmi;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.time.LocalDate;
import java.time.LocalTime;

public class ResultActivity extends AppCompatActivity {

    TextView tvResult,tv1,tv2,tv3,tv4;
    Button btnBack, btnShare, btnSave;
    SharedPreferences sp;
    FirebaseDatabase database;
    DatabaseReference myRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        tvResult=findViewById(R.id.tvResult);
        tv1=findViewById(R.id.tv1);
        tv2=findViewById(R.id.tv2);
        tv3=findViewById(R.id.tv3);
        tv4=findViewById(R.id.tv4);
        btnBack=findViewById(R.id.btnBack);
        btnSave=findViewById(R.id.btnSave);
        btnShare=findViewById(R.id.btnShare);

        database=FirebaseDatabase.getInstance();
        myRef=database.getReference("bm");

        sp=getSharedPreferences("myp3",MODE_PRIVATE);
        final String n=sp.getString("name","");
        String a=sp.getString("age","");
        String p=sp.getString("phone","");

        Intent b= getIntent();
        final String bm=b.getStringExtra("BMI");
        double bm1=Double.parseDouble(bm);
        String res1="";
        if (bm1<18.5){
            res1="You are UnderWeight";
        }
        else if (bm1>=18.5 && bm1<=25){
            res1="You are Normal";
        }
        else if (bm1>=26 && bm1<=30)
        {
            res1="You are OverWeight";
        }
        else{
            res1="you are Obess";
        }
        final String Bmii="Your BMI is "+bm+" and "+res1;
        tvResult.setText(Bmii);


        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent a= new Intent(ResultActivity.this,CalcuActivity.class);
                tvResult.setText("");
                startActivity(a);
            }
        });

        final String collage="Name :"+n+"\n"+"Age :"+a+"\n"+"Phone Number :"+p+"\n"+"BMI :"+bm+"\n"+res1;

        btnShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent a= new Intent(Intent.ACTION_SEND);
                a.putExtra(Intent.EXTRA_TEXT,collage);
                a.setType("text/plain");
                startActivity(a);

            }
        });

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LocalDate date=LocalDate.now();
                String d=date.toString();
                LocalTime time=LocalTime.now();
                String t=time.toString();

                Bm b=new Bm(d,t,Bmii);
                myRef.push().setValue(b);
                Toast.makeText(ResultActivity.this, "Information is Successfully Save", Toast.LENGTH_SHORT).show();


            }
        });

    }
}
