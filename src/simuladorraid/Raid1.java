/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simuladorraid;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.control.Alert;

/**
 *
 * @author Diego Iturriaga
 */
public class Raid1 {
    private ArrayList<String> archivo1;
    private ArrayList<String> archivo2;
    private Archivo archivoOriginal;

    public Raid1(Archivo archivoOriginal) {
        this.archivo1 = new ArrayList<>();
        this.archivo2 = new ArrayList<>();
        this.archivoOriginal = archivoOriginal;
    }
    
    public Raid1() {
    }
    
    public void procesarArchivoRaid1()
    {
        for(int i=0;i<archivoOriginal.size();i++)
        {
            String aux = "" + archivoOriginal.get(i);
            archivo1.add(aux);
            archivo2.add(aux);
        }
        
        try {
        BufferedWriter out = new BufferedWriter(new FileWriter("RAID1\\RAID1_1\\Mirror1" + archivoOriginal.getNombre() + ".txt" ));
            for (int i = 0; i < archivo1.size(); i++) {
                out.write(archivo1.get(i));
                out.newLine();
            }
            out.close();
        } catch (IOException e) {}
        try {
        BufferedWriter out = new BufferedWriter(new FileWriter("RAID1\\RAID1_2\\Mirror2" +  archivoOriginal.getNombre() + ".txt" ));
            for (int i = 0; i < archivo2.size(); i++) {
                out.write(archivo2.get(i));
                out.newLine();
            }
            out.close();
        } catch (IOException e) {}
    }
    
    public String reconstruir(String nombreArchivo){
        String textoReconstruido = "";
        ArrayList<String> Mirror1 = new ArrayList<>();
        ArrayList<String> Mirror2 = new ArrayList<>();

        try{
            Mirror1 = this.abrirArchivo("RAID1_1\\Mirror1"+nombreArchivo+".txt");
            Mirror2 = this.abrirArchivo("RAID1_2\\Mirror2"+nombreArchivo+".txt");

            for (int i = 0; i < Mirror1.size(); i++) {
                textoReconstruido = textoReconstruido + Mirror1.get(i)+"\n";
            }
            for (int i = 0; i < Mirror2.size(); i++) {
                textoReconstruido = textoReconstruido + Mirror2.get(i)+"\n";
            }
        }catch(NullPointerException e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Error");
            alert.setContentText("No se encuentra una de las segmentaciones.");
            alert.showAndWait();
        }

        return textoReconstruido;
    }
    
    private ArrayList<String> abrirArchivo(String nombre)
    {
        ArrayList<String> lineas = new ArrayList<>();
        File file = new File ("RAID1\\" +nombre);
        FileReader in = null;
        try {
            in = new FileReader(file);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(VentanaPrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        BufferedReader buffer = new BufferedReader(in);
        Object[] datos = buffer.lines().toArray();
        for(int i=0;i<datos.length;i++)
        {
            String aux = (String)datos[i];
            lineas.add(aux);
        }
        return lineas;
    }
}