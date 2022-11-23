package com.example.trabajopoo;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ProgressBar;
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

        Pregunta pregunta1 = new Pregunta();
        String URL_PREGUNTAS = "http://trabajopoo.kirudental.net/api/apiPregunta/listarPorId/1";
        ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Por favor espera ..");
        progressDialog.setCancelable(false);
        progressDialog.show();

        RequestQueue request = Volley.newRequestQueue(this);
        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL_PREGUNTAS, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if (response!=null){
                    progressDialog.dismiss();
                    try {
                        JSONObject jsonObject = new JSONObject(response);
                        JSONObject data = jsonObject.getJSONObject("data");
                        System.out.println(data);
                        pregunta1.setPregunta(data.getString("etiqueta"));
                        pregunta1.setTitulo("Pregunta 1");

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

        mPregunta = new ArrayList<>();
        mPregunta.add(pregunta1);

        AdaptadorPregunta adaptadorPregunta = new AdaptadorPregunta(this ,this.mPregunta);
        listView = findViewById(R.id.contenedor_pregunta);
        listView.setAdapter(adaptadorPregunta);

        this.rdbtn1 = findViewById(R.id.rdbtn11);
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
    public ArrayList<String> llamarAPI(Context context){
        ArrayList <String> mArrayApi = new ArrayList<>();

        return mArrayApi;
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

/*

 */