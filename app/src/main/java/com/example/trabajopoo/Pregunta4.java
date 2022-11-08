package com.example.trabajopoo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;

public class Pregunta4 extends AppCompatActivity {
    private TextView preg1;
    private RadioButton rdbtn1;
    private RadioButton rdbtn2;
    private RadioButton rdbtn3;
    private RadioButton rdbtn4;
    private RadioButton rdbtn5;
    private Button btnsgte;
    private Button btnante;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pregunta4);
        preg1 = findViewById(R.id.preg1);
        rdbtn1 = findViewById(R.id.rdbtn1);
        rdbtn2 = findViewById(R.id.rdbtn2);
        rdbtn3 = findViewById(R.id.rdbtn3);
        rdbtn4 = findViewById(R.id.rdbtn4);
        rdbtn5 = findViewById(R.id.rdbtn5);
        btnsgte = findViewById(R.id.btnsgte);
        btnante = findViewById(R.id.btnante);

        /*btnsgte.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view  ) {
                Intent intent = new Intent(Pregunta4.this, Pregunta5.class);
                startActivity(intent);
            }
        });*/
        btnante.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Pregunta4.this, Pregunta3.class);
                startActivity(intent);

            }


        });
    }
}