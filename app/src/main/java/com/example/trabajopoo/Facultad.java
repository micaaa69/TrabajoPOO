package com.example.trabajopoo;

public class Facultad {
    private int IdFacultad;
    private String sigla, nombreFacultad;

    public Facultad(int idFacultad, String sigla, String nombreFacultad) {
        IdFacultad = idFacultad;
        this.sigla = sigla;
        this.nombreFacultad = nombreFacultad;
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
}
