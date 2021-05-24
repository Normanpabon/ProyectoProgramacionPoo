import Pacientes.*;
import TiposServicios.*;

import java.util.ArrayList;
import java.util.*;
public class main {

    /**
     * @param args the command line arguments
     */
    static int contador = 0;
    static ArrayList<Paciente> registroPacientes = new ArrayList<Paciente>();
    public static void main(String[] args) {


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

        //para las excepciones
        boolean error= true;


        while(run){
            System.out.print("\nSeleccione una opcion : \n1. Registrar paciente \n2. Buscar Paciente \n3. Registrar Servicio \n4. Mostrar pacientes \n5. Salir del aplicativo \n\nOpcion: ");
            option = Integer.parseInt(sc.nextLine());

            switch (option) {
                case 1:

                    System.out.print("\nSeleccione tipo paciente a registrar: \n1. Paciente cotizante \n2. Paciente beneficiario \n\nOpcion: ");
                    tipoPaciente = Integer.parseInt(sc.nextLine()); //pide el tipo de paciente para saber que constructor llamar

                    //empieza a pedir datos paciente
                    registro = contador;

                    System.out.print("\n Ingrese el numero documento identidad paciente \nId: ");

                    numeroDocumentoIdentidad = Integer.parseInt(sc.nextLine()); //soluciona error que saltaba el nombre

                            



                    //nombre=JOptionPane.showInputDialog("\nIngrese Nombre completo paciente \n Nombre: ").toUpperCase();
                    System.out.print("\nIngrese Nombre completo paciente \n Nombre: ");
                    nombre = sc.nextLine();

                    if(tipoPaciente == 1){

                        System.out.print("\nIngrese n° celular del paciente \n celular: ");
                        celular = sc.nextLine();

                        System.out.print("\nIngrese salario mensual del paciente \n salario : $");
                        salario = Double.parseDouble(sc.nextLine());

                        //genera obj paciente y lo agrega al array de registro
                        Cotizante pacienteTmp = new Cotizante(celular, salario, registro, numeroDocumentoIdentidad, nombre);
                        RegistrarPaciente(pacienteTmp);

                    }else if(tipoPaciente == 2){

                        System.out.print("\n Ingrese n° registro del cotizante \n registro: ");
                        registroCotizante = Integer.parseInt(sc.nextLine());

                        //verifica si el numero del cotizante esta correcto
                        Paciente tmpObjPaciente = ExistenciaCotizante(registroPacientes, registroCotizante);
                        if( tmpObjPaciente == null){
                            System.out.println("\nError el cotizante no existe !\n");
                            break;
                        }

                        System.out.print("\n Ingrese tipo relacion paciente \n relacion: ");
                        tipoRelacion = sc.nextLine();

                        System.out.println("\n Ingrese tipo identificacion \n (Cedula, Passaporte...): ");
                        tipoIdentificacion = sc.nextLine();

                        //genera obj paciente y lo agrega al array de registro

                        Beneficiario pacienteTmp = new Beneficiario(tipoRelacion, tipoIdentificacion, registro, numeroDocumentoIdentidad, nombre, (Cotizante) tmpObjPaciente);
                        RegistrarPaciente(pacienteTmp);
                    }

                    break;

                case 2:

                    System.out.print("\nIngrese el numero de identificacion del paciente a buscar \nIdentificacion: ");
                    numeroDocumentoIdentidad = Integer.parseInt(sc.nextLine());
                    System.out.println("\n"+MostrarRegistroPaciente(registroPacientes, numeroDocumentoIdentidad)+"\n");
                    break;

                case 3:

                    //la existencia del paciente se verifica luego de la creacion del servicio
                    System.out.print("\n Ingrese el numero documento identidad paciente \nId: ");
                    numeroDocumentoIdentidad = Integer.parseInt(sc.nextLine());

                    //pedir los datos del servicio y luego invocarlo
                    //Servicios objServiciosTmp; //objeto tmp de servicios, se debe entregar como parametro

                    //RegistrarServicio(numeroDocumentoIdentidad, registroPacientes, objServiciosTmp);
                    break;

                case 4:
                    MostrarRegistroPacientes(registroPacientes);
                    break;
                case 5:
                    run = false;
                    break;
                default:
                    break;
            }










        }

    }


    public static Paciente ExistenciaCotizante(ArrayList<Paciente> registroPacientes, int codigoCotizante){
        Paciente tmpObj = new Cotizante();
        for(Paciente paciente : registroPacientes) {
            if (paciente.getNumeroRegistro() == codigoCotizante) {
                tmpObj = paciente;
                break;
            }else{
                tmpObj = null;
            }
        }
        return tmpObj;
    }

    /*
    public static boolean ExistenciaCotizante(ArrayList<Paciente> registroPacientes, int codigoCotizante){
        boolean existe = false;

        for(Paciente paciente : registroPacientes){
            if(paciente.getNumeroRegistro() == codigoCotizante){
                existe = true;
                break;
            }
        }

        return existe;
    }

     */

    public static void RegistrarPaciente(Paciente paciente){
        contador++; //contador numero pacientes
        registroPacientes.add(paciente);


        
    }

    public static String MostrarRegistroPaciente(ArrayList<Paciente> registroPacientes, int identificacionPaciente){
        String output = " ";
        
        //todo Implementar recorrido de array y concatenar el "toString" del paciente en la var output

        for(Paciente paciente : registroPacientes){
            if(paciente.getNumeroDocumentoIdentidad() == identificacionPaciente){
                output = paciente.toString();
                break;
            }else{
                output = "El paciente no existe";
            }
        }
        
        return output;
    }


    public static String RegistrarServicio(int identificacionPaciente, ArrayList<Paciente> registroPacientes, Servicios servicio){
        String tmpStatus = "\n";
        boolean tmpExiste = false;
        int tmpPosicion;

        for(Paciente paciente : registroPacientes){
            if(paciente.getNumeroDocumentoIdentidad() == identificacionPaciente){
                paciente.registrarServicio(servicio);
                tmpStatus += paciente.obtenerUltimoServicio();
                //todo notificar al usuario el valor a pagar y el registro exitoso del servicio

                tmpExiste = true;
                break;
            }
        }
        if(!tmpExiste){
            //todo notificar al usuario que el paciente no existe
            tmpStatus += "\nEl paciente no existe";
        }

        return tmpStatus;
    }

    public static String MostrarRegistroPacientes(ArrayList<Paciente> registroPacientes){
        String tmpOutput = " ";

        for(Paciente paciente : registroPacientes){

            tmpOutput += "\n --------- \n" + paciente.toString();
        }
        //todo Implementar recorrido de array y concatenar el "toString" de cada paciente en la var output
        
        return tmpOutput;
    }
    
}


