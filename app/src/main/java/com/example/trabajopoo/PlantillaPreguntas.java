package com.example.trabajopoo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Array;
import java.util.ArrayList;

import adaptador.AdaptadorPregunta;

public class PlantillaPreguntas extends AppCompatActivity {
    private RadioButton rdbtn1, rdbtn2, rdbtn3, rdbtn4, rdbtn5;
    private ListView contenedor_preguntas;
    private final String URL_API ="http://trabajopoo.kirudental.net/api/apiPregunta/listarTodos";
    private ArrayList<String> preguntas;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plantilla_preguntas);

        int contador = 0;
        iniciarRadioButtons();

        RequestQueue request = Volley.newRequestQueue(this);
        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL_API, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if (response!=null){

                    try {
                        JSONObject jsonObject = new JSONObject(response);
                        JSONArray jsonArray = jsonObject.getJSONArray("data");



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


    }
    public void iniciarRadioButtons(){
        rdbtn1 = findViewById(R.id.rdbtn11);
        rdbtn2 = findViewById(R.id.rdbtn22);
        rdbtn3 = findViewById(R.id.rdbtn33);
        rdbtn4 = findViewById(R.id.rdbtn44);
        rdbtn5 = findViewById(R.id.rdbtn55);
    };
}