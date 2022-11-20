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

public class Pregunta4 extends AppCompatActivity {
    private TextView preg1;
    private RadioButton rdbtn1;
    private RadioButton rdbtn2;
    private RadioButton rdbtn3;
    private RadioButton rdbtn4;
    private RadioButton rdbtn5;
    private Button btnsgte;
    private Button btnante;
    private ListView contenedor_preguntas;
    private ArrayList<Pregunta> preguntas;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pregunta4);

        Pregunta pregunta = new Pregunta("Me voy a dormir sintiéndome estresado, molesto, triste o nervioso.","Pregunta 4");
        this.preguntas = new ArrayList<>();
        this.preguntas.add(pregunta);
        AdaptadorPregunta adaptadorPregunta = new AdaptadorPregunta(this, this.preguntas);

        rdbtn1 = findViewById(R.id.rdbtn1);
        rdbtn2 = findViewById(R.id.rdbtn2);
        rdbtn3 = findViewById(R.id.rdbtn3);
        rdbtn4 = findViewById(R.id.rdbtn4);
        rdbtn5 = findViewById(R.id.rdbtn5);
        btnsgte = findViewById(R.id.btnsgt);
        btnante = findViewById(R.id.btnante);

        btnsgte.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view  ) {
                Intent intent = new Intent(Pregunta4.this, Pregunta5.class);
                startActivity(intent);
            }
        });
        btnante.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Pregunta4.this, Pregunta3.class);
                startActivity(intent);

            }


        });
    }
    public void mostrarPanel(View view){
        boolean checked = ((RadioButton) view).isChecked();


    }

}