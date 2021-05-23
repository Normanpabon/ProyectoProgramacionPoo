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
import Pacientes.Cotizantes;
import java.util.Date;



public class Servicios {
    
    enum Servicio
    {
    
       ConsultaMedicaGeneral, ConsultaMedicaEspecialista, Cirugia, Hospitalizacion
    
    
    }
    
    private double valor;
    private Date fecha = new Date();
    private int tipo;

    public Servicios() {
    }

    public Servicios(double valor, int tipo) {
        this.valor = valor;
        this.tipo = tipo;
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

    @Override
    public String toString() {
        return " Datos del servicio:" + '\''+
                " valor=  " + valor + '\'' + 
                " fecha=  " + fecha + '\'' +
                " tipo=   " + tipo;
    }
    
    
    public void calcularValorServicio(String servicio){
        // creo que aquí hay redundancia, no sería mejor enel valor del servicio es diferente a la cuota y el copago? 
        //leí el enunciado y ni en la tabla sale un valor diferente a esos 
        
        
            
        
    
    
    }
    
    Cotizantes salario= new Cotizantes();

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
    
    
    
    
    
   
    
    
         
            
    
    
}
