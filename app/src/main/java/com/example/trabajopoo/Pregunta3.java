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

public class Pregunta3 extends AppCompatActivity {
    private TextView preg1;
    private RadioButton rdbtn1;
    private RadioButton rdbtn2;
    private RadioButton rdbtn3;
    private RadioButton rdbtn4;
    private RadioButton rdbtn5;
    private Button btnsgte;
    private Button btnante;
    private ListView contenedor_preguntas;
    private ArrayList<Pregunta> mPreguntas;
    private String respuesta;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pregunta3);

        this.contenedor_preguntas = findViewById(R.id.contendor_preguntas3);
        Pregunta pregunta = new Pregunta("Consumo alcohol, tabaco o café cuatro horas antes de ir a la cama","Pregunta 3");
        this.mPreguntas = new ArrayList<>();
        this.mPreguntas.add(pregunta);
        AdaptadorPregunta adaptadorPregunta = new AdaptadorPregunta(this, this.mPreguntas);
        this.contenedor_preguntas.setAdapter(adaptadorPregunta);

        rdbtn1 = findViewById(R.id.rdbtn11);
        rdbtn1.setOnClickListener(this::onCheckedListener);

        rdbtn2 = findViewById(R.id.rdbtn2);
        rdbtn2.setOnClickListener(this::onCheckedListener);

        rdbtn3 = findViewById(R.id.rdbtn3);
        rdbtn3.setOnClickListener(this::onCheckedListener);

        rdbtn4 = findViewById(R.id.rdbtn4);
        rdbtn4.setOnClickListener(this::onCheckedListener);

        rdbtn5 = findViewById(R.id.rdbtn5);
        rdbtn5.setOnClickListener(this::onCheckedListener);

        btnante = findViewById(R.id.btnante);
        btnante.setOnClickListener(this::onClickBtnAnte);

        btnsgte = findViewById(R.id.btnsgt);
        btnsgte.setOnClickListener(this::onClickBtnSgte);
    }
    public void onClickBtnAnte(View view){
        Intent intent = new Intent(Pregunta3.this, Pregunta2.class);
        startActivity(intent);
    }
    public void onClickBtnSgte(View view){
        if (this.respuesta != ""){
            ArrayList<String> respuestas= getIntent().getStringArrayListExtra("Respuesta");
            respuestas.add(this.respuesta);

            Intent intent = new Intent(Pregunta3.this, Pregunta4.class);
            intent.putStringArrayListExtra("Respuesta",respuestas);
            startActivity(intent);

        }else {
            Toast.makeText(this, "Seleccione una respuesta", Toast.LENGTH_SHORT).show();
        }
    }

    public void onCheckedListener(View view){
        boolean checked = ((RadioButton ) view).isChecked();
        switch (view.getId()){
            case R.id.rdbtn11:
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