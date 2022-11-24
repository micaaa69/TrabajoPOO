package com.example.trabajopoo;

public class Alternativa {
    private int idCuestionario, idPregunta;
    private String etiqueta;
    private boolean isChecked;
    public Alternativa(){}
    public Alternativa(int idCuestionario, int idPregunta, String etiqueta){
        this.idCuestionario = idCuestionario;
        this.idPregunta = idPregunta;
        this.etiqueta = etiqueta;
    }
    public Alternativa(int idCuestionario, int idPregunta, String etiqueta, boolean isChecked) {
        this.idCuestionario = idCuestionario;
        this.idPregunta = idPregunta;
        this.etiqueta = etiqueta;
        this.isChecked = isChecked;
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

    public void setChecked(boolean checked) {
        isChecked = checked;
    }

    public boolean isChecked() {
        return isChecked;
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
