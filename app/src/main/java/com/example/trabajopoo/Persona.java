package com.example.trabajopoo;

public class Persona {
    private int idFacultad, idTipo;
    private boolean sexo;
    private String fechaNac;

    public Persona(int idFacultad, int idTipo, boolean sexo, String fechaNac) {

        this.idFacultad = idFacultad;
        this.idTipo = idTipo;
        this.sexo = sexo;
        this.fechaNac = fechaNac;
    }

    @Override
    public String toString() {
        return "Persona{" +
                "idFacultad=" + idFacultad +
                ", idTipo=" + idTipo +
                ", sexo=" + sexo +
                ", fechaNac='" + fechaNac + '\'' +
                '}';
    }

    public int getIdFacultad() {
        return idFacultad;
    }

    public void setIdFacultad(int idFacultad) {
        this.idFacultad = idFacultad;
    }

    public int getIdTipo() {
        return idTipo;
    }

    public void setIdTipo(int idTipo) {
        this.idTipo = idTipo;
    }

    public boolean isSexo() {
        return sexo;
    }

    public void setSexo(boolean sexo) {
        this.sexo = sexo;
    }

    public String getFechaNac() {
        return fechaNac;
    }

    public void setFechaNac(String fechaNac) {
        this.fechaNac = fechaNac;
    }
}
