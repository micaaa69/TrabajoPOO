package com.example.trabajopoo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.RadioButton;

import java.util.ArrayList;

import adaptador.AdaptadorPregunta;

public class PlantillaPreguntas extends AppCompatActivity {
    private RadioButton rdbtn1, rdbtn2, rdbtn3, rdbtn4, rdbtn5;
    private ListView contenedor_preguntas;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plantilla_preguntas);

        int contador = 0;
        String [] preguntas = {"¿Te acuestas cansado en las noches?","¿Realizas ejercicio por las noches?"
                ,"Tomas bebidas energizantes antes de irte a dormir"};

        iniciarRadioButtons();

        String titulo = "Pregunta";

        String [] respuestas = getResources().getStringArray(R.array.Respuestas);

        ArrayList<Pregunta> mArray = new ArrayList<>();


        this.rdbtn1.setText(respuestas[0]);
        this.rdbtn2.setText(respuestas[1]);
        this.rdbtn3.setText(respuestas[2]);
        this.rdbtn4.setText(respuestas[3]);
        this.rdbtn5.setText(respuestas[4]);

        for (int i=0; i<preguntas.length; i++){
            Pregunta pregunta = new Pregunta(preguntas[i],titulo+i);
            mArray.add(pregunta);
        }
        AdaptadorPregunta adaptadorPregunta = new AdaptadorPregunta(this, mArray);
    }
    public void iniciarRadioButtons(){
        rdbtn1 = findViewById(R.id.rdbtn11);
        rdbtn2 = findViewById(R.id.rdbtn22);
        rdbtn3 = findViewById(R.id.rdbtn33);
        rdbtn4 = findViewById(R.id.rdbtn44);
        rdbtn5 = findViewById(R.id.rdbtn55);
    };
}