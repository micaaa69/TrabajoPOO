package com.example.trabajopoo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.TextView;

import java.util.ArrayList;

import adaptador.AdaptadorPregunta;

public class Pregunta2 extends AppCompatActivity {
    private RadioButton rdbtn1;
    private RadioButton rdbtn2;
    private RadioButton rdbtn3;
    private RadioButton rdbtn4;
    private RadioButton rdbtn5;
    private Button btnsgte;
    private Button btnante;
    private ListView contenedorPreguntas;
    private ArrayList <Pregunta> mPregunta;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pregunta2);

        rdbtn1 = findViewById(R.id.rdbtn1);
        rdbtn2 = findViewById(R.id.rdbtn2);
        rdbtn3 = findViewById(R.id.rdbtn3);
        rdbtn4 = findViewById(R.id.rdbtn4);
        rdbtn5 = findViewById(R.id.rdbtn5);


        btnsgte = findViewById(R.id.btnsgt);
        btnante = findViewById(R.id.btnante);

        Pregunta pregunta = new Pregunta("Una hora antes de ir a dormir realizo ejercicio físico.","Pregunta 2");
        this.mPregunta = new ArrayList<>();
        this.mPregunta.add(pregunta);
        AdaptadorPregunta adaptadorPregunta = new AdaptadorPregunta(this, this.mPregunta);
        this.contenedorPreguntas = findViewById(R.id.contenedor_preguntas2);
        this.contenedorPreguntas.setAdapter(adaptadorPregunta);

        btnante.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Pregunta2.this, Pregunta1.class);
                startActivity(intent);

            }


        });
        btnsgte.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view  ) {
                Intent intent = new Intent(Pregunta2.this, Pregunta3.class);
                startActivity(intent);
            }
        });
    }

    public void mostrarPanel2(View view){
        boolean checked = ((RadioButton) view).isChecked();


    }
}