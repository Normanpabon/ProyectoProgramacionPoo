package Pacientes;/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import TiposServicios.Servicios;

/**
 *
 * @author Norman
 * @author Juan
 */
public final class Beneficiario extends Paciente {


    private String tipoRelacion;
    private Cotizante cotizante;
    private String tipoIdentificacion;

    public Beneficiario(){

    }


    public Beneficiario(String tipoRelacion, String tipoIdentificacion, int numeroRegistro, int numeroDocumentoIdentidad, String nombre, Cotizante cotizante){

        super(numeroRegistro, numeroDocumentoIdentidad, nombre);
        this.cotizante = cotizante;
        this.tipoRelacion = tipoRelacion;

        this.tipoIdentificacion = tipoIdentificacion;


    }





    public String getTipoRelacion() {
        return tipoRelacion;
    }

    public void setTipoRelacion(String tipoRelacion) {
        this.tipoRelacion = tipoRelacion;
    }

    public double getSalarioCotizante() {
        return cotizante.getSalario();
    }


    public String getTipoIdentificacion() {
        return tipoIdentificacion;
    }

    public void setTipoIdentificacion(String tipoIdentificacion) {
        this.tipoIdentificacion = tipoIdentificacion;
    }



    @Override
    public void registrarServicio(Servicios servicio){

        super.registrarServicio(servicio);
    }

    @Override
    public int compareTo(Object o) {
        int compareIdentidad = ((Paciente) o).getNumeroDocumentoIdentidad();
        return super.getNumeroDocumentoIdentidad() - compareIdentidad;
    }

    @Override
    public String toString() {
        return  super.toString() +"\nTipo de paciente: Beneficiario" +
                "\nTipo de relación: " + tipoRelacion +
                "\nTipo de identificación: " + tipoIdentificacion +
                "\n" + getHistorialMedico();

    }


}
