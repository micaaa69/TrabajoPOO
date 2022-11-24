package com.example.trabajopoo;

public class Tipo {
    private int idTipo;
    private String nombreTipo;
    private boolean isChecked;
    public Tipo(int id_tipo, String nombreTipo, boolean isChecked) {
        this.idTipo = id_tipo;
        this.nombreTipo = nombreTipo;
        this.isChecked = isChecked;
    }

    @Override
    public String toString() {
        return "Tipo{" +
                "id_tipo=" + idTipo +
                ", nombreTipo='" + nombreTipo + '\'' +
                '}';
    }

    public int getId_tipo() {
        return idTipo;
    }

    public void setId_tipo(int id_tipo) {
        this.idTipo = id_tipo;
    }

    public String getnombreTipo() {
        return nombreTipo;
    }

    public void setnombreTipo(String nombreTipo) {
        this.nombreTipo = nombreTipo;
    }

    public void setChecked(boolean isChecked){
        this.isChecked = isChecked;
    }
    public boolean getChecked(){
        return this.isChecked;
    }
}

