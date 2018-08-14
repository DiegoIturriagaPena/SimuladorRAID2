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
public class Raid4 {
    private ArrayList<String> archivo1;
    private ArrayList<String> archivo2;
    private ArrayList<String> archivo3;
    private ArrayList<String> archivo4;
    private ArrayList<String> paridad;
    private Archivo archivoOriginal;
    
    public Raid4(Archivo archivoOriginal) {
        this.archivo1 = new ArrayList<>();
        this.archivo2 = new ArrayList<>();
        this.archivo3 = new ArrayList<>();
        this.archivo4 = new ArrayList<>();
        this.paridad = new ArrayList<>();
        this.archivoOriginal = archivoOriginal;
    }
    
    public Raid4(){
        
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
    
    public int cantidadLineasArchivo3() {
        return archivo3.size();
    }

    public String obtenerLineaArchivo3(int index) {
        return archivo3.get(index);
    }

    public boolean agregarLineaArchivo3(String e) {
        return archivo3.add(e);
    }

    public String eliminarLineaArchivo3(int index) {
        return archivo3.remove(index);
    }
    
    public int cantidadLineasArchivo4() {
        return archivo4.size();
    }

    public String obtenerLineaArchivo4(int index) {
        return archivo4.get(index);
    }

    public boolean agregarLineaArchivo4(String e) {
        return archivo4.add(e);
    }

    public String eliminarLineaArchivo4(int index) {
        return archivo4.remove(index);
    }

    public int cantidadParidad() {
        return paridad.size();
    }

    public String obtenerParidad(int index) {
        return paridad.get(index);
    }

    public boolean agregarParidad(String e) {
        return paridad.add(e);
    }

    public String eliminarParidad(int index) {
        return paridad.remove(index);
    }
    
    public void procesarArchivoRaid4(){
        int cont = 0;
        int limite = this.archivoOriginal.size()/4;
        while(cont<limite){
            String linea = this.archivoOriginal.get(cont);
            this.agregarLineaArchivo1(linea);
            
            cont++;
        }
        this.agregarParidad("0");
        while(cont<(2*limite)){
            String linea = this.archivoOriginal.get(cont);
            this.agregarLineaArchivo2(linea);
            cont++;
        }
        this.agregarParidad("0");
        
        while(cont<(3*limite)){
            String linea = this.archivoOriginal.get(cont);
            this.agregarLineaArchivo3(linea);
            cont++;
        }
        this.agregarParidad("0");
        
        while(cont<this.archivoOriginal.size()){
            String linea = this.archivoOriginal.get(cont);
            this.agregarLineaArchivo4(linea);
            cont++;
        }
        this.agregarParidad("0");
        this.CopiarAlDiscoDuro();
    }
    
    private void CopiarAlDiscoDuro() {
        try {
            BufferedWriter out = new BufferedWriter(new FileWriter("RAID4\\RAID4_1\\Fourth1" +  archivoOriginal.getNombre() + ".txt"));
            for (int i = 0; i < this.archivo1.size(); i++) {
                out.write(this.archivo1.get(i));
                out.newLine();
            }
            out.close();
        } catch (IOException e) {}
        try {
            BufferedWriter out = new BufferedWriter(new FileWriter("RAID4\\RAID4_2\\Fourth2" +  archivoOriginal.getNombre() + ".txt"));
            for (int i = 0; i < this.archivo2.size(); i++) {
                out.write(this.archivo2.get(i));
                out.newLine();
            }
            out.close();
        } catch (IOException e) {}
        try {
            BufferedWriter out = new BufferedWriter(new FileWriter("RAID4\\RAID4_3\\Fourth3" +  archivoOriginal.getNombre() + ".txt"));
            for (int i = 0; i < this.archivo3.size(); i++) {
                out.write(this.archivo3.get(i));
                out.newLine();
            }
            out.close();
        } catch (IOException e) {}
        try {
            BufferedWriter out = new BufferedWriter(new FileWriter("RAID4\\RAID4_4\\Fourth4" +  archivoOriginal.getNombre() + ".txt"));
            for (int i = 0; i < this.archivo4.size(); i++) {
                out.write(this.archivo4.get(i));
                out.newLine();
            }
            out.close();
        } catch (IOException e) {}
        try {
            BufferedWriter out = new BufferedWriter(new FileWriter("RAID4\\RAID4_P\\Paridad" +  archivoOriginal.getNombre() + ".txt"));
            for (int i = 0; i < this.paridad.size(); i++) {
                out.write(this.paridad.get(i));
                out.newLine();
            }
            out.close();
        } catch (IOException e) {}
    }
    
    private void sobreescribirParidad(Archivo archivo, ArrayList<String> paridad) {
        try {
            BufferedWriter out = new BufferedWriter(new FileWriter("RAID4\\RAID4_P\\Paridad" +  archivo.getNombre() + ".txt"));
            for (int i = 0; i < paridad.size(); i++) {
                out.write(paridad.get(i));
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
    
    public String reconstruir(Archivo archivo){
        String textoReconstruido = ""+archivo.getNombre()+" - RAID4 (Este mensaje NO forma parte del archivo.)\n";
        ArrayList<String> fourth1 = new ArrayList<>();
        ArrayList<String> fourth2 = new ArrayList<>();
        ArrayList<String> fourth3 = new ArrayList<>();
        ArrayList<String> fourth4 = new ArrayList<>();
        ArrayList<String> paridad = new ArrayList<>();
        ArrayList<String> archivoOriginal = this.obtenerLineasArchivo(archivo);
        try{
            fourth1 = this.abrirArchivo("RAID4_1\\Fourth1"+archivo.getNombre()+".txt");
            fourth2 = this.abrirArchivo("RAID4_2\\Fourth2"+archivo.getNombre()+".txt");
            fourth3 = this.abrirArchivo("RAID4_3\\Fourth3"+archivo.getNombre()+".txt");
            fourth4 = this.abrirArchivo("RAID4_4\\Fourth4"+archivo.getNombre()+".txt");
            paridad = this.abrirArchivo("RAID4_P\\Paridad"+archivo.getNombre()+".txt");
            
            int cont = 0;
            while(cont<archivoOriginal.size()){
                for (int i = 0; i < fourth1.size(); i++) {
                    if(!archivoOriginal.get(cont).equals(fourth1.get(i))){
                        paridad.remove(0);
                        paridad.add(0, "1");
                    }
                    textoReconstruido = textoReconstruido + fourth1.get(i)+"\n";
                    cont++;
                }
                
                for (int i = 0; i < fourth2.size(); i++) {
                    if(!archivoOriginal.get(cont).equals(fourth2.get(i))){
                        paridad.remove(1);
                        paridad.add(1, "1");
                    }
                    textoReconstruido = textoReconstruido + fourth2.get(i)+"\n";
                    cont++;
                }
                
                for (int i = 0; i < fourth3.size(); i++) {
                    if(!archivoOriginal.get(cont).equals(fourth3.get(i))){
                        paridad.remove(2);
                        paridad.add(2, "1");
                    }
                    textoReconstruido = textoReconstruido + fourth3.get(i)+"\n";
                    cont++;
                }
                for (int i = 0; i < fourth4.size(); i++) {
                    if(!archivoOriginal.get(cont).equals(fourth4.get(i))){
                        paridad.remove(3);
                        paridad.add(3, "1");
                    }
                    textoReconstruido = textoReconstruido + fourth4.get(i)+"\n";
                    cont++;
                }
            }
            if(this.verificarParidad(paridad) && cont==archivoOriginal.size()){
                return textoReconstruido;
            }
            else{
                this.sobreescribirParidad(archivo, paridad);
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("Error");
                alert.setContentText("La Paridad indica errores en los bytes.");
                alert.showAndWait();
            }
        }
        catch (NullPointerException e){
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
        File file = new File ("RAID4\\" +nombre);
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
