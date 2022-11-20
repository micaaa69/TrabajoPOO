package com.example.trabajopoo;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.Response.ErrorListener;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

import adaptador.AdaptadorPregunta;

public class Pregunta1 extends AppCompatActivity {
    private String respuesta = "";
    private RadioButton rdbtn1;
    private RadioButton rdbtn2;
    private RadioButton rdbtn3;
    private RadioButton rdbtn4;
    private RadioButton rdbtn5;
    private Button btnsgte;
    ListView listView;
    ArrayList<Pregunta> mPregunta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pregunta1);

        Pregunta pregunta1 = new Pregunta("En las noches me acuesto (o voy a la cama) a diferentes horas","Pregunta 1");
        mPregunta = new ArrayList<>();
        mPregunta.add(pregunta1);

        AdaptadorPregunta adaptadorPregunta = new AdaptadorPregunta(this ,this.mPregunta);
        listView = findViewById(R.id.contenedor_pregunta);
        listView.setAdapter(adaptadorPregunta);

        this.rdbtn1 = findViewById(R.id.rdbtn1);
        this.rdbtn1.setOnClickListener(this::onCheckedListener);

        this.rdbtn2 = findViewById(R.id.rdbtn2);
        this.rdbtn2.setOnClickListener(this::onCheckedListener);

        this.rdbtn3 = findViewById(R.id.rdbtn3);
        this.rdbtn3.setOnClickListener(this::onCheckedListener);

        this.rdbtn4 = findViewById(R.id.rdbtn4);
        this.rdbtn4.setOnClickListener(this::onCheckedListener);

        this.rdbtn5 = findViewById(R.id.rdbtn5);
        this.rdbtn5.setOnClickListener(this::onCheckedListener);

        btnsgte = findViewById(R.id.btnsgt);
        btnsgte.setOnClickListener(this::onClickBtnSgte);
    }

    public void onClickBtnSgte(View view){
        if (this.respuesta!=""){
            ArrayList<String> respuestas = new ArrayList<>();
            respuestas.add(respuesta);
            Intent intent = new Intent(Pregunta1.this, Pregunta2.class);
            intent.putStringArrayListExtra("Respuesta",respuestas);
            Toast.makeText(this,"Pregunta 1 : "+ this.respuesta, Toast.LENGTH_SHORT).show();
            startActivity(intent);
        }else{
            Toast.makeText(this, "Selecciona una respuesta ",Toast.LENGTH_SHORT).show();
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