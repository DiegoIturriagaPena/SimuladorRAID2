/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simuladorraid;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ToolBar;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

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
    ArrayList<Archivo> archivosCargados;
    int actual;
    public VentanaPrincipal() {
        actual =-1;
        this.archivosCargados = new ArrayList<>();
        this.root = new BorderPane();
        Scene scene = new Scene (root,960,542);
        this.setScene(scene);
        this.doTop();
        this.doCenter();
        this.setTitle("Raid Simulato: arcade triple omega mata pulgas y garrapatas ");
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
               Archivo archivo = new Archivo(file.getName(),nombreDelArchivo);
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
            Raid0 raid0= new Raid0();
            
        }
        
        if(event.getSource() == this.raid1 && event instanceof ActionEvent)
        {
            Raid1 raid1 = new Raid1(this.archivosCargados.get(actual));
            raid1.procesarArchivoRaid1();
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
        botones.getChildren().addAll(this.raid0,this.raid1,this.raid2,this.raid3,this.raid4,this.raid5,this.raid6);
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
    
}
