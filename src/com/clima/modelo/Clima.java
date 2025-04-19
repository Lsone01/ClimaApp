
package com.clima.modelo;

public class Clima {
    private String nombre;
    private double temperatura;
    private String descripcion;

    public Clima(String nombre, double temperatura, String descripcion) {
        this.nombre = nombre;
        this.temperatura = temperatura;
        this.descripcion = descripcion;
    }

    public String getNombre() {
        return nombre;
    }

    public double getTemperatura() {
        return temperatura;
    }

    public String getDescripcion() {
        return descripcion;
    }
}
