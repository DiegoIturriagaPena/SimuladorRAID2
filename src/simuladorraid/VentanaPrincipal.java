/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simuladorraid;


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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.ToolBar;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

/**
 *
 * @author Ariel
 */
public class VentanaPrincipal extends Stage implements EventHandler<Event> {
    private BorderPane root;
    //Botones para raid
    private Button Abrir;
    private Button raid0;
    private Button raid1;
    private Button raid2;
    private Button raid3;
    private Button raid4;
    private Button raid5;
    private Button raid6;
    //Botones para mostrar raid
    private Button mostrar0;
    private Button mostrar1;
    private Button mostrar2;
    private Button mostrar3;
    private Button mostrar4;
    private Button mostrar5;
    private Button mostrar6;
    //Aqui term
    private ComboBox archivos;
    private TextArea textoLeido;
    ArrayList<Archivo> archivosCargados;
    int actual;
    public VentanaPrincipal() {
        actual =-1;
        this.archivosCargados = new ArrayList<>();
        this.desarializar();
        this.root = new BorderPane();
        Scene scene = new Scene (root,871,352);
        this.setScene(scene);
        this.doTop();
        this.doCenter();
        this.setTitle("Raid Simulator / Ariel Cornejo - Diego Iturriaga");
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
           FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("TXT files (*.txt)", "*.txt");
           fileChooser.getExtensionFilters().add(extFilter);
           fileChooser.setTitle("Abrir Archivo"); 
           File file = fileChooser.showOpenDialog(this); 
           if(file!=null)
           {
                this.activarBotones();
                String nombreDelArchivo = file.getParent() +"\\"+file.getName();
                String[] nombreDelArchivo2 = file.getName().split("\\.");
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
               if(!this.coincidenciArchivo(archivo.getNombre()))
               {
                   this.archivosCargados.add(archivo);
                   actual = this.archivosCargados.size()-1;// se carga el archivo actual
                   this.rellenarComboBox(2);
                   this.archivos.getSelectionModel().selectLast();
               }
               else
               {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error");
                    alert.setHeaderText("Error");
                    alert.setContentText("Archivo ya cargado");
                    alert.showAndWait();
               }
               
           }
        }
        if(event.getSource() == this.raid0 && event instanceof ActionEvent)
        {
            if(this.archivosCargados.get(actual).size()>=4)
            {
                Raid0 raid0 = new Raid0(this.archivosCargados.get(actual));
                raid0.procesarArchivoRaid0();
                this.raid0.setDisable(true);
                this.mostrar0.setDisable(false);
                this.archivosCargados.get(actual).agregarRaidHecho("0");
            }
            else
            {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("Error");
                alert.setContentText("Archivo con menos de 4 lineas");
                alert.showAndWait();
            }
            
        }
        
        if(event.getSource() == this.raid1 && event instanceof ActionEvent)
        {
            if(this.archivosCargados.get(actual).size()>=2)
            {
                Raid1 raid1 = new Raid1(this.archivosCargados.get(actual));
                raid1.procesarArchivoRaid1();
                this.raid1.setDisable(true);
                this.mostrar1.setDisable(false);
                this.archivosCargados.get(actual).agregarRaidHecho("1");
            }
            else
            {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("Error");
                alert.setContentText("Archivo con menos de 2 lineas");
                alert.showAndWait();
            }
            
        }
        
        if(event.getSource() == this.raid2 && event instanceof ActionEvent)
        {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Información");
            alert.setHeaderText("RAID2");
            alert.setContentText("Función No Implementada.");
            alert.showAndWait();
        }
        
        if(event.getSource() == this.raid3 && event instanceof ActionEvent)
        {
            if(this.archivosCargados.get(actual).size()>=2)
            {
                Raid3 raid3 = new Raid3(this.archivosCargados.get(actual));
                raid3.procesarArchivoRaid3();
                this.raid3.setDisable(true);
                this.mostrar3.setDisable(false);
                this.archivosCargados.get(actual).agregarRaidHecho("3");
            }
            else
            {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("Error");
                alert.setContentText("Archivo con menos de 2 lineas");
                alert.showAndWait();
            }
            
        }
        
        if(event.getSource() == this.raid4 && event instanceof ActionEvent)
        {
            if(this.archivosCargados.get(actual).size()>=4)
            {
                Raid4 raid4 = new Raid4(this.archivosCargados.get(actual));
                raid4.procesarArchivoRaid4();
                this.raid4.setDisable(true);
                this.mostrar4.setDisable(false);
                this.archivosCargados.get(actual).agregarRaidHecho("4");
            }
            else
            {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("Error");
                alert.setContentText("Archivo con menos de 4 lineas");
                alert.showAndWait();
            }
            
        }
        
        if(event.getSource() == this.raid5 && event instanceof ActionEvent)
        {
            if(this.archivosCargados.get(actual).size()>=3)
            {
                Raid5 raid5 = new Raid5(this.archivosCargados.get(actual));
                raid5.procesarArchivoRaid5();
                this.raid5.setDisable(true);
                this.mostrar5.setDisable(false);
                this.archivosCargados.get(actual).agregarRaidHecho("5");
            }
            else
            {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("Error");
                alert.setContentText("Archivo con menos de 3 lineas");
                alert.showAndWait();
            }
            
        }
        
        if(event.getSource() == this.raid6 && event instanceof ActionEvent)
        {
            Raid6 raid6 = new Raid6(this.archivosCargados.get(actual));
            raid6.procesarArchivoRaid6();
            this.raid6.setDisable(true);
            this.mostrar6.setDisable(false);
            this.archivosCargados.get(actual).agregarRaidHecho("6");
        }
        ///Mostar texto
        if(event.getSource() == this.mostrar0 && event instanceof ActionEvent)
        {
            Archivo archivo = this.archivosCargados.get(actual);
            Raid0 raidDisk = new Raid0();
            String aux = raidDisk.reconstruir(archivo.getNombre());
            this.textoLeido.setText(aux);
            
        }
        if(event.getSource() == this.mostrar1 && event instanceof ActionEvent)
        {
            Archivo archivo = this.archivosCargados.get(actual);
            Raid1 raidDisk = new Raid1();
            String aux = raidDisk.reconstruir(archivo.getNombre());
            this.textoLeido.setText(aux);
            
        }
        if(event.getSource() == this.mostrar3 && event instanceof ActionEvent)
        {
            Archivo archivo = this.archivosCargados.get(actual);
            Raid3 raidDisk = new Raid3();
            String aux = raidDisk.reconstruir(archivo);
            this.textoLeido.setText(aux);
        }
        if(event.getSource() == this.mostrar4 && event instanceof ActionEvent)
        {
            Archivo archivo = this.archivosCargados.get(actual);
            Raid4 raidDisk = new Raid4();
            String aux = raidDisk.reconstruir(archivo);
            this.textoLeido.setText(aux);
            
        }
        if(event.getSource() == this.mostrar5 && event instanceof ActionEvent)
        {
            Archivo archivo = this.archivosCargados.get(actual);
            Raid5 raidDisk = new Raid5();
            String aux = raidDisk.reconstruir(archivo);
            this.textoLeido.setText(aux);
            
        }
        if(event.getSource() == this.mostrar6 && event instanceof ActionEvent)
        {
            Archivo archivo = this.archivosCargados.get(actual);
            Raid6 raidDisk = new Raid6();
            String aux = raidDisk.reconstruir(archivo);
            this.textoLeido.setText(aux);
            
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
        HBox columnas = new HBox();
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
        this.raid4.addEventHandler(EventType.ROOT, this);
        this.raid4.setDisable(true);
        this.raid5 = new Button("Raid 5");
        this.raid5.addEventHandler(EventType.ROOT, this);
        this.raid5.setDisable(true);
        this.raid6 = new Button("Raid 6");
        this.raid6.addEventHandler(EventType.ROOT, this);
        this.raid6.setDisable(true);
        this.textoLeido= new TextArea();
        this.textoLeido.setEditable(false);
        this.textoLeido.setPrefWidth(503);
        this.textoLeido.setPrefHeight(284);
        
        this.rellenarComboBox(1);
        botones.getChildren().addAll(this.raid0,this.raid1,this.raid2,this.raid3,this.raid4,this.raid5,this.raid6,this.archivos);
        VBox botonMostrar = new VBox();
        this.mostrar0 = new Button("mostrar");
        this.mostrar0.addEventHandler(EventType.ROOT, this);
        this.mostrar0.setDisable(true);
        this.mostrar1 = new Button("mostrar");
        this.mostrar1.addEventHandler(EventType.ROOT, this);
        this.mostrar1.setDisable(true);
        this.mostrar2 = new Button("mostrar");
        this.mostrar2.addEventHandler(EventType.ROOT, this);
        this.mostrar2.setDisable(true);
        this.mostrar3 = new Button("mostrar");
        this.mostrar3.addEventHandler(EventType.ROOT, this);
        this.mostrar3.setDisable(true);
        this.mostrar4 = new Button("mostrar");
        this.mostrar4.addEventHandler(EventType.ROOT, this);
        this.mostrar4.setDisable(true);
        this.mostrar5 = new Button("mostrar");
        this.mostrar5.addEventHandler(EventType.ROOT, this);
        this.mostrar5.setDisable(true);
        this.mostrar6 = new Button("mostrar");
        this.mostrar6.addEventHandler(EventType.ROOT, this);
        this.mostrar6.setDisable(true);
        botonMostrar.getChildren().addAll(this.mostrar0,this.mostrar1,this.mostrar2,this.mostrar3,this.mostrar4,this.mostrar5,this.mostrar6);
        botonMostrar.setSpacing(10);
        botonMostrar.setPadding(new Insets(5,5,5,30));
        columnas.setSpacing(10);
        columnas.setPrefHeight(300);
        VBox texto = new VBox();
        texto.setPrefHeight(300);
        texto.getChildren().add(this.textoLeido);
        columnas.getChildren().addAll(botones,botonMostrar,texto);
        
        this.archivos.getSelectionModel().selectFirst();
       
        this.root.setCenter(columnas);
        
        
        
    }
    
    public void desactivarMostrar()
    {
        this.mostrar0.setDisable(true);
        this.mostrar1.setDisable(true);
        this.mostrar2.setDisable(true);
        this.mostrar3.setDisable(true);
        this.mostrar4.setDisable(true);
        this.mostrar5.setDisable(true);
        this.mostrar6.setDisable(true);
        
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
    
    public void rellenarComboBox(int vez)
    {
        
        ObservableList<SimpleStringProperty> nombres = FXCollections.observableArrayList();
        if(vez!=1){
            this.archivos.getItems().add(this.archivosCargados.get(actual).getNombre());
        }
        else{
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
                        buscarArchivo((String)ov.getValue());

                    }
                }    
            );
        }
      
        
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
        this.desactivarMostrar();
        this.activarBotones();
        this.deshabilitarRaidHechos();
    }
    
    public boolean coincidenciArchivo(String nombre)
    {
        boolean encontrado=false;
        for(int i=0;i<this.archivosCargados.size() && !encontrado;i++)
        {
            if(nombre.compareTo(this.archivosCargados.get(i).getNombre())==0)
            {
                return true;
            }
        }
        
        return encontrado;
    }
    public void deshabilitarRaidHechos()
    {
        Archivo archivo = this.archivosCargados.get(actual);
        for(int i=0; i<archivo.cantidadRaidsHechos();i++)
        {
            String aux = archivo.obtenerRaidHecho(i);
            switch (aux)
            {
                case "0":
                    this.raid0.setDisable(true);
                    this.mostrar0.setDisable(false);
                    break;
                case "1":
                    this.raid1.setDisable(true);
                    this.mostrar1.setDisable(false);
                    break;
                case "2":
                    this.raid2.setDisable(true);
                    this.mostrar2.setDisable(false);
                    break;
                case "3":
                    this.raid3.setDisable(true);
                    this.mostrar3.setDisable(false);
                    break;
                case "4":
                    this.raid4.setDisable(true);
                    this.mostrar4.setDisable(false);
                    break;
                case "5":
                    this.raid5.setDisable(true);
                    this.mostrar5.setDisable(false);
                    break;
                case "6":
                    this.raid6.setDisable(true);
                    this.mostrar6.setDisable(false);
                    break;
            }
        }
    }

  

   
}
