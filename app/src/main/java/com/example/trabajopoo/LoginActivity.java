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
import android.widget.Toast;

import java.util.Calendar;
import java.util.Date;

public class LoginActivity extends AppCompatActivity {
    AutoCompleteTextView autoCompleteTxt, autoCompleteTxt_2;

    String[] facultades={"Ingeniería","Empresas","Derecho","Comunicaciones","Humanidades","Educación"};
    String[] profesiones={"Profesor","Alumno","Administrativo"};
    String facultad;
    String profesion;
    EditText userName, fecha;
    ImageButton btn_calendario;
    Button btnIngresar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        //relacionar los componentes gráficos con los lógicos

        autoCompleteTxt=findViewById(R.id.auto_complete_txt);
        autoCompleteTxt_2=findViewById(R.id.auto_complete_txt_2);
        userName=findViewById(R.id.et_user);
        fecha=findViewById(R.id.et_date);
        btn_calendario=findViewById(R.id.btn_calendario);
        btnIngresar=findViewById(R.id.button3);

        Calendar calendario=Calendar.getInstance();
        int year=calendario.get(Calendar.YEAR);
        int month=calendario.get(Calendar.MONTH);
        int day=calendario.get(Calendar.DAY_OF_MONTH);



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


        ArrayAdapter<String>  adapterItems= new ArrayAdapter<String>(this,R.layout.list_item,facultades);

        autoCompleteTxt.setAdapter(adapterItems);

        autoCompleteTxt.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int i, long l) {
                facultad=parent.getItemAtPosition(i).toString();
            }
        });

        ArrayAdapter<String> adapterItems_2=new ArrayAdapter<String>(this,R.layout.list_item,profesiones);

        autoCompleteTxt_2.setAdapter(adapterItems_2);

        autoCompleteTxt_2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int i, long l) {
                profesion=parent.getItemAtPosition(i).toString();
            }


        });
        btnIngresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, Pregunta1.class);
                startActivity(intent);
            }
        });

    }


    public void ingresar (View view){
        String nombre=userName.getText().toString();
        String fecha_s=fecha.getText().toString();

        if (nombre.equals("")){
            Toast error= Toast.makeText(this, "Ingrese su nombre",Toast.LENGTH_LONG);
            error.show();
        }

        if (fecha_s.equals("")){
            Toast error= Toast.makeText(this, "Ingrese la fecha",Toast.LENGTH_LONG);
            error.show();
        } //falta

        if (!nombre.equals("")&&!fecha_s.equals("")){
            Intent iIngresarPreguntas = new Intent(this, MainActivity.class);
            iIngresarPreguntas.putExtra("userName",nombre);
            startActivity(iIngresarPreguntas);
        }

    }


}