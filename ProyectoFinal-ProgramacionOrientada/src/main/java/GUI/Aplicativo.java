package GUI;

import Pacientes.*;
import java.util.ArrayList;
import TiposServicios.*;
import java.io.*;
import java.util.*;
import javax.swing.*;


/**
 *
 * @author juanb
 * @author Norman
 */
public class Aplicativo extends javax.swing.JFrame {

    private int contador = 0;
    private ArrayList<Paciente> registroPacientes;

    //Para la opción de tipo de pacientes al presionar el botón de Registrar Paciente
    private String[] tiposPaciente = {

            "Paciente cotizante",
            "Paciente beneficiario"
    };

    //Para la opción de tipo de relación del paciente beneficiario al presionar el botón de registrar paciente
    private String[] tiposRelacion = {

            "Padre",
            "Hijo",
            "Cónyuge"

    };

    //Para las opciones de tipos de servicios
    private String[] tiposServicio = {

            "Consulta Médica General",
            "Consulta Médica Especialista",
            "Cirugía",
            "Hospitalización"

    };

    // Opciones para el tipo de identificacion
    private String[] tiposIdentificacion = {

            "Cedula",
            "Tarjeta de identidad",
            "Registro de nacimiento"
    };

    /**
     * Creates new form Aplicativo
     */
    public Aplicativo() {
        registroPacientes = RecuperarDatos();
        initComponents();

    }

    // metodos parte logica

    private int ObtenerNumeroConsecutivo(){
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

    private ArrayList<Paciente> RecuperarDatos(){
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

    // fin metodos parte logica





    private void bBuscarPacienteActionPerformed(java.awt.event.ActionEvent evt) {
        boolean tmpError = true;
        int identificacionTemp = 0;
        // todo Joption pane que pida id de paciente

        while(tmpError){
            try{
                identificacionTemp = Integer.parseInt((JOptionPane.showInputDialog("Ingrese el ID del paciente:")));
                tmpError = false;
            }catch (NumberFormatException e){

            }
        }



        tAreaReporte.setText(MostrarRegistroPaciente(registroPacientes, identificacionTemp));

    }

    private void bRegistrarPacienteActionPerformed(java.awt.event.ActionEvent evt) {

        boolean cotizanteExiste = false;

        boolean errorExiste = true;

        // Variables Pacientes
        int numeroDocumentoIdentidad = 0;
        String nombre = "";
        int tmpContador;

        // PacienteCotizante
        String celular = "";
        double salario = 0.0;

        // PacienteBeneficiario
        int registroCotizante = 0;
        String tipoRelacion;
        String tipoIdentificacion;

        while(errorExiste){
            try{
                numeroDocumentoIdentidad = Integer.parseInt((JOptionPane.showInputDialog("Ingrese el número de identidad del paciente a registrar:")));
                errorExiste = false;
            }catch (NumberFormatException e){

            }
        }

        nombre = JOptionPane.showInputDialog("Ingrese el nombre completo del paciente a registrar:");


        String resp = (String) JOptionPane.showInputDialog(null, "Seleccione el tipo de paciente a registrar:", "Tipo de paciente", JOptionPane.DEFAULT_OPTION, null, tiposPaciente, tiposPaciente[0]);

        if(resp == "Paciente cotizante"){
            // codigo wikiti

            while(!errorExiste){ // v - f
                try{
                    salario = Double.parseDouble((JOptionPane.showInputDialog("Ingrese el salario del paciente a registrar:")));
                    errorExiste = true;
                }catch (NumberFormatException e){

                }
            }

            //todo crear manejo excepcion despues celular)
            celular = JOptionPane.showInputDialog("Ingrese el celular del paciente a registrar:");


            tmpContador = ObtenerNumeroConsecutivo();

            Cotizante pacienteTmp = new Cotizante(celular, salario, tmpContador, numeroDocumentoIdentidad, nombre );
            RegistrarPaciente(pacienteTmp);

        }else{ // beneficiario

            while(errorExiste){
                try{
                    registroCotizante = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el número de registro del paciente cotizante:"));
                    errorExiste = false;
                }catch (NumberFormatException e){

                }
            }

            Paciente tmpObjPaciente = ExistenciaCotizante(registroPacientes, registroCotizante);
            if(tmpObjPaciente.getNombre() == null){
                //todo manejo de excepciones
                String error = JOptionPane.showInputDialog(null, "El paciente cotizante no existe. Ingrese nuevamente el numero de registro del paciente:", "Error", JOptionPane.ERROR_MESSAGE);

            }else{
                cotizanteExiste = true;
            }

            if(cotizanteExiste){
                tipoIdentificacion = (String) JOptionPane.showInputDialog(null,"Ingrese el tipo de identificación del paciente a registrar:","Tipo de identificacion",JOptionPane.DEFAULT_OPTION,null,tiposIdentificacion, tiposIdentificacion[0]);
                tipoRelacion = (String) JOptionPane.showInputDialog(null, "Seleccione el tipo de relación del paciente a registrar:", "Tipo de relación", JOptionPane.DEFAULT_OPTION, null, tiposRelacion, tiposRelacion[0]);
                tmpContador = ObtenerNumeroConsecutivo();
                Beneficiario pacienteTmp = new Beneficiario(tipoRelacion, tipoIdentificacion, tmpContador, numeroDocumentoIdentidad, nombre, (Cotizante) tmpObjPaciente);
                RegistrarPaciente(pacienteTmp);

            }

        }

    }

    private void bMostrarPacientesRegistradosActionPerformed(java.awt.event.ActionEvent evt) {

        // todo verificar cualquier error

        tAreaReporte.setText(MostrarRegistroPacientes(registroPacientes));


    }

    private void bRegistrarServicioActionPerformed(java.awt.event.ActionEvent evt) {
        boolean tmpError = true;
        int tmpDocumento = 0;
        int tmpServicio = 0;

        //Opciones para escoger el tipo de servicio medico a registrarse

        while(tmpError){
            try{
                tmpDocumento = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el número de identificación del paciente:"));
                tmpError = false;
            }catch (NumberFormatException e){

            }
        }



        String rsp = (String) JOptionPane.showInputDialog(null, "Selecione el tipo de servicio a registrar:", "Tipo de servicio", JOptionPane.DEFAULT_OPTION, null, tiposServicio, tiposServicio[0]);

        switch (rsp) {

            case "Consulta Médica General":
                tmpServicio = 1;
                break;
            case "Consulta Médica Especialista":
                tmpServicio = 2;
                break;

            case "Cirugía":
                tmpServicio = 3;
                break;

            case "Hospitalización":
                tmpServicio = 4;
                break;

            default:
                break;
        }


        // numeroDocumentoIdentidad = JoptionPanel
        if(ExistePaciente(registroPacientes, tmpDocumento)){
            tAreaReporte.setText(RegistrarServicio(tmpDocumento, registroPacientes, tmpServicio));

        }else{
            //todo Mostrar error
            String error = JOptionPane.showInputDialog(null, "El número de identificación ingresado no está registrado.", "Error", JOptionPane.ERROR_MESSAGE);

        }



    }

    private void bSalirActionPerformed(java.awt.event.ActionEvent evt) {
        int resp = JOptionPane.showConfirmDialog(null, "¿Está seguro que desea salir del aplicativo?", "Alerta", JOptionPane.YES_NO_OPTION);
        switch (resp) {
            case 0:
                SalvarDatos(registroPacientes);
                System.exit(0);
                break;

            case 1:
                break;


        }

    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Aplicativo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Aplicativo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Aplicativo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Aplicativo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Aplicativo().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bBuscarPaciente;
    private javax.swing.JButton bMostrarPacientesRegistrados;
    private javax.swing.JButton bRegistrarPaciente;
    private javax.swing.JButton bRegistrarServicio;
    private javax.swing.JButton bSalir;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea tAreaReporte;
    private javax.swing.JLabel txtRegimenContributivoDeSalud;
    private javax.swing.JLabel txtReporte;
    private javax.swing.JLabel txtSeleccioneUnaOpcion;
    // End of variables declaration//GEN-END:variables

    // <editor-fold defaultstate="collapsed" desc="Generated Code">    
    private void initComponents() {

        txtRegimenContributivoDeSalud = new javax.swing.JLabel();
        txtSeleccioneUnaOpcion = new javax.swing.JLabel();
        txtReporte = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tAreaReporte = new javax.swing.JTextArea();
        bRegistrarPaciente = new javax.swing.JButton();
        bBuscarPaciente = new javax.swing.JButton();
        bMostrarPacientesRegistrados = new javax.swing.JButton();
        bRegistrarServicio = new javax.swing.JButton();
        bSalir = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Hospital");

        txtRegimenContributivoDeSalud.setFont(new java.awt.Font("Tahoma", 3, 36)); // NOI18N
        txtRegimenContributivoDeSalud.setText("Regimen contributivo de salud");

        txtSeleccioneUnaOpcion.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        txtSeleccioneUnaOpcion.setText("Seleccione una opción:");

        txtReporte.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        txtReporte.setText("Reporte:");

        tAreaReporte.setEditable(false);
        tAreaReporte.setColumns(20);
        tAreaReporte.setRows(5);
        tAreaReporte.setEnabled(false);
        jScrollPane1.setViewportView(tAreaReporte);

        bRegistrarPaciente.setFont(new java.awt.Font("Tahoma", 3, 16)); // NOI18N
        bRegistrarPaciente.setText("Registrar a un paciente");
        bRegistrarPaciente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bRegistrarPacienteActionPerformed(evt);
            }
        });

        bBuscarPaciente.setFont(new java.awt.Font("Tahoma", 3, 16)); // NOI18N
        bBuscarPaciente.setText("Buscar a un paciente");
        bBuscarPaciente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bBuscarPacienteActionPerformed(evt);
            }
        });

        bMostrarPacientesRegistrados.setFont(new java.awt.Font("Tahoma", 3, 16)); // NOI18N
        bMostrarPacientesRegistrados.setText("Mostrar a los pacientes registrados");
        bMostrarPacientesRegistrados.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bMostrarPacientesRegistradosActionPerformed(evt);
            }
        });

        bRegistrarServicio.setFont(new java.awt.Font("Tahoma", 3, 16)); // NOI18N
        bRegistrarServicio.setText("Registrar un servicio");
        bRegistrarServicio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bRegistrarServicioActionPerformed(evt);
            }
        });

        bSalir.setFont(new java.awt.Font("Tahoma", 3, 16)); // NOI18N
        bSalir.setForeground(new java.awt.Color(204, 0, 0));
        bSalir.setText("Salir del aplicativo");
        bSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bSalirActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addComponent(txtReporte)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addGroup(layout.createSequentialGroup()
                                                                .addComponent(bRegistrarPaciente)
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                                .addComponent(bBuscarPaciente)
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                                .addComponent(bMostrarPacientesRegistrados, javax.swing.GroupLayout.PREFERRED_SIZE, 343, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                                .addComponent(bRegistrarServicio, javax.swing.GroupLayout.DEFAULT_SIZE, 266, Short.MAX_VALUE)
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                                .addComponent(bSalir, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                        .addComponent(jScrollPane1)))
                                        .addGroup(layout.createSequentialGroup()
                                                .addComponent(txtSeleccioneUnaOpcion)
                                                .addGap(0, 0, Short.MAX_VALUE)))
                                .addContainerGap())
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(txtRegimenContributivoDeSalud, javax.swing.GroupLayout.PREFERRED_SIZE, 568, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(370, 370, 370))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(111, 111, 111)
                                .addComponent(txtSeleccioneUnaOpcion)
                                .addGap(111, 111, 111)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addComponent(txtReporte)
                                                .addGap(0, 0, Short.MAX_VALUE))
                                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 246, Short.MAX_VALUE))
                                .addContainerGap())
                        .addGroup(layout.createSequentialGroup()
                                .addGap(31, 31, 31)
                                .addComponent(txtRegimenContributivoDeSalud)
                                .addGap(94, 94, 94)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(bRegistrarPaciente)
                                        .addComponent(bBuscarPaciente)
                                        .addComponent(bMostrarPacientesRegistrados)
                                        .addComponent(bRegistrarServicio)
                                        .addComponent(bSalir))
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>

}
