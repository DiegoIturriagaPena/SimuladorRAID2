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
public class Raid3 {
    private ArrayList<String> archivo1;
    private ArrayList<String> archivo2;
    private ArrayList<String> paridad1;
    private ArrayList<String> paridad2;
    private Archivo archivoOriginal;

    public Raid3(Archivo archivoOriginal) {
        this.archivo1 = new ArrayList<>();
        this.archivo2 = new ArrayList<>();
        this.paridad1 = new ArrayList<>();
        this.paridad2 = new ArrayList<>();
        this.archivoOriginal = archivoOriginal;
    }
    
    public Raid3(){
        
    }
    
    public int cantidadLineasArchivo1() {
        return archivo1.size();
    }

    public String obtenerLineaArchivo1(int index) {
        return archivo1.get(index);
    }

    public boolean agregarLineaArchivo1(String e) {
        return archivo1.add(e);
    }

    public String eliminarLineaArchivo1(int index) {
        return archivo1.remove(index);
    }

    public int cantidadLineasArchivo2() {
        return archivo2.size();
    }

    public String obtenerLineaArchivo2(int index) {
        return archivo2.get(index);
    }

    public boolean agregarLineaArchivo2(String e) {
        return archivo2.add(e);
    }

    public String eliminarLineaArchivo2(int index) {
        return archivo2.remove(index);
    }

    public int cantidadParidad() {
        return paridad1.size();
    }

    public String obtenerParidad(int index) {
        return paridad1.get(index);
    }

    public boolean agregarParidad(String e) {
        return paridad1.add(e);
    }

    public String eliminarParidad(int index) {
        return paridad1.remove(index);
    }
    
    public int cantidadParidad2() {
        return paridad2.size();
    }

    public String obtenerParidad2(int index) {
        return paridad2.get(index);
    }

    public boolean agregarParidad2(String e) {
        return paridad2.add(e);
    }

    public String eliminarParidad2(int index) {
        return paridad2.remove(index);
    }
    
    public void procesarArchivoRaid3(){
        int cont = 0;
        int limite = this.archivoOriginal.size()/2;
        while(cont<limite){
            String linea = this.archivoOriginal.get(cont);
            this.agregarLineaArchivo1(linea);
            this.agregarParidad("0");
            cont++;
        }
        while(cont<this.archivoOriginal.size()){
            String linea = this.archivoOriginal.get(cont);
            this.agregarLineaArchivo2(linea);
            this.agregarParidad2("0");
            cont++;
        }
        this.CopiarAlDiscoDuro();
    }
    
    public void CopiarAlDiscoDuro(){
        try {
            BufferedWriter out = new BufferedWriter(new FileWriter("RAID3\\RAID3_1\\Bytes1" +  archivoOriginal.getNombre() + ".txt"));
            for (int i = 0; i < this.archivo1.size(); i++) {
                out.write(this.archivo1.get(i));
                out.newLine();
            }
            out.close();
        } catch (IOException e) {}
        
        try {
            BufferedWriter out = new BufferedWriter(new FileWriter("RAID3\\RAID3_2\\Bytes2" +  archivoOriginal.getNombre() + ".txt"));
            for (int i = 0; i < this.archivo2.size(); i++) {
                out.write(this.archivo2.get(i));
                out.newLine();
            }
            out.close();
        } catch (IOException e) {}
        
        try {
            BufferedWriter out = new BufferedWriter(new FileWriter("RAID3\\RAID3_P\\Paridad1" +  archivoOriginal.getNombre() + ".txt"));
            for (int i = 0; i < this.paridad1.size(); i++) {
                out.write(this.paridad1.get(i));
                out.newLine();
            }
            out.close();
        } catch (IOException e) {}
        try {
            BufferedWriter out = new BufferedWriter(new FileWriter("RAID3\\RAID3_P\\Paridad2" +  archivoOriginal.getNombre() + ".txt"));
            for (int i = 0; i < this.paridad2.size(); i++) {
                out.write(this.paridad2.get(i));
                out.newLine();
            }
            out.close();
        } catch (IOException e) {}
    }
    
    private ArrayList<String> obtenerLineasArchivo(Archivo archivo){
        ArrayList<String> lineasArchivoOriginal = new ArrayList<>();
        for (int i = 0; i < archivo.size(); i++) {
            lineasArchivoOriginal.add(archivo.get(i));
        }
        return lineasArchivoOriginal;
    }
    
    private Boolean verificarParidad(ArrayList<String> paridad){
        for (int i = 0; i < paridad.size(); i++) {
            if("1".equals(paridad.get(i))){
                return false;
            }
        }
        return true;
    }
    
    private void sobreescribirParidad1(Archivo archivo, ArrayList<String> paridad){
        try {
            BufferedWriter out = new BufferedWriter(new FileWriter("RAID3\\RAID3_P\\Paridad1" +  archivo.getNombre() + ".txt"));
            for (int i = 0; i < paridad.size(); i++) {
                out.write(paridad.get(i));
                out.newLine();
            }
            out.close();
        } catch (IOException e) {}
    }
    
    private void sobreescribirParidad2(Archivo archivo, ArrayList<String> paridad){
        try {
            BufferedWriter out = new BufferedWriter(new FileWriter("RAID3\\RAID3_P\\Paridad2" +  archivo.getNombre() + ".txt"));
            for (int i = 0; i < paridad.size(); i++) {
                out.write(paridad.get(i));
                out.newLine();
            }
            out.close();
        } catch (IOException e) {}
    }
    
    public String reconstruir(Archivo archivo){ 
        String textoReconstruido = ""+archivo.getNombre()+" - RAID3 (Este mensaje NO forma parte del archivo.)\n";
        ArrayList<String> bytes1 = new ArrayList<>();
        ArrayList<String> bytes2 = new ArrayList<>();
        ArrayList<String> paridad1 = new ArrayList<>();
        ArrayList<String> paridad2 = new ArrayList<>();
        ArrayList<String> archivoOriginal = this.obtenerLineasArchivo(archivo);
        try{
            paridad1 = this.abrirArchivo("RAID3_P\\Paridad1"+archivo.getNombre()+".txt");
            paridad2 = this.abrirArchivo("RAID3_P\\Paridad2"+archivo.getNombre()+".txt");
            bytes1 = this.abrirArchivo("RAID3_1\\Bytes1"+archivo.getNombre()+".txt");
            bytes2 = this.abrirArchivo("RAID3_2\\Bytes2"+archivo.getNombre()+".txt");
            
            int cont = 0;
            while(cont<archivoOriginal.size()){
                for (int i = 0; i < bytes1.size(); i++) {
                    if(!archivoOriginal.get(cont).equals(bytes1.get(i))){
                        paridad1.remove(i);
                        paridad1.add(i, "1");
                    }
                    textoReconstruido = textoReconstruido + bytes1.get(i)+"\n";
                    cont++;
                }
                
                for (int i = 0; i < bytes2.size(); i++) {
                    if(!archivoOriginal.get(cont).equals(bytes2.get(i))){
                        paridad2.remove(i);
                        paridad2.add(i, "1");
                    }
                    textoReconstruido = textoReconstruido + bytes2.get(i)+"\n";
                    cont++;
                }
            }

            if(this.verificarParidad(paridad1) && this.verificarParidad(paridad2) && cont==archivoOriginal.size()){
                return textoReconstruido;
            }
            else{
                this.sobreescribirParidad1(archivo, paridad1);
                this.sobreescribirParidad2(archivo, paridad2);
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("Error");
                alert.setContentText("La Paridad indica errores en los bytes.");
                alert.showAndWait();
            }
            
        }catch(NullPointerException e0){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Error");
            alert.setContentText("Archivos Corruptos o Eliminados.");
            alert.showAndWait();
        }
        
        return "";
    }
    
    private ArrayList<String> abrirArchivo(String nombre)
    {
        ArrayList<String> lineas = new ArrayList<>();
        File file = new File ("RAID3\\" +nombre);
        FileReader in = null;
        try {
            in = new FileReader(file);
        } catch (FileNotFoundException ex) {
            
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
