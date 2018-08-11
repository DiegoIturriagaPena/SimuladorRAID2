/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simuladorraid;


import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ToolBar;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javax.swing.event.ChangeEvent;

/**
 *
 * @author Ariel
 */
public class VentanaPrincipal extends Stage implements EventHandler<Event> {
    private BorderPane root;
    private Button Abrir;
    private Button raid0;
    private Button raid1;
    private Button raid2;
    private Button raid3;
    private Button raid4;
    private Button raid5;
    private Button raid6;
    private ComboBox archivos;
    ArrayList<Archivo> archivosCargados;
    int actual;
    public VentanaPrincipal() {
        actual =-1;
        this.archivosCargados = new ArrayList<>();
        this.desarializar();
        this.root = new BorderPane();
        Scene scene = new Scene (root,960,542);
        this.setScene(scene);
        this.doTop();
        this.doCenter();
        this.setTitle("Raid Simulator: arcade triple omega mata pulgas y garrapatas ");
        this.setOnCloseRequest(new EventHandler<WindowEvent>() {
            public void handle(WindowEvent we) {
                serializar();
          }
        });
       
        System.out.println("" +this.archivosCargados.size());
    }
    
    
    @Override
    public void handle(Event event) {
        if(event.getSource() == this.Abrir && event instanceof ActionEvent)
        {
           FileChooser fileChooser = new FileChooser();
           fileChooser.setTitle("Abrir Archivo"); 
           File file = fileChooser.showOpenDialog(this); 
           if(file!=null)
           {
                this.activarBotones();
                String nombreDelArchivo = file.getParent() +"\\"+file.getName();
                String[] nombreDelArchivo2 = file.getName().split("\\.");
                System.out.println("" + nombreDelArchivo2[0]);
                Archivo archivo = new Archivo(nombreDelArchivo2[0],nombreDelArchivo);
                FileReader in = null;
                try {
                    in = new FileReader(file);
                } catch (FileNotFoundException ex) {
                    Logger.getLogger(VentanaPrincipal.class.getName()).log(Level.SEVERE, null, ex);
                }
               BufferedReader fi = new BufferedReader(in);
               Object[] datos = fi.lines().toArray();
               for(int i=0;i<datos.length;i++)
               {
                   String aux = (String)datos[i];
                   archivo.add(aux);
               }
               this.archivosCargados.add(archivo);
               actual = this.archivosCargados.size()-1;// se carga el archivo actual
           }
        }
        if(event.getSource() == this.raid0 && event instanceof ActionEvent)
        {

            Raid0 raid0 = new Raid0(this.archivosCargados.get(actual));
            raid0.procesarArchivoRaid0();
            this.raid0.setDisable(true);
            this.archivosCargados.get(actual).agregarRaidHecho("0");
        }
        
        if(event.getSource() == this.raid1 && event instanceof ActionEvent)
        {
            Raid1 raid1 = new Raid1(this.archivosCargados.get(actual));
            raid1.procesarArchivoRaid1();
            this.raid1.setDisable(true);
            this.archivosCargados.get(actual).agregarRaidHecho("1");
        }
        
        if(event.getSource() == this.raid5 && event instanceof ActionEvent)
        {
            Raid5 raid5 = new Raid5(this.archivosCargados.get(actual));
            raid5.procesarArchivoRaid5();
            this.raid5.setDisable(true);
            this.archivosCargados.get(actual).agregarRaidHecho("5");
        }
        
        if(event.getSource() == this.raid6 && event instanceof ActionEvent)
        {
            Raid6 raid6 = new Raid6(this.archivosCargados.get(actual));
            raid6.procesarArchivoRaid6();
            this.raid6.setDisable(true);
            this.archivosCargados.get(actual).agregarRaidHecho("6");
        }
    }

    private void doTop() {
       ToolBar botonera = new ToolBar();
       this.Abrir = new Button("Abrir");
       this.Abrir.addEventHandler(EventType.ROOT, this);
       botonera.getItems().add(Abrir);
       VBox contenedor = new VBox();
       contenedor.getChildren().add(botonera);
       this.root.setTop(contenedor);
    }

    private void doCenter() {
        VBox botones = new VBox();
        botones.setSpacing(10);
        botones.setPadding(new Insets(5,5,5,10));
        this.raid0 = new Button("Raid 0");
        this.raid0.addEventHandler(EventType.ROOT, this);
        this.raid0.setDisable(true);
        this.raid1 = new Button("Raid 1");
        this.raid1.addEventHandler(EventType.ROOT, this);
        this.raid1.setDisable(true);
        this.raid2 = new Button("Raid 2");
        this.raid2.addEventHandler(EventType.ROOT, this);
        this.raid2.setDisable(true);
        this.raid3 = new Button("Raid 3");
        this.raid3.addEventHandler(EventType.ROOT, this);
        this.raid3.setDisable(true);
        this.raid4 = new Button("Raid 4");
        this.addEventHandler(EventType.ROOT, this);
        this.raid4.setDisable(true);
        this.raid5 = new Button("Raid 5");
        this.raid5.addEventHandler(EventType.ROOT, this);
        this.raid5.setDisable(true);
        this.raid6 = new Button("Raid 6");
        this.raid6.addEventHandler(EventType.ROOT, this);
        this.raid6.setDisable(true);
        this.rellenarComboBox();
       
        botones.getChildren().addAll(this.raid0,this.raid1,this.raid2,this.raid3,this.raid4,this.raid5,this.raid6,this.archivos);
        this.root.setCenter(botones);
        
        
        
    }
    
    public void activarBotones()
    {
        this.raid0.setDisable(false);
        this.raid1.setDisable(false);
        this.raid2.setDisable(false);
        this.raid3.setDisable(false);
        this.raid4.setDisable(false);
        this.raid5.setDisable(false);
        this.raid6.setDisable(false);
    }
    
    public void serializar()
    {
        try {
            FileOutputStream fileOut =
            new FileOutputStream("respaldo\\respaldo.ser");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.reset();
            out.writeObject(this.archivosCargados);
            out.close();
            fileOut.close();
            System.out.printf("Datos serializados en \\respaldo\\respaldo.ser");
        }catch (IOException i) {
            i.printStackTrace();
        }
    }
    public void desarializar()
    {
        try {
            FileInputStream fileIn = new FileInputStream("respaldo\\respaldo.ser");
            ObjectInputStream in = new ObjectInputStream(fileIn);
            System.out.println("" + this.archivosCargados.size());
            Object nuevo = in.readObject();
            ArrayList<Archivo> new1 = (ArrayList)nuevo;
            this.archivosCargados = (ArrayList)nuevo;
        }catch (IOException i) {
            //i.printStackTrace();
            return;
        }catch (ClassNotFoundException c) {
            System.out.println("Employee class not found");
            c.printStackTrace();
            return;
        }catch(Exception e)
        {
            System.out.println("" + e.getMessage());
        }
        
    }
    
    public void rellenarComboBox()
    {
        ObservableList<SimpleStringProperty> nombres = FXCollections.observableArrayList();
        this.archivos = new ComboBox();
        for(int i =0;i<this.archivosCargados.size();i++)
        {
            SimpleStringProperty aux = new SimpleStringProperty(this.archivosCargados.get(i).getNombre());
            this.archivos.getItems().add(aux.getValue());
            
        }
        this.archivos.valueProperty().addListener(new ChangeListener() {
            @SuppressWarnings("rawtypes")
            @Override
            public void changed(ObservableValue ov, Object arg1,
                    Object arg2) {

                    System.out.println("" + (String)ov.getValue());
                    
                }
            }    
        );
      
        
    }
    
    public void buscarArchivo(String nombreArchivo)
    {
        for(int i=0;i<this.archivosCargados.size();i++)
        {
            if(nombreArchivo.compareTo(this.archivosCargados.get(i).getNombre())==0)
            {
                this.actual=i;
            }
        }
    }

  

   
}
