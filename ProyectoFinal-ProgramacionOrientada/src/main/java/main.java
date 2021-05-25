import Pacientes.*;
import TiposServicios.*;

import java.io.*;
import java.util.ArrayList;
import java.util.*;
<<<<<<< HEAD
import javax.swing.JOptionPane;
=======

// librerias para persistencia


>>>>>>> be8f585fe10544bc8062f68cd702ded0235bd0c5
public class main {

    static int contador = 0;
    static ArrayList<Paciente> registroPacientes;
    public static void main(String[] args) {


        CorrerSistema();



    }



    public static void CorrerSistema(){

        //Verifica existencia de datos anteriores y carga el archivo
        registroPacientes = RecuperarDatos(); //todo revisar, esta fallando

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
        
        //servicio 
        Date fecha= new Date(); 
        int servicio= 0; 

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

                    System.out.print("\n Ingrese el numero documento identidad paciente \nId: ");
                    numeroDocumentoIdentidad = Integer.parseInt(sc.nextLine());
<<<<<<< HEAD
                    
                    System.out.print("\n Ingrese la fecha \nfecha: ");
                    //fecha = fecha.setDate(sc.nextLine());    aquí no sabría cómo poner eso  
                    
                    
                    System.out.print("\nSeleccione tipo de servicio: \n1. Consulta médica general \n2. Consulta médica especialista \n3. Cirugía \n4. Hospitalización:");
                    servicio= Integer.parseInt(sc.nextLine());
                    

                    Servicios objServicioTmp= new Servicios(fecha, salarioCotizante, tipoPaciente, servicio); 
                    //Servicios objServiciosTmp; //objeto tmp de servicios, se debe entregar como parametro
                    
                    
=======

                    if(ExistePaciente(registroPacientes, numeroDocumentoIdentidad)){

                        System.out.print("\nIngrese el tipo de servicio \n1. Consulta General \n2. Consulta Especialista \n3. Cirugia \n4. Hospitalizacion \nOpcion: ");
                        int servicio = Integer.parseInt(sc.nextLine());
                        //todo al implementar la gui, ese system.out.println debe ser removido
                        System.out.println(RegistrarServicio(numeroDocumentoIdentidad, registroPacientes, servicio));
                        //RegistrarServicio(numeroDocumentoIdentidad, registroPacientes, tipoServicio);

                    }else{
                        System.out.println("\nEl documento ingresado no esta registrado");
                        break;
                    }
>>>>>>> be8f585fe10544bc8062f68cd702ded0235bd0c5

                    break;

                case 4:
                    //todo al implementar la gui, ese system.out.println debe ser removido
                    System.out.println(MostrarRegistroPacientes(registroPacientes));
                    break;
                case 5:
                    run = false;
                    //guarda los datos al pedir cerrar el programa
                    SalvarDatos(registroPacientes);
                    break;
                default:
                    break;
            }










        }

    }

    public static boolean ExistePaciente(ArrayList<Paciente> registroPacientes, int identificacionPaciente){
        boolean tmpVar = false;

        for(Paciente paciente : registroPacientes){

            if(paciente.getNumeroDocumentoIdentidad() == identificacionPaciente){
                tmpVar = true;
            }
        }


        return tmpVar;
    }


    public static Paciente ExistenciaCotizante(ArrayList<Paciente> registroPacientes, int codigoCotizante){
        Paciente tmpObj = new Cotizante();
        for(Paciente paciente : registroPacientes) {
            if (paciente.getNumeroRegistro() == codigoCotizante && paciente instanceof Cotizante) {
                tmpObj = paciente;
                break;
            }else{
                tmpObj = null;
            }
        }
        return tmpObj;
    }


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


<<<<<<< HEAD
    public static String RegistrarServicio(int identificacionPaciente, ArrayList<Paciente> registroPacientes, servicio , objServicioTmp){
=======
    public static String RegistrarServicio(int identificacionPaciente, ArrayList<Paciente> registroPacientes, int tipoServicio){
>>>>>>> be8f585fe10544bc8062f68cd702ded0235bd0c5
        String tmpStatus = "\n";
        Servicios tmpServicio;
        boolean tmpExiste = false;
        int tmpPosicion;

        for(Paciente paciente : registroPacientes){
            if(paciente.getNumeroDocumentoIdentidad() == identificacionPaciente){
                if(paciente instanceof Cotizante){
                    tmpServicio = new Servicios(1, tipoServicio, ((Cotizante) paciente).getSalario());
                }else{
                    tmpServicio = new Servicios(2, tipoServicio, ((Beneficiario) paciente).getSalarioCotizante());
                }
                paciente.registrarServicio(tmpServicio);
                // todo borrar esto e invocar metodo "toString" del servicio, el metodo "paciente.obtenerUltimoServicio()" es deundante
                tmpStatus = paciente.obtenerUltimoServicio();


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

        if(registroPacientes.size() == 0){
            tmpOutput = " No se encuentran pacientes registrados";
            return tmpOutput;
        }

        for(Paciente paciente : registroPacientes){

            tmpOutput += "\n --------- \n" + paciente.toString();
        }
        //todo Implementar el metodo para sortear los pacientes en orden descendente a la hora de mostrarlos (segun su numero de identificacion)
        
        return tmpOutput;
    }

    public static void SalvarDatos(ArrayList<Paciente> registroPacientes){
        String fName = "Data";
        try {
            FileOutputStream file = new FileOutputStream(fName);
            ObjectOutputStream oos = new ObjectOutputStream(file);
            oos.writeObject(registroPacientes);
            oos.close();
            file.close();
        }catch (IOException ioe){

        }

    }

    public static ArrayList<Paciente> RecuperarDatos(){
        //todo revisar y buscar el error
        String fname = "Data";
        ArrayList<Paciente> registroTmp = new ArrayList<>();

        try {
            FileInputStream file = new FileInputStream(fname);
            ObjectInputStream ois = new ObjectInputStream(file);
            //quizas el error se encuentra aca
            registroTmp = (ArrayList) ois.readObject();

            ois.close();
            file.close();

        }catch (IOException ioe){
            return registroTmp;
        }catch (ClassNotFoundException c){

        }


        return registroTmp;
    }
    
}


