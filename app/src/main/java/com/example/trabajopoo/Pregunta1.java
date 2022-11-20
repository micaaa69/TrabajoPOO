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

import adaptador.AdaptadorPregunta;

public class Pregunta1 extends AppCompatActivity {
    private ArrayList <String> array_respuestas;
    private TextView preg1;
    private String resupuesta;
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

        btnsgte = findViewById(R.id.btnsgt);
        btnsgte.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Pregunta1.this, Pregunta2.class);
                startActivity(intent);
            }
        });


    }

    public void parseArray(JSONArray jsonArray){

    }
}