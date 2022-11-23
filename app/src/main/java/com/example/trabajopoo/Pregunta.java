package com.example.trabajopoo;

public class Pregunta {
    private String pregunta, titulo;
    public Pregunta(){}
    public Pregunta(String pregunta, String titulo) {
        this.pregunta = pregunta;
        this.titulo = titulo;
    }

    @Override
    public String toString() {
        return "Pregunta{" +
                "pregunta='" + pregunta + '\'' +
                ", titulo='" + titulo + '\'' +
                '}';
    }

    public String getPregunta() {
        return pregunta;
    }

    public void setPregunta(String pregunta) {
        this.pregunta = pregunta;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }
}
