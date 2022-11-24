package com.example.trabajopoo;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
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

    String[] facultades={"Ingeniería","Empresas","Derecho","Comunicaciones","Humanidades","Educación"};
    //String[] profesiones={"Profesor","Alumno","Administrativo"};
    String facultad;
    //String profesion;
    EditText userName;
    TextView fecha;
    ImageButton btn_calendario;
    Button btnIngresar;
    Button rbalum, rbadmin, rbprofe;
    Button rb_varon, rb_mujer;
    private String cargo ="";
    private String sexo = "";
    private Tipo[] array_tipo;
    private Facultad[] array_facultad;
    private ArrayList<Integer> id_btntipo;
    private ArrayList<Integer> id_btnfacultad;
    private final String URL_API1 = "http://trabajopoo.kirudental.net/api/apiPelicula/listarTodos";
    private final String URL_API ="http://trabajopoo.kirudental.net/api/apiTipo/listarTodos";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        //relacionar los componentes gráficos con los lógicos

        autoCompleteTxt=findViewById(R.id.auto_complete_txt);
        //autoCompleteTxt_2=findViewById(R.id.auto_complete_txt_2);
        fecha=findViewById(R.id.et_date);
        btn_calendario=findViewById(R.id.btn_calendario);
        btnIngresar=findViewById(R.id.button3);


        Calendar calendario=Calendar.getInstance();
        int year=calendario.get(Calendar.YEAR);
        int month=calendario.get(Calendar.MONTH);
        int day=calendario.get(Calendar.DAY_OF_MONTH);
        //JSONObject jsonObject = new JSONObject(response);
        //JSONObject jsonObject1 = jsonArray.getJSONObject(i);


        RequestQueue request = Volley.newRequestQueue(this);
        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL_API, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if (response != null) {
                    try {
                        JSONObject jsonObject = new JSONObject(response);
                        JSONArray jsonArray = jsonObject.getJSONArray("data");

                        int idTipo;
                        String nombreTipo;

                        array_tipo = new Tipo[3];
                        id_btntipo = new ArrayList<>();

                        for (int i=0; i<3; i++){
                            JSONObject jsonObject1 = jsonArray.getJSONObject(i);
                            //Guardamos los tipos en array
                            String tipo = jsonObject1.getString("nombre");
                            int idtipo = jsonObject.getInt("idTipo");

                            array_tipo[i] = new Tipo( idtipo, tipo);
                            ArrayList<Tipo> tipoArrayList = new ArrayList<>();
                            tipoArrayList.add(array_tipo[i]);

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

        request.add(stringRequest);

        //Facultad
        RequestQueue request1 = Volley.newRequestQueue(this);
        StringRequest stringRequest1 = new StringRequest(Request.Method.GET, URL_API1, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if (response != null) {
                    try {
                        JSONObject jsonObject = new JSONObject(response);
                        JSONArray jsonArray = jsonObject.getJSONArray("data");

                        int idFacultad;
                        String sigla, nombreFacultad;

                        array_facultad = new Facultad[6];
                        id_btnfacultad = new ArrayList<>();

                        for (int i=0; i<6; i++){
                            JSONObject jsonObject1 = jsonArray.getJSONObject(i);
                            //Guardamos las facultades en array
                            String siglafacu = jsonObject1.getString("sigla");
                            String facu = jsonObject1.getString("nombre");
                            int idfacu = jsonObject1.getInt("idTipo");

                            array_facultad[i] = new Facultad(idfacu, siglafacu, facu);
                            ArrayList<Facultad> FacuArrayList = new ArrayList<>();
                            FacuArrayList.add(array_facultad[i]);

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
            }
        });


        btnIngresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, PlantillaPreguntas.class);
                startActivity(intent);
            }
        });

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
    public void onCheckedListener(View view) {
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
    public void onCheckedListener2(View view) {
        boolean checked = ((RadioButton) view).isChecked();
        switch (view.getId()){
            case R.id.rb_varon:
                if (checked) this.sexo="Varon";
                break;
            case R.id.rb_mujer:
                if (checked) this.sexo = "Mujer";
                break;
        }
    }


}