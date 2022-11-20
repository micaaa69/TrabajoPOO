package com.example.trabajopoo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.List;

public class AdaptadorPregunta extends BaseAdapter {
    private Context mContexto;

    private TextView textViewPregunta, textViewTitulo;
    private List<Pregunta> mPregunta;

    public AdaptadorPregunta(Context mContexto, List<Pregunta> pregunta){
        this.mContexto = mContexto;
        this.mPregunta = pregunta;
    }
    @Override
    public int getCount() {
        return this.mPregunta.size();
    }

    @Override
    public Object getItem(int i) {
        return this.mPregunta.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view = LayoutInflater.from(this.mContexto).inflate(R.layout.adaptador_pregunta,null );
        textViewPregunta = (TextView) view.findViewById(R.id.texto_pregunta);
        textViewPregunta.setText(this.mPregunta.get(i).getPregunta());
        textViewTitulo = (TextView) view.findViewById(R.id.titulo_pregunta);
        textViewTitulo.setText(this.mPregunta.get(i).getTitulo());

        return  view;
    }
}
