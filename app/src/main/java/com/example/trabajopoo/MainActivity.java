package com.example.trabajopoo;

import androidx.appcompat.app.AppCompatActivity;
import android.widget.Button;
import android.content.Intent;
import android.view.View;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private TextView txtitulo1;
    private ImageView garfield;
    private Button btnIniciarTest;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtitulo1=findViewById(R.id.txtitulo1);
        garfield=findViewById(R.id.garfield);
        btnIniciarTest=findViewById(R.id.btnInicioTest);

        btnIniciarTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =  new Intent(MainActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });



    }
}