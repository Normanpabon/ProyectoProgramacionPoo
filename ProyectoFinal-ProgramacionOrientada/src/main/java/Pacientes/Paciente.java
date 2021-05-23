/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Norman
 */
package Pacientes;
import java.util.ArrayList;
import TiposServicios.Servicios;

public abstract class Paciente {

    private int numeroRegistro;
    private int numeroDocumentoIdentidad;
    private String nombre;
    private ArrayList<Servicios> historialMedico;

    public Paciente(){}

    public Paciente(int numeroRegistro, int numeroDocumentoIdentidad, String nombre){



        this.numeroRegistro = numeroRegistro;
        this.numeroDocumentoIdentidad = numeroDocumentoIdentidad;
        this.nombre = nombre;
        historialMedico = new ArrayList<Servicios>();

    }

    public int getNumeroRegistro() {
        return numeroRegistro;
    }

    public void setNumeroRegistro(int numeroRegistro) {
        this.numeroRegistro = numeroRegistro;
    }

    public int getNumeroDocumentoIdentidad() {
        return numeroDocumentoIdentidad;
    }

    public void setNumeroDocumentoIdentidad(int numeroDocumentoIdentidad) {
        this.numeroDocumentoIdentidad = numeroDocumentoIdentidad;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public ArrayList<Servicios> getHistorialMedico() {
        return historialMedico;
    }


    public void registrarServicio(Servicios servicio) {
        this.historialMedico.add(servicio);
    }




    @Override
    public String toString() {
        return "Datos del paciente" +'\''+
                "numeroRegistro=" + numeroRegistro + '\''+
                ", numeroDocumentoIdentidad='" + numeroDocumentoIdentidad + '\'' +
                ", nombre='" + nombre + '\'' +
                ", historialMedico=" + historialMedico +
                ;
    }
}
