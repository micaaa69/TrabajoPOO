package com.example.trabajopoo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class Pregunta1 extends AppCompatActivity {
    private ArrayList <String> array_respuestas;
    private TextView preg1;
    private RadioButton rdbtn1;
    private RadioButton rdbtn2;
    private RadioButton rdbtn3;
    private RadioButton rdbtn4;
    private RadioButton rdbtn5;
    private Button btnsgte;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pregunta1);
        array_respuestas = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            array_respuestas.add(i + 1, "No seleccionado");
        }
        String pregunta = "En las noches me acuesto (o voy a la cama) a diferentes horas";
        AdaptadorPregunta adaptadorPregunta = new AdaptadorPregunta(getApplicationContext(), pregunta);
        ListView listView = findViewById(R.id.pregunta_contenedor);
        listView.setAdapter(adaptadorPregunta);

        rdbtn1 = findViewById(R.id.rdbtn1);
        rdbtn1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b){
                    array_respuestas.set(1, "Frecuentemente");
                }
                array_respuestas.set(1, "No seleccionado");
            }
        });
        rdbtn2 = findViewById(R.id.rdbtn2);
        rdbtn2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b){
                    array_respuestas.set(2, "Frecuentemente");
                }
                array_respuestas.set(2, "No seleccionado");
            }
        });
        rdbtn3 = findViewById(R.id.rdbtn3);
        rdbtn3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b){
                    array_respuestas.set(3, "Frecuentemente");
                }
                array_respuestas.set(3, "No seleccionado");
            }
        });
        rdbtn4 = findViewById(R.id.rdbtn4);
        rdbtn4.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b){
                    array_respuestas.set(4, "Frecuentemente");
                }
                array_respuestas.set(4, "No seleccionado");
            }
        });
        rdbtn5 = findViewById(R.id.rdbtn5);
        rdbtn5.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b){
                    array_respuestas.set(5, "Frecuentemente");
                }
                array_respuestas.set(5, "No seleccionado");
            }
        });

        btnsgte = findViewById(R.id.btnsgt);
        btnsgte.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String text = "";
                for (int i=0;i< array_respuestas.size();i++){
                    text = text + i+" : "+ array_respuestas.get(i);
                }
                Toast.makeText(getApplicationContext(), "Hola",Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(Pregunta1.this, Pregunta2.class);

                startActivity(intent);

            }

        });
    }

}