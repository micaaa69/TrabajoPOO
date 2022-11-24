package com.example.trabajopoo;

public class Alternativa {
    int idCuestionario, idPregunta;
    String etiqueta;

    public Alternativa(int idCuestionario, int idPregunta, String etiqueta) {
        this.idCuestionario = idCuestionario;
        this.idPregunta = idPregunta;
        this.etiqueta = etiqueta;
    }

    public int getIdCuestionario() {
        return idCuestionario;
    }

    public void setIdCuestionario(int idCuestionario) {
        this.idCuestionario = idCuestionario;
    }

    public int getIdPregunta() {
        return idPregunta;
    }

    public void setIdPregunta(int idPregunta) {
        this.idPregunta = idPregunta;
    }

    public String getEtiqueta() {
        return etiqueta;
    }

    public void setEtiqueta(String etiqueta) {
        this.etiqueta = etiqueta;
    }

    @Override
    public String toString() {
        return "Alternativa{" +
                "idCuestionario=" + idCuestionario +
                ", idPregunta=" + idPregunta +
                ", etiqueta='" + etiqueta + '\'' +
                '}';
    }
}
