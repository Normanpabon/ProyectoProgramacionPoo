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
        return "Servicios{" + "valor=" + valor + ", fecha=" + fecha + ", tipo=" + tipo + '}';
    }
    
    
    public void calcularValorServicio(String servicio){
        
        
            
        
    
    
    }
    public double calcularCopago(double salario){
        double returnValue = 0.0;
        
        
        return returnValue;
        
        
    
    
    }
    
    public double calcularCuotaMOderadora(double salario, int tipo){
        double returnValue = 0.0;
        
        
        
        if (tipo==1){
            
            //cuota= getSalario();
        
        
        }
    
    return returnValue;
    }
    
    
    
    
    
   
    
    
         
            
    
    
}
