package com.example.sayali.bmi;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class HistoryActivity extends AppCompatActivity {

    ListView lvData;
    ArrayList<Bm> b=new ArrayList<>();
    ArrayAdapter<Bm> ad;
    FirebaseDatabase database;
    DatabaseReference myRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        lvData=findViewById(R.id.lvData);
        database=FirebaseDatabase.getInstance();
        myRef=database.getReference("Products");

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                b.clear();
                for (DataSnapshot d : dataSnapshot.getChildren()) {
                    Bm data = d.getValue(Bm.class);
                    b.add(data);

                }
                ad=new ArrayAdapter<Bm>(HistoryActivity.this,android.R.layout.simple_list_item_1,b);
                lvData.setAdapter(ad);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
