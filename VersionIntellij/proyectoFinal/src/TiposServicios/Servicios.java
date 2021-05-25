/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Norman
 * @author Juan
 */
package TiposServicios;
import java.util.Date;

enum Servicio{
    ConsultaMedicaGeneral, ConsultaMedicaEspecialista, Cirugia, Hospitalizacion;
    // ConsultaMedicaGeneral == 1, ... Hospitalizacion == 4

}

public class Servicios {
    //Constantes, facilitan mejoras futuras
    private final double RANGO_SALARIAL_1 = 1817051;
    private final double RANGO_SALARIAL_2 = 4542631;
    private final double LIM_COPAGO_1 = 260.747;
    private final double LIM_COPAGO_2 = 1044805;
    private final double LIM_COPAGO_3 = 2089610;



    private Servicio servicio;
    private double sueldo;
    private double valor; // coste final del procedimiento
    private Date fecha; // Fecha de creacion, usa el tiempo actual
    private int tipo; // tipo 1 == Cotizante | 2 == Beneficiario (o buscar otra manera)

    public Servicios() {
        fecha = new Date();
    }

    public Servicios(int tipo, int servicio, double sueldo) {

        // todo verificar asignacion servicio desde el main

        //la var temporal "servicio" se pasa desde el metodo de registrar el servicio que se cree en el main, indica el tipo de servicio
        switch (servicio) {
            case 1:
                this.servicio = Servicio.ConsultaMedicaGeneral;
                break;
            case 2:
                this.servicio = Servicio.ConsultaMedicaEspecialista;
                break;
            case 3:
                this.servicio = Servicio.Cirugia;
                break;
            case 4:
                this.servicio = Servicio.Hospitalizacion;
                break;
            default:
                break;
        }

        //this.servicio = servicio;
        this.sueldo = sueldo;
        this.tipo = tipo;
        // la fecha se registra al crear el servicio (al llamar su constructor)

        fecha = new Date();


        //Se calcula directamente el valor a pagar
        CalcularValor(tipo, this.servicio, sueldo);
    }


    private void CalcularValor(int tipo, Servicio servicio, double sueldo){

        if(servicio == Servicio.Cirugia || servicio == Servicio.Hospitalizacion){

            if(tipo == 1){
                this.valor = 0;
            }else{
                this.valor = CalcularCopago(sueldo);
            }

        }else{

            this.valor = CalcularCuotaMOderadora(sueldo);

        }


    }


    private double CalcularCopago(double sueldo){
        double tmpValor = 0;
        if(sueldo <= RANGO_SALARIAL_1){
            tmpValor = sueldo*0.115;
            //aca se hace una comprobacion de que el copago no pase los "260,747" pero es redudante
            if(tmpValor > LIM_COPAGO_1){
                tmpValor = LIM_COPAGO_1;
            }
        }else if(sueldo > RANGO_SALARIAL_1 && sueldo <= RANGO_SALARIAL_2){
            tmpValor = sueldo*0.173;
            //aca se hace una comprobacion de que el copago no pase los "1,044,805" pero es redudante
            if(tmpValor > LIM_COPAGO_2){
                tmpValor = LIM_COPAGO_2;
            }
        }else{
            tmpValor = sueldo*0.23;
            if(tmpValor > LIM_COPAGO_3){
                tmpValor = LIM_COPAGO_3;
            }
        }


        return tmpValor;

    }
    
    
    private double CalcularCuotaMOderadora(double sueldo){
        double tmpValor = 0;
        if(sueldo <= RANGO_SALARIAL_1){
            tmpValor =  3500;

        }else if(sueldo > RANGO_SALARIAL_1 && sueldo <= RANGO_SALARIAL_2){
            tmpValor = 14000;
        }else{
            tmpValor = 36800;
        }
        
        

        return tmpValor;
    }




    //todo colocar toString de nuevo


    @Override
    public String toString() {
        return "\n--- Servicio registrado---\n" +
                "Fecha: " + fecha +
                "\nServicio: " + servicio +
                "\nValor a pagar: " + valor;
    }
}
