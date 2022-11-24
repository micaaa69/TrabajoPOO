package com.example.trabajopoo;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
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
import java.util.HashMap;
import java.util.Map;
import java.util.Queue;

import adaptador.AdaptadorPregunta;

public class PlantillaPreguntas extends AppCompatActivity {
    private RadioButton rdbtn1, rdbtn2, rdbtn3, rdbtn4, rdbtn5;
    private Button btn_sgte, btn_ante;
    private ListView contenedor_preguntas;

    private final String URL_API ="http://trabajopoo.kirudental.net/api/apiPregunta/listarTodos";
    private final String URL_ALTERNATIVA = "http://trabajopoo.kirudental.net/api/apiAlternativa/listarTodos";
    private final String URL_POSTRESPUESTA = "http://trabajopoo.kirudental.net/api/apiRespuesta/insertar";

    private ArrayList<String> preguntas, respuestas;
    private ArrayList<Integer> id_item;
    private ArrayList<Alternativa> list_alternativas;
    private HashMap<Integer, Alternativa> respuestas_final;
    private Pregunta [] lista_preguntas;
    private AdaptadorPregunta [] lista_adaptadorPregunta;
    private String [] respuesta_item;
    private Alternativa[] alternativas;
    private Pregunta pregunta;
    private  AdaptadorPregunta adaptadorPregunta;
    private ProgressDialog progressDialog;
    private int pagina = 0;
    private String radio_respuesta = "";
    private RadioGroup grupoRespuestas;
    private Boolean [] ifChecked;
    private int idPersona;
    private JSONArray json_respuestas;
    private int contador_alter=0;
    private int idRespuesta, idPregunta = 1;
    private boolean terminaronPreguntas = false;
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
        lista_preguntas = new Pregunta[10];
        lista_adaptadorPregunta = new AdaptadorPregunta[10];
        json_respuestas = new JSONArray();
        preguntas = new ArrayList<>();
        respuestas = new ArrayList<>();
        list_alternativas = new ArrayList<>();
        respuestas_final = new HashMap<>();
        //Almacenamos el idPersona que devuelve el API
        idPersona = getIntent().getIntExtra("idPersona",1);

        RequestQueue request = Volley.newRequestQueue(this);
        StringRequest requestPreguntas = new StringRequest(Request.Method.GET, URL_API, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if (response!=null){
                    try {
                        JSONObject jsonObject = new JSONObject(response);
                        JSONArray jsonArray = jsonObject.getJSONArray("data");


                        id_item = new ArrayList<>();
                        ifChecked = new Boolean[10];
                        alternativas = new Alternativa[10];

                        for(int i=0; i<10; i++){
                            JSONObject jsonObject1 = jsonArray.getJSONObject(i);
                            //Guardamos las preguntas en un ArrayList
                            String titulo = "Pregunta "+(i+1);
                            String preg = jsonObject1.getString("etiqueta");
                            int idPregunta = jsonObject1.getInt("idPregunta");

                            ifChecked[i] = false;
                            lista_preguntas[i] = new Pregunta(idPregunta,preg,titulo);
                            alternativas[i] = new Alternativa();

                            ArrayList<Pregunta> preguntaArrayList = new ArrayList<>();
                            preguntaArrayList.add(lista_preguntas[i]);

                            lista_adaptadorPregunta[i] = new AdaptadorPregunta(getApplicationContext(), preguntaArrayList);
                            id_item.add(0);
                        }

                        contenedor_preguntas.setAdapter(lista_adaptadorPregunta[pagina]);
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

        RequestQueue request_alt = Volley.newRequestQueue(this);
        StringRequest requestAlternativas = new StringRequest(Request.Method.GET, URL_ALTERNATIVA, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if (response!=null){
                    try {
                        JSONObject jsonObject = new JSONObject(response);
                        JSONArray data = jsonObject.getJSONArray("data");
                        for (int i=0; i<50; i++){

                            JSONObject jsonObject1 = data.getJSONObject(i);
                            int idPregunta = jsonObject1.getInt("idPregunta");
                            int idAlternativa = jsonObject1.getInt("idAlternativa");
                            String etiqueta = jsonObject1.getString("etiqueta");

                            //Adaptador
                            Alternativa alternativa = new Alternativa(idAlternativa,idPregunta,etiqueta);
                            list_alternativas.add(alternativa);
                        }
                    }catch (JSONException ex){
                        ex.printStackTrace();
                    }
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), error.toString(), Toast.LENGTH_LONG);
            }
        });
        request.add(requestPreguntas);
        request_alt.add(requestAlternativas);
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
            if (pagina < 10 && !terminaronPreguntas){

                respuesta_item[pagina] = this.radio_respuesta;
                alternativas[pagina].setEtiqueta(this.radio_respuesta);
                alternativas[pagina].setIdCuestionario(this.idRespuesta);

                System.out.println(pagina);
                System.out.println("contador_alter -> "+contador_alter);
                System.out.println("Id_respuesta -> "+idRespuesta);
                // Reseteo el valor de radio_respuesta
                this.radio_respuesta="";
                if (ifChecked[pagina]){
                    positionRadioChecked(pagina);
                }else{
                    ifChecked[pagina]=true;
                    grupoRespuestas.clearCheck();
                }

                contenedor_preguntas.setAdapter(lista_adaptadorPregunta[pagina]);
                btn_ante.setVisibility(View.VISIBLE);
                contador_alter+=5;

                if (pagina == lista_preguntas.length-1) {
                    btn_sgte.setText("Terminar");
                    terminaronPreguntas=true;
                } else{
                    btn_sgte.setText("Continuar");
                    pagina++;
                }
            }else{
                for (int i=0; i<10; i++){
                    enviarData(i);

                }
                System.out.println("IdPersona -> "+idPersona);
                Intent intent = new Intent(this, ActivityFinal.class);
                startActivity(intent);
            }
        }else{
            Toast.makeText(this, "Selecciona una opción",Toast.LENGTH_SHORT).show();
        }
    }

    public void onClickBtnAnte(View view){
        if (pagina>0) {
            pagina -= 1;
            contador_alter-=5;
            if (pagina == 0) btn_ante.setVisibility(View.GONE);
            contenedor_preguntas.setAdapter(lista_adaptadorPregunta[pagina]);
            positionRadioChecked(pagina);
        }
    }

    public void positionRadioChecked(int position){
        int id_radio = id_item.get(position);
        switch (id_radio){
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
            case R.id.rdbtn55:
                this.rdbtn5.setChecked(true);
                break;
        }
    }
    public void onCheckedListener(View view){
        boolean checked = ((RadioButton ) view).isChecked();
        switch (view.getId()){
            case R.id.rdbtn11:
                if (checked) this.radio_respuesta = "Siempre";
                this.id_item.set(pagina, R.id.rdbtn11);
                this.idRespuesta = contador_alter+1;
                break;
            case R.id.rdbtn22:
                if (checked) this.radio_respuesta = "Frecuentemente";
                this.id_item.set(pagina, R.id.rdbtn22);
                this.idRespuesta = contador_alter+2;
                break;
            case R.id.rdbtn33:
                if (checked) this.radio_respuesta = "A veces";
                this.id_item.set(pagina, R.id.rdbtn33);
                this.idRespuesta = contador_alter+3;
                break;
            case R.id.rdbtn44:
                if (checked) this.radio_respuesta = "Rara vez";
                this.id_item.set(pagina, R.id.rdbtn44);
                this.idRespuesta = contador_alter+4;
                break;
            case R.id.rdbtn55:
                if (checked) this.radio_respuesta = "Nunca";
                this.id_item.set(pagina, R.id.rdbtn55);
                this.idRespuesta = contador_alter+5;
                break;
        }
    }
    public void enviarData(int j){
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        StringRequest postData = new StringRequest(Request.Method.POST, URL_POSTRESPUESTA, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if (response!=null){
                    try {
                        JSONObject jsonObject = new JSONObject(response);
                        System.out.println("Pagina "+j+ jsonObject.getString("message"));
                    }catch (JSONException ex){
                        ex.printStackTrace();
                    }
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), error.toString(), Toast.LENGTH_LONG).show();
            }
        }){
            @Override
            protected Map<String, String> getParams(){
                Map <String,String > params = new HashMap<>();

                params.put("idPersona", Integer.toString(idPersona));
                params.put("idAlternativa",Integer.toString(alternativas[j].getIdCuestionario()));

                return params;
            }
        };

        requestQueue.add(postData);
    }
}