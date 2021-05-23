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
    // ConsultaMedicaGeneral == 0, ... Hospitalizacion == 3

}

public class Servicios {
    

    private Servicio servicio;
    private double valor; // coste final del procedimiento
    private Date fecha; // Fecha de creacion, usa el tiempo actual
    private int tipo; // tipo 1 == Cotizante | 2 == Beneficiario (o buscar otra manera)

    public Servicios() {
        fecha = new Date();
    }

    public Servicios(int tipo, int servicio) {

        // todo verificar asignacion servicio desde el main

        //la var temporal "servicio" se pasa desde el metodo de registrar el servicio que se cree en el main, indica el tipo de servicio
        switch (servicio) {
            case 0:
                this.servicio = Servicio.ConsultaMedicaGeneral;
                break;
            case 1:
                this.servicio = Servicio.ConsultaMedicaEspecialista;
                break;
            case 2:
                this.servicio = Servicio.Cirugia;
                break;
            default:
                this.servicio = Servicio.Hospitalizacion;
                break;
        }

        //this.servicio = servicio;
        this.tipo = tipo;


        // la fecha se registra al crear el servicio (al llamar su constructor)

        fecha = new Date();
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }


    
    public void calcularValorServicio(String servicio){
        // creo que aquí hay redundancia, no sería mejor enel valor del servicio es diferente a la cuota y el copago?
        //leí el enunciado y ni en la tabla sale un valor diferente a esos






    }


    public double calcularCopago( int tipo ){
        double coPago = 0.0;
        
        if((tipo==3 || tipo==4)){
            //coPago= ;   si sabes, explicame esto....cómo que porcentajes de qué, no sabría cómo sacar el copago 
        
        
        
        }
        //norman, aquí no tiene sentido que esté salario porque no es un atributo de beneficiarios, y como
        //el copago solo es para ellos, pues no iría, solo  sería mandarle el tipo de servicio, me refiero a cambiarlo en el uml
        
        
        return coPago;
        
        
    
    
    }
    
    
    
    public double calcularCuotaMOderadora(double salario, int tipo){
        double cuotaModeradora = 0.0;
        
        
        
        if ((tipo==1 || tipo==2) && (salario <=1817051)){
          
            cuotaModeradora= 3500; 
            this.valor=3500; // hago esto por lo que mencioné en CalcularValorServicio()
        }
        else{
            if((tipo==1 || tipo==2) &&  ((1817051 <= salario)&& (salario<= 4542631))){
                cuotaModeradora= 14000; 
                this.valor=14000;
            }
            else{
                if((tipo==1 || tipo==2)&&  (4542631 < salario)){
                    cuotaModeradora= 36000;
                    this.valor=36000; 
                }
     
            }
        
        
        
        }
    
    return cuotaModeradora;
    }


    @Override
    public String toString() {
        return " Datos del servicio:" + '\''+
                " valor=  " + valor + '\'' +
                " fecha=  " + fecha + '\'' +
                " tipo=   " + tipo;
    }

}
