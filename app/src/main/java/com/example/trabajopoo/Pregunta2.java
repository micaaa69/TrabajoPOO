package com.example.trabajopoo;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

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
    private ListView listView;
    private ArrayList <Pregunta> mPregunta;
    private ProgressDialog progressDialog;
    private Pregunta pregunta1;
    private String respuesta ="";
    private AdaptadorPregunta adaptadorPregunta;
    final String URL_PREGUNTAS = "http://trabajopoo.kirudental.net/api/apiPregunta/listarPorId/2";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pregunta2);

        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Por favor espera ..");
        progressDialog.setCancelable(false);
        progressDialog.show();

        pregunta1 = new Pregunta();

        RequestQueue request = Volley.newRequestQueue(this);
        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL_PREGUNTAS, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if (response!=null){
                    progressDialog.dismiss();
                    try {
                        JSONObject jsonObject = new JSONObject(response);
                        JSONObject data = jsonObject.getJSONObject("data");
                        String pregunta_texto = data.getString("etiqueta");
                        pregunta1.setPregunta(pregunta_texto);
                        pregunta1.setTitulo("Pregunta 1");

                        mPregunta = new ArrayList<>();
                        System.out.println("=====================================================");
                        System.out.println(pregunta1);
                        mPregunta.add(pregunta1);

                        adaptadorPregunta = new AdaptadorPregunta(getApplicationContext(), mPregunta);
                        listView = findViewById(R.id.contenedor_pregunta);
                        listView.setAdapter(adaptadorPregunta);
                        System.out.println(pregunta1);

                    }catch (JSONException ex){
                        ex.printStackTrace();
                    }
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(),error.toString(),Toast.LENGTH_SHORT).show();
            }
        });

        request.add(stringRequest);

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

        btnsgte = findViewById(R.id.btnsgt);
        btnante = findViewById(R.id.btnante);



        btnante.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Pregunta2.this, Pregunta1.class);
                startActivity(intent);

            }


        });
        btnsgte.setOnClickListener(this::onClickBtnSgte);
    }
    public void onClickBtnAnte(View view){
        ArrayList<String> respuestas = getIntent().getStringArrayListExtra("Respuesta");

    }
    public void onClickBtnSgte(View view){
        if (this.respuesta != ""){
            ArrayList<String> respuestas = getIntent().getStringArrayListExtra("Respuesta");
            respuestas.add(this.respuesta);
            Intent intent = new Intent(Pregunta2.this, Pregunta3.class);
            intent.putStringArrayListExtra("Respuesta",respuestas);
            String text = "Pregunta 1: "+respuestas.get(0)+"\n Pregunta 2: "+respuestas.get(1);
            Toast.makeText(this, text,Toast.LENGTH_SHORT).show();
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