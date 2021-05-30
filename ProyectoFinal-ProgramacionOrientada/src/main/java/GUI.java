// Non-gui libraries
import Pacientes.*;
import TiposServicios.*;
import java.io.*;
import java.util.ArrayList;
import java.util.*;

//GUI
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class GUI extends Container implements ActionListener {

    //gui objects
    private JButton button;
    public JPanel panelMain;
    private JLabel label;
    private JFrame frame;

    private int contador = 0;
    private ArrayList<Paciente> registroPacientes;
    private JButton botonPrueba;
    private JLabel labelPrueba;

    public GUI(){

        frame = new JFrame();
        button = new JButton("Prueba"); //boton de prueba
        label = new JLabel("Numero de usuarios registrados : 0");

        JPanel panel = new JPanel();
        panel.setBorder(BorderFactory.createEmptyBorder(30, 30, 10, 30));
        panel.setLayout(new GridLayout(0, 1));

        //se añaden los botones y label de pruebas
        panel.add(button);
        panel.add(label);

        frame.add(panel, BorderLayout.CENTER);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Administracion de pacientes");
        frame.pack();
        frame.setVisible(true);



        CorrerSistema();



    }



    public void CorrerSistema(){

        //Verifica existencia de datos anteriores y carga el archivo
        registroPacientes = RecuperarDatos();

        //codigo de prueba funcionalidad basica consola
        boolean run = true;
        int option;
        Scanner sc = new Scanner(System.in);



        //Variables para paciente

        int tipoPaciente; // 0 = null, 1 = cotizante, 2 = beneficiario
        int registro; // Registro interno del sistema
        int numeroDocumentoIdentidad;
        String nombre = "";

        //pacienteCotizante
        String celular;
        double salario;

        //pacienteBeneficiario
        int registroCotizante;
        String tipoRelacion;
        double salarioCotizante;
        String tipoIdentificacion;

        //para las excepciones
        boolean error= true;


        while(run){
            //actualiza el label que avisa el numero de pacientes registrados
            this.labelPrueba.setText("Contador pacientes registrados: "+Integer.toString(this.contador));

            System.out.print("\nSeleccione una opción: \n1. Registrar a un paciente. \n2. Buscar a un paciente. \n3. Registrar un servicio. \n4. Mostrar a los pacientes registrados. \n5. Salir del aplicativo. \n\nOpción: ");
            option = Integer.parseInt(sc.nextLine());

            switch (option) {
                case 1:

                    System.out.print("\nSeleccione el tipo de paciente a registrar: \n1. Paciente cotizante. \n2. Paciente beneficiario. \n\nOpción: ");
                    tipoPaciente = Integer.parseInt(sc.nextLine()); //pide el tipo de paciente para saber que constructor llamar

                    //empieza a pedir datos paciente
                    registro = contador;

                    System.out.print("\nIngrese el número de identificación del paciente a registrar. \nID: ");

                    numeroDocumentoIdentidad = Integer.parseInt(sc.nextLine()); //soluciona error que saltaba el nombre





                    //nombre=JOptionPane.showInputDialog("\nIngrese el nombre completo del paciente a registrar. Nombre: ").toUpperCase();
                    System.out.print("\nIngrese el nombre completo del paciente a registrar. \nNombre: ");
                    nombre = sc.nextLine();

                    if(tipoPaciente == 1){

                        System.out.print("\nIngrese el número de celular del paciente a registrar. \nCelular: ");
                        celular = sc.nextLine();

                        System.out.print("\nIngrese el salario mensual del paciente a registrar. \nSalario : $");
                        salario = Double.parseDouble(sc.nextLine());

                        //genera obj paciente y lo agrega al array de registro
                        Cotizante pacienteTmp = new Cotizante(celular, salario, registro, numeroDocumentoIdentidad, nombre);
                        RegistrarPaciente(pacienteTmp);

                    }else if(tipoPaciente == 2){

                        System.out.print("\nIngrese el número de registro del paciente cotizante. \nRegistro: ");
                        registroCotizante = Integer.parseInt(sc.nextLine());

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


    public  Paciente ExistenciaCotizante(ArrayList<Paciente> registroPacientes, int codigoCotizante){
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

    private int obtenerNumeroConsecutivo(){
        return this.contador;
    }


    private void RegistrarPaciente(Paciente paciente){
        this.contador++; //contador numero pacientes
        registroPacientes.add(paciente);



    }

    public String MostrarRegistroPaciente(ArrayList<Paciente> registroPacientes, int identificacionPaciente){
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


    public  String RegistrarServicio(int identificacionPaciente, ArrayList<Paciente> registroPacientes, int tipoServicio){
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

    public String MostrarRegistroPacientes(ArrayList<Paciente> regPacientes){
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

    public void SalvarDatos(ArrayList<Paciente> registroPacientes){
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

    public ArrayList<Paciente> RecuperarDatos(){
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


    @Override
    public void actionPerformed(ActionEvent e) {
        this.labelPrueba.setText("Contador pacientes registrados: "+Integer.toString(this.contador));
    }
}
