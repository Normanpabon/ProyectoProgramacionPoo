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
import Pacientes.*;

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
    
    public Servicios(Date fecha, double salario, int tipoPaciente, int servicio) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        
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

    

    
    
    // todo revisar metodos, metodos en desuso deben ser borrados
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


    
//quité lo que calcular valor ya que éste ya se hizo 


    


    public double calcularCopago(  int servicio, double salario ){
        double coPago = 0.0;
        
        
        if((servicio == 2 || servicio ==3)&& salario<=260.747 ){
            coPago= salario*0.115;
            this.valor=salario*0.115; 
        } else {
            if((servicio == 2 || servicio ==3)&& (260747<=salario)&&(salario<=1044805)){
                coPago= salario*0.173;
                this.valor= salario*0.173;
               
            } else{
                if((servicio == 2 || servicio ==3)&& (1044805<=salario)&&(salario<=2089610)){
                    coPago= salario*0.23;
                    this.valor= salario*0.23;
   
             } 
    
    
           }
            
        } 
        
        return coPago;
        
        
    
    }
    
    
    public double calcularCuotaMOderadora(double salario, int servicio){
        double cuotaModeradora = 0.0;
        
        
        
        if ((servicio==0 || servicio==1) && (salario <=1817051)){
          
            cuotaModeradora= 3500; 
            this.valor=3500; // hago esto por lo que mencioné en CalcularValorServicio()
        }
        else{
            if((servicio==0 || servicio==1) &&  ((1817051 <= salario)&& (salario<= 4542631))){
                cuotaModeradora= 14000; 
                this.valor=14000;
            }
            else{
                if((servicio==0|| servicio==1)&&  (4542631 < salario)){
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
