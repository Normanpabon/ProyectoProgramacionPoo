package Pacientes;/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import TiposServicios.Servicios;

import java.util.ArrayList;

/**
 *
 * @author Norman
 * @author Juan
 */
public final class Cotizante extends Paciente {
    private String celular;
    private double salario;

    public Cotizante(){

    }

    public Cotizante(String celular, double salario, int numeroRegistro, int numeroDocumentoIdentidad, String nombre){

        super(numeroRegistro, numeroDocumentoIdentidad, nombre);

        this.celular = celular;
        this.salario = salario;


    }



    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public double getSalario() {
        return salario;
    }

    public void setSalario(double salario) {
        this.salario = salario;
    }


    //registrar un servicio
    @Override
    public void registrarServicio(Servicios servicio) {
        //todo crear sistema de registro y pasar como argumento constructor del servicio, el "1" para hacer referencia a cotizante

        //todo pedir los datos para crear el servicio aca
        super.registrarServicio(servicio);
    }

    @Override
    public String toString() {  //todo acomodar
        return  super.toString() +"\nTipo de paciente: Cotizante" +
                "\nNúmero de celular: " + celular +
                "\nSalario mensual: $" + salario +
                "\n" + getHistorialMedico();

    }
}
