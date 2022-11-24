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

public class LoginActivity extends AppCompatActivity {

    AutoCompleteTextView autoCompleteTxt, autoCompleteTxt_2;

    String[] facultades = {"Ingeniería", "Empresas", "Derecho", "Comunicaciones", "Humanidades", "Educación"};
    String [] array_string_facultades;
    String facultad;
    //String profesion;
    EditText userName;
    TextView fecha;
    ImageButton btn_calendario;
    Button btnIngresar;
    RadioButton rbalum, rbadmin, rbprofe;
    RadioButton rb_varon, rb_mujer;
    private String cargo = "";
    private boolean sexo;
    private Tipo[] array_tipo;
    private Facultad[] array_facultad;
    private ArrayList<Integer> id_btntipo;
    private ArrayList<Integer> id_btnfacultad;

    private ProgressDialog progressDialog;

    private final String URL_TIPO_PERSONA ="http://trabajopoo.kirudental.net/api/apiTipo/listarTodos";
    private final String URL_FACULTADES = "http://trabajopoo.kirudental.net/api/apiFacultad/listarTodos";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        iniciarComponentes();

        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Cargando la data ...");
        progressDialog.show();

        Calendar calendario=Calendar.getInstance();
        int year=calendario.get(Calendar.YEAR);
        int month=calendario.get(Calendar.MONTH);
        int day=calendario.get(Calendar.DAY_OF_MONTH);


        RequestQueue request = Volley.newRequestQueue(this);
        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL_TIPO_PERSONA, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if (response != null) {
                    try {
                        JSONObject jsonObject = new JSONObject(response);
                        JSONArray jsonArray = jsonObject.getJSONArray("data");

                        int idTipo;
                        String nombreTipo;

                        array_tipo = new Tipo[jsonArray.length()];
                        id_btntipo = new ArrayList<>();

                        for (int i=0; i<3; i++){
                            JSONObject jsonObject1 = jsonArray.getJSONObject(i);
                            //Guardamos los tipos en array
                            int idtipo = jsonObject.getInt("idTipo");
                            String tipo = jsonObject1.getString("nombre");

                            array_tipo[i] = new Tipo( idtipo, tipo);
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
                        JSONArray jsonArray = jsonObject.getJSONArray("data");

                        int idFacultad;
                        String sigla, nombreFacultad;

                        array_string_facultades = new String[jsonArray.length()];
                        array_facultad = new Facultad[jsonArray.length()];

                        for (int i=0; i<6; i++){
                            JSONObject jsonObject1 = jsonArray.getJSONObject(i);

                            //Guardamos las facultades en array
                            String siglafacu = jsonObject1.getString("sigla");
                            String facu = jsonObject1.getString("nombre");
                            int idfacu = jsonObject1.getInt("idTipo");


                            array_facultad[i] = new Facultad(idfacu, siglafacu, facu);
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

            }
        });

        request1.add(stringRequest1);


        btn_calendario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog dialog = new DatePickerDialog(LoginActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        month=month+1;
                        String date = null;
                        if (month>9 && dayOfMonth>9){
                            date=dayOfMonth+"/"+month+"/"+year;
                        }
                        if (month>9 && dayOfMonth<10){
                            date="0"+dayOfMonth+"/"+month+"/"+year;
                        }
                        if (month<10 && dayOfMonth<10){
                            date="0"+dayOfMonth+"/0"+month+"/"+year;
                        }
                        if (month<10 && dayOfMonth>9){
                            date=+dayOfMonth+"/0"+month+"/"+year;
                        }
                        fecha.setText(date);
                    }
                },year,month,day);
                dialog.show();
            }

        });

        fecha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog dialog = new DatePickerDialog(LoginActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        month=month+1;
                        String date = null;
                        if (month>9 && dayOfMonth>9){
                            date=dayOfMonth+"/"+month+"/"+year;
                        }
                        if (month>9 && dayOfMonth<10){
                            date="0"+dayOfMonth+"/"+month+"/"+year;
                        }
                        if (month<10 && dayOfMonth<10){
                            date="0"+dayOfMonth+"/0"+month+"/"+year;
                        }
                        if (month<10 && dayOfMonth>9){
                            date=+dayOfMonth+"/0"+month+"/"+year;
                        }
                        fecha.setText(date);
                    }
                },year,month,day);
                dialog.show();
            }

        });


        ArrayAdapter<String>  adapterItems= new ArrayAdapter<String>(this,R.layout.list_item,facultades);

        autoCompleteTxt.setAdapter(adapterItems);
        autoCompleteTxt.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int i, long l) {
                facultad=parent.getItemAtPosition(i).toString();
                System.out.println(facultad);
            }
        });

        btnIngresar.setOnClickListener(this::Ingresar);

    }
    public void iniciarComponentes(){
        fecha=findViewById(R.id.et_date);
        btn_calendario=findViewById(R.id.btn_calendario);
        btnIngresar=findViewById(R.id.button3);
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
        String nombre=userName.getText().toString();
        String fecha_s=fecha.getText().toString();


        if (fecha_s.equals("")){
            Toast error= Toast.makeText(this, "Ingrese la fecha",Toast.LENGTH_LONG);
            error.show();
        } //falta


        if (!fecha_s.equals("")){
            Intent iIngresarPreguntas = new Intent(this, PlantillaPreguntas.class);
            iIngresarPreguntas.putExtra("userName",nombre);
            startActivity(iIngresarPreguntas);
        }

    }
    public void onCheckedTipoPersona(View view) {
        boolean checked = ((RadioButton) view).isChecked();
        switch (view.getId()){
            case R.id.rbalum:
                if (checked) this.cargo = "Alumno";
                break;
            case R.id.rbprofe:
                if (checked) this.cargo = "Docente";
                break;
            case R.id.rbadmin:
                if (checked) this.cargo = "Administrador";
                break;
        }
    }

    public void onCheckedSexo(View view) {
        boolean checked = ((RadioButton) view).isChecked();
        switch (view.getId()){
            case R.id.rb_varon:
                if (checked) this.sexo=true;                break;
            case R.id.rb_mujer:
                if (checked) this.sexo = false;
                break;
        }
    }
    public void mandarDatos(){

    }

}