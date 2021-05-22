import Pacientes.*;
import java.util.ArrayList;
import java.util.*;
public class main {

    static int contador = 0;
    static ArrayList<Paciente> registroPacientes = new ArrayList<Paciente>();
    public static void main(String[] args) {


        // TODO code application logic here

        CorrerSistema();








    }

    public static void CorrerSistema(){

        //codigo de prueba funcionalidad basica consola
        boolean run = true;
        int option = 0;
        Scanner sc = new Scanner(System.in);



        //Variables para paciente

        int tipoPaciente = 0; // 0 = null, 1 = cotizante, 2 = beneficiario

        int registro;
        int numeroDocumentoIdentidad = 0;
        String nombre = "";

        //pacienteCotizante
        String celular = "";
        double salario = 0.0;

        //pacienteBeneficiario
        int registroCotizante = 0;
        String tipoRelacion = "";
        double salarioCotizante = 0.0;
        String tipoIdentificacion = "";


        while(run){
            System.out.print("\nSeleccione una opcion : \n1. Registrar paciente \n2. Buscar Paciente \n3. Salir del aplicativo \n\nOpcion: ");
            option = sc.nextInt();

            switch (option) {
                case 1:
                    
                    System.out.print("\nSeleccione tipo paciente a registrar: \n1. Paciente cotizante \n2. Paciente beneficiario \n\nOpcion: ");
                    tipoPaciente = sc.nextInt(); //pide el tipo de paciente para saber que constructor llamar
                    //empieza a pedir datos paciente
                    registro = contador;
                    System.out.print("\nIngrese n° Documento \n document: ");
                    numeroDocumentoIdentidad = Integer.parseInt(sc.nextLine()); //soluciona error que saltaba el nombre
                    System.out.print("\nIngrese Nombre completo paciente \n Nombre: ");
                    nombre = sc.nextLine();
                    
                    if(tipoPaciente == 1){
                        System.out.print("\nIngrese n° celular del paciente \n celular: ");
                        celular = sc.next();
                        
                        System.out.print("\nIngrese salario mensual del paciente \n salario : $");
                        salario = sc.nextDouble();
                        
                        
                        //genera obj paciente y lo agrega al array de registro
                        Cotizantes pacienteTmp = new Cotizantes(celular, salario, registro, numeroDocumentoIdentidad, nombre);
                        RegistrarPaciente(pacienteTmp);
                        
                    }else if(tipoPaciente == 2){
                        
                        System.out.print("\n Ingrese n° registro del cotizante \n registro: ");
                        registroCotizante = sc.nextInt();
                        
                        //todo verificar existencia cotizante, llamar metodo "ExistenciaCotizante()"
                        
                        System.out.print("\n Ingrese tipo relacion paciente \n relacion: ");
                        tipoRelacion = sc.next();
                        
                        System.out.print("\n Ingrese el salario del cotizante \n salario: ");
                        //todo quizas cambiar esto por un metodo y recuperar el salario al buscar el cotizante
                        salarioCotizante = sc.nextDouble();
                        
                        System.out.println("\n Ingrese tipo identificacion");
                        tipoIdentificacion = sc.next();
                        
                        //genera obj paciente y lo agrega al array de registro
                        Beneficiario pacienteTmp = new Beneficiario(registroCotizante, tipoRelacion, salarioCotizante, tipoIdentificacion);
                        RegistrarPaciente(pacienteTmp);
                        
                    }
                    
                    
                    
                    
                    
                    
                    
                    // RegistrarPaciente();
                    break;
            //MostrarRegistroPaciente();
                case 2:
                    break;
                case 3:
                    run = false;
                    break;
                default:
                    break;
            }









        }

    }




    public static boolean ExistenciaCotizante(ArrayList<Paciente> registroPacientes, int codigoCotizante){
        boolean existe = false;
        
              
        //todo Implementar recorridoal array y retornar verdadero si el cotizante existe
        
        return existe;
    }

    public static void RegistrarPaciente(Paciente paciente){
        contador++; //contador numero pacientes


        
    }

    public static String MostrarRegistroPaciente(ArrayList<Paciente> registroPacientes, int identificacionPaciente){
        String output = " ";
        
        //todo Implementar recorrido de array y concatenar el "toString" del paciente en la var output
        
        return output;
    }

    public static String MostrarRegistroPacientes(ArrayList<Paciente> registroPacientes){
        String output = " ";
        
        //todo Implementar recorrido de array y concatenar el "toString" de cada paciente en la var output
        
        return output;
    }
    
}
