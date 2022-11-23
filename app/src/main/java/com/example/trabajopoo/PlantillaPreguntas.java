package com.example.trabajopoo;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
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
    private Button btn_sgte, btn_ante;
    private ListView contenedor_preguntas;
    private final String URL_API ="http://trabajopoo.kirudental.net/api/apiPregunta/listarTodos";
    private ArrayList<String> preguntas, respuestas;
    private ArrayList<Integer> id_item;
    private String [] respuesta_item;
    private Pregunta pregunta;
    private  AdaptadorPregunta adaptadorPregunta;
    private ProgressDialog progressDialog;
    private int pagina = 0;
    private String radio_respuesta = "";
    private RadioGroup grupoRespuestas;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plantilla_preguntas);

        iniciarComponentes();

        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Cargando data ...");
        progressDialog.setCancelable(false);
        progressDialog.show();

        // TODO: iniciar variables locales
        preguntas = new ArrayList<>();
        respuestas = new ArrayList<>();
        id_item = new ArrayList<>();

        RequestQueue request = Volley.newRequestQueue(this);
        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL_API, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if (response!=null){
                    try {
                        JSONObject jsonObject = new JSONObject(response);
                        JSONArray jsonArray = jsonObject.getJSONArray("data");
                        for(int i=0; i<10; i++){
                            JSONObject jsonObject1 = jsonArray.getJSONObject(i);
                            //Guardamos las preguntas en un ArrayList
                            preguntas.add(jsonObject1.getString("etiqueta"));
                        }

                        pregunta = new Pregunta();
                        pregunta.setPregunta(preguntas.get(pagina));
                        pregunta.setTitulo("Pregunta"+(pagina+1));

                        ArrayList<Pregunta> item_pregunta = new ArrayList<>();
                        item_pregunta.add(pregunta);

                        adaptadorPregunta = new AdaptadorPregunta(getApplicationContext(), item_pregunta);
                        contenedor_preguntas.setAdapter(adaptadorPregunta);

                        progressDialog.dismiss();
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

    public void iniciarComponentes(){

        grupoRespuestas = findViewById(R.id.grupo_respuestas);
        contenedor_preguntas = findViewById(R.id.contenedor_preguntas);
        respuesta_item = new String[10];

        rdbtn1 = findViewById(R.id.rdbtn11);
        rdbtn1.setOnClickListener(this::onCheckedListener);

        rdbtn2 = findViewById(R.id.rdbtn22);
        rdbtn2.setOnClickListener(this::onCheckedListener); //what is this?

        rdbtn3 = findViewById(R.id.rdbtn33);
        rdbtn3.setOnClickListener(this::onCheckedListener);

        rdbtn4 = findViewById(R.id.rdbtn44);
        rdbtn4.setOnClickListener(this::onCheckedListener);

        rdbtn5 = findViewById(R.id.rdbtn55);
        rdbtn5.setOnClickListener(this::onCheckedListener);

        btn_ante = findViewById(R.id.btn_ante);
        btn_ante.setVisibility(View.GONE);
        btn_ante.setOnClickListener(this::onClickBtnAnte);

        btn_sgte = findViewById(R.id.btn_sgte);
        btn_sgte.setOnClickListener(this::mostrarPreguntas);
    }

    public void mostrarPreguntas(View view){
        if (this.radio_respuesta!=""){
            if (pagina < preguntas.size()){
                pagina+=1;

                if (pagina == preguntas.size()-1) btn_sgte.setText("Terminar");

                respuesta_item[pagina] = this.radio_respuesta;
                grupoRespuestas.clearCheck();

                btn_ante.setVisibility(View.VISIBLE);
                pregunta.setTitulo("Pregunta "+(pagina+1));
                pregunta.setPregunta(preguntas.get(pagina));

                ArrayList<Pregunta> item_pregunta = new ArrayList<>();
                item_pregunta.add(pregunta);

                adaptadorPregunta = new AdaptadorPregunta(this, item_pregunta);
                contenedor_preguntas.setAdapter(adaptadorPregunta);

            }
        }else{
            Toast.makeText(this, "Selecciona una opción",Toast.LENGTH_SHORT).show();
        }
    }

    public void onClickBtnAnte(View view){
        if (pagina>=0) {
            pagina -= 1;

            if (pagina == 0) btn_ante.setVisibility(View.GONE);

            //Cambiar el titulo y cambiar la pregunta a la pregunta anterior
            pregunta.setTitulo("Pregunta" + (pagina + 1));
            pregunta.setPregunta(preguntas.get(pagina));

            ArrayList<Pregunta> item_pregunta = new ArrayList<>();
            item_pregunta.add(pregunta);

            adaptadorPregunta = new AdaptadorPregunta(this, item_pregunta);
            contenedor_preguntas.setAdapter(adaptadorPregunta);

            int id_anterior= this.id_item.get(pagina);
            switch (id_anterior){
                case R.id.rdbtn11:
                    this.rdbtn1.setChecked(true);
                    break;
                case R.id.rdbtn22:
                    this.rdbtn2.setChecked(true);
                    break;
                case R.id.rdbtn33:
                    this.rdbtn3.setChecked(true);
                    break;
                case R.id.rdbtn44:
                    this.rdbtn4.setChecked(true);
                    break;
            }
        }
    }

    public void onCheckedListener(View view){
        boolean checked = ((RadioButton ) view).isChecked();
        switch (view.getId()){
            case R.id.rdbtn11:
                if (checked) {
                    this.radio_respuesta = "Siempre";
                    this.id_item.add(R.id.rdbtn11);
                }
                break;
            case R.id.rdbtn22:
                if (checked) this.radio_respuesta = "Frecuentemente";
                this.id_item.add(R.id.rdbtn22);
                break;
            case R.id.rdbtn33:
                if (checked) this.radio_respuesta = "A veces";
                this.id_item.add(R.id.rdbtn33);
                break;
            case R.id.rdbtn44:
                if (checked) this.radio_respuesta = "Rara vez";
                this.id_item.add(R.id.rdbtn44);
                break;
            case R.id.rdbtn55:
                if (checked) this.radio_respuesta = "Nunca";
                this.id_item.add(R.id.rdbtn55);
                break;
        }
    }

}