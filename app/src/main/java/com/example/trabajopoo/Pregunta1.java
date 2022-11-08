package com.example.trabajopoo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.TextView;

public class Pregunta1 extends AppCompatActivity {
    private TextView preg1;
    private RadioButton rdbtn1;
    private RadioButton rdbtn2;
    private RadioButton rdbtn3;
    private RadioButton rdbtn4;
    private RadioButton rdbtn5;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pregunta1);

        preg1 =findViewById(R.id.preg1);
        rdbtn1=findViewById(R.id.rdbtn1);
        rdbtn2=findViewById(R.id.rdbtn2);
        rdbtn3=findViewById(R.id.rdbtn3);
        rdbtn4=findViewById(R.id.rdbtn4);
        rdbtn5=findViewById(R.id.rdbtn5);

    }

    public void opciones(View view){
        boolean checked = ((RadioButton) view).isChecked();

    }
}