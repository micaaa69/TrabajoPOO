package com.example.trabajopoo;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.TextView;
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

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import adaptador.AdaptadorFacultades;

public class LoginActivity extends AppCompatActivity {
    static boolean loading = true;
    AutoCompleteTextView autoCompleteTxt;
    String fecha_envio;
    String [] array_string_facultades;
    String facultad;
    //String profesion;
    EditText userName;
    TextView fecha;
    ImageButton btn_calendario;
    Button btnIngresar;
    RadioButton rbalum, rbadmin, rbprofe,rb_varon, rb_mujer;
    private String cargo = "";
    private boolean sexo, cargando=true;
    private Tipo[] array_tipo;
    private Facultad[] array_facultad;
    private int idPersona;
    int year, month, day;
    ProgressDialog progressDialog, progressDialog_enviandoData;

    private final String URL_TIPO_PERSONA ="http://trabajopoo.kirudental.net/api/apiTipo/listarTodos";
    private final String URL_FACULTADES = "http://trabajopoo.kirudental.net/api/apiFacultad/listarTodos";
    private  final String URL_POST_DATA ="http://trabajopoo.kirudental.net/api/apiPersona/insertar";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        iniciarComponentes();

        Calendar calendario=Calendar.getInstance();
        year=calendario.get(Calendar.YEAR);
        month=calendario.get(Calendar.MONTH);
        day=calendario.get(Calendar.DAY_OF_MONTH);

        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Cargando data ...");
        progressDialog.show();
        array_string_facultades = new String[6];
        array_facultad = new Facultad[6];
        array_tipo = new Tipo[3];

        RequestQueue request = Volley.newRequestQueue(this);
        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL_TIPO_PERSONA, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if (response != null) {
                    try {
                        JSONObject jsonObject = new JSONObject(response);
                        JSONArray data = jsonObject.getJSONArray("data");

                        int idTipo;
                        String nombreTipo;

                        for (int i=0; i<3; i++){
                            JSONObject jsonResponseData = data.getJSONObject(i);
                            //Guardamos los tipos en array
                            int idtipo = jsonResponseData.getInt("idTipo");
                            String tipo = jsonResponseData.getString("nombre");
                            array_tipo[i] = new Tipo(idtipo, tipo, false);
                        }
                        // Guardamos nombre boton
                        rbalum.setText(array_tipo[0].getnombreTipo());
                        rbprofe.setText(array_tipo[1].getnombreTipo());
                        rbadmin.setText(array_tipo[2].getnombreTipo());
                        progressDialog.dismiss();
                    } catch (JSONException ex){
                        ex.printStackTrace();
                    }
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), error.toString(), Toast.LENGTH_SHORT).show();
            }
        });

        request.add(stringRequest);

        //Facultad
        RequestQueue request1 = Volley.newRequestQueue(this);
        StringRequest stringRequest1 = new StringRequest(Request.Method.GET, URL_FACULTADES, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if (response != null) {
                    try {
                        JSONObject jsonObject = new JSONObject(response);
                        JSONArray data = jsonObject.getJSONArray("data");
                        System.out.println(data);
                        int idFacultad;
                        String sigla, nombreFacultad;

                        for (int i=0; i<data.length(); i++){
                            JSONObject jsonObject1 = data.getJSONObject(i);

                            //Guardamos las facultades en array
                            String siglafacu = jsonObject1.getString("sigla");
                            String facu = jsonObject1.getString("nombre");
                            int idfacu = jsonObject1.getInt("idFacultad");

                            array_facultad[i] = new Facultad(idfacu, siglafacu, facu, false);
                            array_string_facultades[i] = facu;
                        }
                    } catch (JSONException ex){
                        ex.printStackTrace();
                    }
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), error.toString(), Toast.LENGTH_SHORT).show();
            }
        });
        request1.add(stringRequest1);

        ArrayAdapter<String> adapterItems= new ArrayAdapter<String>(this,R.layout.list_item,array_string_facultades);
        autoCompleteTxt.setAdapter(adapterItems);
        autoCompleteTxt.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int i, long l) {
                facultad=parent.getItemAtPosition(i).toString();
                switch (facultad){
                    case "Ingieneria":
                        array_facultad[0].setChecked(true);
                        array_facultad[1].setChecked(false);
                        array_facultad[2].setChecked(false);
                        array_facultad[3].setChecked(false);
                        array_facultad[4].setChecked(false);
                        array_facultad[5].setChecked(false);

                        break;
                    case "Empresas":
                        array_facultad[0].setChecked(false);
                        array_facultad[1].setChecked(true);
                        array_facultad[2].setChecked(false);
                        array_facultad[3].setChecked(false);
                        array_facultad[4].setChecked(false);
                        array_facultad[5].setChecked(false);
                        break;
                    case "Derecho":
                        array_facultad[0].setChecked(false);
                        array_facultad[1].setChecked(false);
                        array_facultad[2].setChecked(true);
                        array_facultad[3].setChecked(false);
                        array_facultad[4].setChecked(false);
                        array_facultad[5].setChecked(false);
                        break;
                    case "Comunicación":
                        array_facultad[0].setChecked(false);
                        array_facultad[1].setChecked(false);
                        array_facultad[2].setChecked(false);
                        array_facultad[3].setChecked(true);
                        array_facultad[4].setChecked(false);
                        array_facultad[5].setChecked(false);
                        break;
                    case "Educación":
                        array_facultad[0].setChecked(false);
                        array_facultad[1].setChecked(false);
                        array_facultad[2].setChecked(false);
                        array_facultad[3].setChecked(false);
                        array_facultad[4].setChecked(true);
                        array_facultad[5].setChecked(false);
                        break;
                    case "Humanidades":
                        array_facultad[0].setChecked(false);
                        array_facultad[1].setChecked(false);
                        array_facultad[2].setChecked(false);
                        array_facultad[3].setChecked(false);
                        array_facultad[4].setChecked(false);
                        array_facultad[5].setChecked(true);
                        break;
                }
            }
        });

    }

    public void mostrarCalendario(View view){
        DatePickerDialog dialog = new DatePickerDialog(LoginActivity.this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                month=month+1;
                String date = null;
                if (month>9 && dayOfMonth>9){
                    date=year+"-"+month+"-"+dayOfMonth;
                }
                if (month>9 && dayOfMonth<10){
                    date=year+"-"+month+"-0"+dayOfMonth;
                }
                if (month<10 && dayOfMonth<10){
                    date=year +"-0"+month+"-0"+dayOfMonth;
                }
                if (month<10 && dayOfMonth>9){
                    date=+year  +"-0"+month+"-"+dayOfMonth;
                }
                fecha_envio = date;
                fecha.setText(date);
            }
        },year,month,day);
        dialog.show();
    }
    public void iniciarComponentes(){
        fecha=findViewById(R.id.et_date);
        fecha.setOnClickListener(this::mostrarCalendario);

        btn_calendario=findViewById(R.id.btn_calendario);
        btn_calendario.setOnClickListener(this::mostrarCalendario);

        btnIngresar=findViewById(R.id.button3);
        btnIngresar.setOnClickListener(this::Ingresar);

        autoCompleteTxt=findViewById(R.id.auto_complete_txt);

        //RadioBUTTONS Sexo
        rb_mujer = findViewById(R.id.rb_mujer);
        rb_mujer.setOnClickListener(this::onCheckedSexo);
        rb_varon = findViewById(R.id.rb_varon);
        rb_varon.setOnClickListener(this::onCheckedSexo);

        //RadioButtons Tipo Persona
        rbalum = findViewById(R.id.rbalum);
        rbalum.setOnClickListener(this::onCheckedTipoPersona);
        rbadmin = findViewById(R.id.rbadmin);
        rbadmin.setOnClickListener(this::onCheckedTipoPersona);
        rbprofe = findViewById(R.id.rbprofe);
        rbprofe.setOnClickListener(this::onCheckedTipoPersona);
    }
    public void Ingresar (View view){
        String fecha_s=fecha.getText().toString();
        if (fecha_s.equals("")){
            Toast error= Toast.makeText(this, "Ingrese la fecha",Toast.LENGTH_LONG);
            error.show();
        } //falta


        if (!fecha_s.equals("")){
            mandarDatos();
        }

    }
    public void onCheckedTipoPersona(View view) {
        boolean checked = ((RadioButton) view).isChecked();
        switch (view.getId()){
            case R.id.rbalum:
                if (checked){
                    array_tipo[0].setChecked(true);
                    array_tipo[1].setChecked(false);
                    array_tipo[2].setChecked(false);
                }
                break;
            case R.id.rbprofe:
                if (checked) {
                    array_tipo[1].setChecked(true);
                    array_tipo[0].setChecked(false);
                    array_tipo[2].setChecked(false);
                }
                break;
            case R.id.rbadmin:
                if (checked) {
                    array_tipo[2].setChecked(true);
                    array_tipo[0].setChecked(false);
                    array_tipo[1].setChecked(false);
                }
                break;
        }
    }

    public void onCheckedSexo(View view) {
        boolean checked = ((RadioButton) view).isChecked();
        switch (view.getId()){
            case R.id.rb_varon:
                if (checked) this.sexo=true;
                break;
            case R.id.rb_mujer:
                if (checked) this.sexo = false;
                break;
        }
    }
    public void mandarDatos(){
        RequestQueue postRequest = Volley.newRequestQueue(this);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_POST_DATA, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if (response!=null){

                    try {
                        JSONObject jsonResponse = new JSONObject(response);
                        JSONObject data = jsonResponse.getJSONObject("data");
                        idPersona = data.getInt("idPersona");

                        Intent iIngresarPreguntas = new Intent(LoginActivity.this, PlantillaPreguntas.class);
                        iIngresarPreguntas.putExtra("idPersona",idPersona);
                        startActivity(iIngresarPreguntas);

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
            protected Map<String, String> getParams()
            {
                Map<String, String>  params = new HashMap<>();
                int idFacultad = 1, idTipo = 1, sexo_text=0;
                for(int i=0; i<array_facultad.length;i++){
                    if (array_facultad[i].isChecked()){

                        idFacultad = array_facultad[i].getIdFacultad();
                    }
                }
                for (int i=0;i<array_tipo.length; i++){
                    if (array_tipo[i].getChecked()){

                        idTipo = array_tipo[i].getId_tipo();
                    }
                }
                if (sexo)sexo_text=1;

                params.put("idFacultad", Integer.toString(idFacultad));
                params.put("idTipo",Integer.toString(idTipo));
                params.put("sexo",Integer.toString(sexo_text));
                params.put("fechaNac",fecha_envio);
                return params;
            }


        };
        postRequest.add(stringRequest);
    }

}
