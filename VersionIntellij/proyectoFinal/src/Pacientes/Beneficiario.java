package Pacientes;/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import TiposServicios.Servicios;

/**
 *
 * @author Norman
 */
public final class Beneficiario extends Paciente {


    private String tipoRelacion;
    private Cotizante cotizante; // crear objeto y relacionarlo
    private String tipoIdentificacion;

    public Beneficiario(){

    }


    public Beneficiario(String tipoRelacion, String tipoIdentificacion, int numeroRegistro, int numeroDocumentoIdentidad, String nombre, Cotizante cotizante){

        super(numeroRegistro, numeroDocumentoIdentidad, nombre);
        //todo manejo de excepciones e interfaz
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
        //todo crear sistema de registro y pasar como argumento constructor del servicio, el "2" para hacer referencia a beneficiario



        super.registrarServicio(servicio);
    }

    @Override
    public String toString() {
        return  super.toString() +" \nBeneficiario{" +
                ", tipoRelacion='" + tipoRelacion + '\'' +
                ", tipoIdentificacion='" + tipoIdentificacion + '\'' +
                '}';
    }
}
