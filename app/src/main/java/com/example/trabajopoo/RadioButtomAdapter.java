package com.example.trabajopoo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CompoundButton;
import android.widget.ListView;
import android.widget.RadioButton;

import java.util.ArrayList;

public class RadioButtomAdapter extends BaseAdapter {
    Context mContext;
    ArrayList<String> respuestas;
    String resp;



    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        view = LayoutInflater.from(mContext).inflate(R.layout.botones,null);
        RadioButton radio1 = view.findViewById(R.id.rdbtn1);
        radio1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b){
                   respuestas.set(i, "siempre") ;
                }
            }
        });
        RadioButton radio2 = view.findViewById(R.id.rdbtn2);
        RadioButton radio3 = view.findViewById(R.id.rdbtn3);
        RadioButton radio4 = view.findViewById(R.id.rdbtn4);
        RadioButton radio5 = view.findViewById(R.id.rdbtn5);


        return view;
    }
}
