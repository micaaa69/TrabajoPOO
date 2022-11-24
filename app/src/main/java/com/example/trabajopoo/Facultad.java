package com.example.trabajopoo;

public class Facultad {
    private int IdFacultad;
    private String sigla, nombreFacultad;
    private boolean isChecked;
    public Facultad(int idFacultad, String sigla, String nombreFacultad, boolean isChecked) {
        IdFacultad = idFacultad;
        this.sigla = sigla;
        this.nombreFacultad = nombreFacultad;
        this.isChecked = isChecked;
    }

    @Override
    public String toString() {
        return "Facultad{" +
                "IdFacultad=" + IdFacultad +
                ", sigla='" + sigla + '\'' +
                ", nombreFacultad='" + nombreFacultad + '\'' +
                '}';
    }

    public int getIdFacultad() {
        return IdFacultad;
    }

    public void setIdFacultad(int idFacultad) {
        IdFacultad = idFacultad;
    }

    public String getSigla() {
        return sigla;
    }

    public void setSigla(String sigla) {
        this.sigla = sigla;
    }

    public String getnombreFacultad() {
        return nombreFacultad;
    }

    public void setnombreFacultad(String nombreFacultad) {
        this.nombreFacultad = nombreFacultad;
    }

    public boolean isChecked() {
        return isChecked;
    }

    public void setChecked(boolean checked) {
        isChecked = checked;
    }
}
