package com.example.prm3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final Button registerButton = (Button) findViewById(R.id.button2);
        final Button loginButton = (Button) findViewById(R.id.button);
        final EditText username = (EditText) findViewById(R.id.username);
        final EditText password = (EditText) findViewById(R.id.password);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseReference reference = FirebaseDatabase.getInstance().getReference("User");
                reference.orderByChild("email").equalTo(username.getText().toString()).addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        for (DataSnapshot datas : dataSnapshot.getChildren()) {
                            String passwordFromDatabase = datas.child("password").getValue().toString();
                            if (passwordFromDatabase.equals(password.getText().toString())) {
                                Intent afterLoginintent = new Intent(MainActivity.this, AfterLogin.class);
                                afterLoginintent.putExtra("username",username.getText().toString());
                                MainActivity.this.startActivity(afterLoginintent);
                            } else {
                                password.setError("Nieprawidlowe haslo");
                            }
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                    }
                });
            }
        });


        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent registerintent = new Intent(MainActivity.this, RegisterActivity.class);
                MainActivity.this.startActivity(registerintent);
            }
        });
    }
}
