package com.example.trabajopoo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import org.w3c.dom.Text;

public class AdaptadorPregunta extends BaseAdapter {
    private Context mContexto;
    private String pregunta;
    public AdaptadorPregunta(Context mContexto, String pregunta){
        this.mContexto = mContexto;
        this.pregunta = pregunta;
    }
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
        view = LayoutInflater.from(this.mContexto).inflate(R.layout.adaptador_pregunta,null );
        TextView textView = (TextView) view.findViewById(R.id.texto_pregunta);
        textView.setText(this.pregunta);

        return  view;
    }
}
