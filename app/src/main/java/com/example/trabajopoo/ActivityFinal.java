package com.example.trabajopoo;

import androidx.appcompat.app.AppCompatActivity;


import android.os.Bundle;
import android.view.View;


public class ActivityFinal extends AppCompatActivity {
    private String URL = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_final);
        String [] respuestas = getIntent().getStringArrayExtra("Respuestas");
        System.out.println(respuestas);
    }

    public void enviarDatos(View view){

    }
}