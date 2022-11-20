package com.example.trabajopoo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

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
    private  String respuesta = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pregunta4);

        Pregunta pregunta = new Pregunta("Me voy a dormir sintiéndome estresado, molesto, triste o nervioso.","Pregunta 4");
        this.preguntas = new ArrayList<>();
        this.preguntas.add(pregunta);
        AdaptadorPregunta adaptadorPregunta = new AdaptadorPregunta(this, this.preguntas);

        rdbtn1 = findViewById(R.id.rdbtn1);
        rdbtn1.setOnClickListener(this::onCheckedListener);

        rdbtn2 = findViewById(R.id.rdbtn2);
        rdbtn2.setOnClickListener(this::onCheckedListener);

        rdbtn3 = findViewById(R.id.rdbtn3);
        rdbtn3.setOnClickListener(this::onCheckedListener);

        rdbtn4 = findViewById(R.id.rdbtn4);
        rdbtn4.setOnClickListener(this::onCheckedListener);

        rdbtn5 = findViewById(R.id.rdbtn5);
        rdbtn5.setOnClickListener(this::onCheckedListener);

        btnsgte = findViewById(R.id.btnsgt);
        btnante = findViewById(R.id.btnante);

        btnsgte.setOnClickListener(this::onClickBtnSgte);
        btnante.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Pregunta4.this, Pregunta3.class);
                startActivity(intent);

            }


        });
    }
    public void onClickBtnSgte(View view){
        if (this.respuesta != ""){
            ArrayList<String> respuestas= getIntent().getStringArrayListExtra("Respuesta");
            respuestas.add(this.respuesta);
            Intent intent = new Intent(Pregunta4.this, Pregunta5.class);
            intent.putStringArrayListExtra("Respuesta",respuestas);
            startActivity(intent);
        }else {
            Toast.makeText(this, "Seleccione una respuesta", Toast.LENGTH_SHORT).show();
        }

    }
    public void onCheckedListener(View view){
        boolean checked = ((RadioButton ) view).isChecked();
        switch (view.getId()){
            case R.id.rdbtn1:
                if (checked)this.respuesta = "Siempre";
                break;
            case R.id.rdbtn2:
                if (checked) this.respuesta = "Frecuentemente";
                break;
            case R.id.rdbtn3:
                if (checked) this.respuesta = "A veces";
                break;
            case R.id.rdbtn4:
                if (checked) this.respuesta = "Rara vez";
                break;
            case R.id.rdbtn5:
                if (checked) this.respuesta = "Nunca";
                break;
        }
    }

}