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
 */
public final class Cotizantes extends Paciente{
    private String celular;
    private double salario;

    public Cotizantes(){

    }

    public Cotizantes(String celular, double salario, int numeroRegistro, int numeroDocumentoIdentidad, String nombre){

        super(numeroRegistro, numeroDocumentoIdentidad, nombre);

        this.celular = celular;
        this.salario = salario;


    }

    //registrar un servicio
    public void registrarServicio(Servicios objServicios){

        super.registrarServicio(objServicios);

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

    @Override
    public String toString() {  //todo acomodar
        return "Paciente"+ super.toString() +" \nCotizantes{" +
                "celular='" + celular + '\'' +
                ", salario=" + salario +
                '}';
    }
}
