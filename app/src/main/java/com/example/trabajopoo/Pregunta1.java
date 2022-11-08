package com.example.trabajopoo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.TextView;

public class Pregunta1 extends AppCompatActivity {
    private TextView preg1;
    private RadioButton rdbtn1;
    private RadioButton rdbtn2;
    private RadioButton rdbtn3;
    private RadioButton rdbtn4;
    private RadioButton rdbtn5;
    private Button btnsgte;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pregunta1);

        preg1 = findViewById(R.id.preg1);
        rdbtn1 = findViewById(R.id.rdbtn1);
        rdbtn2 = findViewById(R.id.rdbtn2);
        rdbtn3 = findViewById(R.id.rdbtn3);
        rdbtn4 = findViewById(R.id.rdbtn4);
        rdbtn5 = findViewById(R.id.rdbtn5);

        btnsgte = findViewById(R.id.btnsgte);
        /*RadioButtomAdapter radioButtomAdapter = new RadioButtomAdapter();
        ListView listView = (ListView) findViewById(R.id.containerpreguntas);
        listView.setAdapter(radioButtomAdapter);

*/
        btnsgte.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Pregunta1.this, Pregunta2.class);
                startActivity(intent);

            }

        });
    }

    public void mostrarPanel(View view){
        boolean checked = ((RadioButton) view).isChecked();


    }
    }