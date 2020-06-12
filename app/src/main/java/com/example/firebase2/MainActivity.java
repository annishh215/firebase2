package com.example.firebase2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {
    private EditText et1;
    private EditText et2;
    private Button b;
    private String t1,t2;
    private String e;

    DatabaseReference dbu;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        et2 = (EditText) findViewById(R.id.et2);
        et1 = (EditText) findViewById(R.id.et1);
        b = (Button) findViewById(R.id.b);
        et2.setEnabled(false);
        et1.setEnabled(false);




        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dbu = FirebaseDatabase.getInstance().getReference("user");
                e = b.getText().toString();
                if (e.equals("Edit")){
                    et2.setEnabled(true);
                    et1.setEnabled(true);
                    b.setText("Save");
                }if (e.equals("Save")){
                    add();
                    b.setText("Edit");
                    et2.setEnabled(false);
                    et1.setEnabled(false);
                }
            }
        });

        dbu.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String n = dataSnapshot.child("name").getValue().toString();
                String c = dataSnapshot.child("cals").getValue().toString();
                et1.setText(n);
                et2.setText(c);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }

    private void add() {

            t1 = et1.getText().toString();
            t2 = et2.getText().toString();
            String id = dbu.push().getKey();
            user u = new user(id,t1,t2);
            dbu.child(id).setValue(u);

    }


}


