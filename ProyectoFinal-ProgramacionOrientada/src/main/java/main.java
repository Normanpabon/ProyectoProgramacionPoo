import Pacientes.*;
import TiposServicios.*;
import java.io.*;
import java.util.ArrayList;
import java.util.*;
import javax.swing.JOptionPane;

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
        int option;
        Scanner sc = new Scanner(System.in);



        //Variables para paciente

        int tipoPaciente = 0; // 0 = null, 1 = cotizante, 2 = beneficiario
        int registro=0; // Registro interno del sistema
        int numeroDocumentoIdentidad= 0;
        String nombre = "";

        //pacienteCotizante
        String celular;
        double salario= 0.0;

        //pacienteBeneficiario
        int registroCotizante=0;
        String tipoRelacion;
        double salarioCotizante;
        String tipoIdentificacion;

        //para las excepciones
        boolean error= true;


        while(run){
            System.out.print("\nSeleccione una opción: \n1. Registrar a un paciente. \n2. Buscar a un paciente. \n3. Registrar un servicio. \n4. Mostrar a los pacientes registrados. \n5. Salir del aplicativo. \n\nOpción: ");
            option = Integer.parseInt(sc.nextLine());

            switch (option) {
                case 1:
                    while(error){
                    try{
                        
                    System.out.print("\nSeleccione el tipo de paciente a registrar: \n1. Paciente cotizante. \n2. Paciente beneficiario. \n\nOpción: ");
                    tipoPaciente = Integer.parseInt(sc.nextLine()); //pide el tipo de paciente para saber que constructor llamar

                    //empieza a pedir datos paciente
                    registro = contador;

                    System.out.print("\nIngrese el número de identificación del paciente a registrar. \nID: ");
                    numeroDocumentoIdentidad = Integer.parseInt(sc.nextLine()); //soluciona e11rror que saltaba el nombre
                        error=false;
                        
                        
                    } catch(NumberFormatException e){
                        JOptionPane.showMessageDialog(null, "El numero regristrado debe ser un ENTERO\n"+
                                "Mensaje de excepción: "+ e.getMessage()+'\n'+
                                "toString: "+e.toString());
                      }
                    
                    }   System.out.println("tipo paciente= "+ tipoPaciente+'\n'+
                                           "N° documento: "+ numeroDocumentoIdentidad);
                    
                    
                    
                 
                    //nombre=JOptionPane.showInputDialog("\nIngrese el nombre completo del paciente a registrar. Nombre: ").toUpperCase();
                    System.out.print("\nIngrese el nombre completo del paciente a registrar. \nNombre: ");
                    nombre = sc.nextLine();

                    if(tipoPaciente == 1){

                        System.out.print("\nIngrese el número de celular del paciente a registrar. \nCelular: ");
                        celular = sc.nextLine();
                        
                        while(error){
                            
                            try{
                                System.out.print("\nIngrese el salario mensual del paciente a registrar. \nSalario : $");
                                salario = Double.parseDouble(sc.nextLine());
                                error=false; 

                            }catch(NumberFormatException e){
                                JOptionPane.showMessageDialog(null, "El salario debe ser un decimal (ejemplo: 200.000)\n"+
                                "Mensaje de excepción: "+ e.getMessage()+'\n'+
                                "toString: "+e.toString());
                                
                            }
                        } System.out.println("salario= "+salario);

                        //genera obj paciente y lo agrega al array de registro
                        Cotizante pacienteTmp = new Cotizante(celular, salario, registro, numeroDocumentoIdentidad, nombre);
                        RegistrarPaciente(pacienteTmp);

                    }else if(tipoPaciente == 2){
                        
                        while(error){
                            
                            try{

                        System.out.print("\nIngrese el número de registro del paciente cotizante. \nRegistro: ");
                        registroCotizante = Integer.parseInt(sc.nextLine());
                        error=false; 
                            }catch(NumberFormatException e){
                                JOptionPane.showMessageDialog(null, "El número de registro debe ser un entero(ejemplo: 1234)\n"+
                                "Mensaje de excepción: "+ e.getMessage()+'\n'+
                                "toString: "+e.toString());
                            
                            }
                        } System.out.print("N° de registro del cotizante=  "+ registroCotizante);

                        //verifica si el numero del cotizante esta correcto
                        Paciente tmpObjPaciente = ExistenciaCotizante(registroPacientes, registroCotizante);
                        if( tmpObjPaciente.getNombre() == null){
                            System.out.println("\n¡Error! El cotizante no existe. \n");
                            break;
                        }

                        System.out.print("\nIngrese el tipo de relación con el paciente. \nRelación: ");
                        tipoRelacion = sc.nextLine();

                        System.out.println("\nIngrese el tipo de identificación. \n(Cedula, Pasaporte...): ");
                        tipoIdentificacion = sc.nextLine();

                        //genera obj paciente y lo agrega al array de registro

                        Beneficiario pacienteTmp = new Beneficiario(tipoRelacion, tipoIdentificacion, registro, numeroDocumentoIdentidad, nombre, (Cotizante) tmpObjPaciente);
                        RegistrarPaciente(pacienteTmp);
                    }

                    break;

                case 2:

                    System.out.print("\nIngrese el número de identificación del paciente a buscar. \nIdentificación: ");
                    numeroDocumentoIdentidad = Integer.parseInt(sc.nextLine());
                    System.out.println("\n"+MostrarRegistroPaciente(registroPacientes, numeroDocumentoIdentidad)+"\n");
                    break;

                case 3:

                    System.out.print("\nIngrese el número de identificación del paciente. \nID: ");
                    numeroDocumentoIdentidad = Integer.parseInt(sc.nextLine());

                    if(ExistePaciente(registroPacientes, numeroDocumentoIdentidad)){

                        System.out.print("\nIngrese el tipo de servicio a registrar: \n1. Consulta General. \n2. Consulta Especialista. \n3. Cirugía. \n4. Hospitalización. \nOpcion: ");
                        int servicio = Integer.parseInt(sc.nextLine());
                        //todo al implementar la gui, ese system.out.println debe ser removido
                        System.out.println(RegistrarServicio(numeroDocumentoIdentidad, registroPacientes, servicio));
                        //RegistrarServicio(numeroDocumentoIdentidad, registroPacientes, tipoServicio);

                    }else{
                        System.out.println("\nEl número de identificación ingresado no está registrado.");
                        break;
                    }

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
                output = "El paciente no existe.";
            }
        }
        
        return output;
    }


    public static String RegistrarServicio(int identificacionPaciente, ArrayList<Paciente> registroPacientes, int tipoServicio){
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
                tmpStatus = tmpServicio.toString();


                tmpExiste = true;
                break;
            }
        }
        if(!tmpExiste){

            tmpStatus += "\nEl paciente no existe.";
        }

        return tmpStatus;
    }

    public static String MostrarRegistroPacientes(ArrayList<Paciente> regPacientes){
        String tmpOutput = " ";
        Collections.sort(regPacientes);


        if(regPacientes.size() == 0){
            tmpOutput = "No se encuentran pacientes registrados.";
            return tmpOutput;
        }

        for(Paciente paciente : regPacientes){

            tmpOutput += "\n ------------------------------ \n" + paciente.toString(); //separador para pacientes
        }

        
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
            System.out.println(ioe);

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
            System.out.print(ioe);
            return registroTmp;

        }catch (ClassNotFoundException c){
            System.out.println(c);

        }


        return registroTmp;
    }
    
}


