package Pacientes;/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Norman
 */
public final class Beneficiario extends Paciente {

    private int registroCotizante;
    private String tipoRelacion;
    private double salarioCotizante;    //va a ser necesario para ser mas eficiente, toca pensar si luego pasarlo a una
    //relacion, por si al cotizante le varia el salario
    private String tipoIdentificacion;

    public Beneficiario(){

    }


    public Beneficiario(int registroCotizante, String tipoRelacion, double salarioCotizante, String tipoIdentificacion){

        //todo manejo de excepciones e interfaz
        this.registroCotizante = registroCotizante;
        this.tipoRelacion = tipoRelacion;
        this.salarioCotizante = salarioCotizante;
        this.tipoIdentificacion = tipoIdentificacion;


    }

    public int getRegistroCotizante() {
        return registroCotizante;
    }

    public void setRegistroCotizante(int registroCotizante) {
        this.registroCotizante = registroCotizante;
    }

    public String getTipoRelacion() {
        return tipoRelacion;
    }

    public void setTipoRelacion(String tipoRelacion) {
        this.tipoRelacion = tipoRelacion;
    }

    public double getSalarioCotizante() {
        return salarioCotizante;
    }

    public void setSalarioCotizante(double salarioCotizante) {
        this.salarioCotizante = salarioCotizante;
    }

    public String getTipoIdentificacion() {
        return tipoIdentificacion;
    }

    public void setTipoIdentificacion(String tipoIdentificacion) {
        this.tipoIdentificacion = tipoIdentificacion;
    }

    @Override
    public String toString() {
        return "Paciente"+ super.toString() +" \nBeneficiario{" +
                "registroCotizante=" + registroCotizante +
                ", tipoRelacion='" + tipoRelacion + '\'' +
                ", salarioCotizante=" + salarioCotizante +
                ", tipoIdentificacion='" + tipoIdentificacion + '\'' +
                '}';
    }
}
