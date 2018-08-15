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
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.control.Alert;

/**
 *
 * @author Diego Iturriaga
 */
public class Raid5 {
    private ArrayList<String> archivo1;
    private ArrayList<String> archivo2;
    private ArrayList<String> archivo3;
    private ArrayList<String> paridad;
    private Archivo archivoOriginal;

    public Raid5(Archivo archivoOriginal) {
        this.archivo1 = new ArrayList<>();
        this.archivo2 = new ArrayList<>();
        this.archivo3 = new ArrayList<>();
        this.paridad = new ArrayList<>();
        this.archivoOriginal = archivoOriginal;
    }
    
    public Raid5(){
        
    }
    
    public void procesarArchivoRaid5(){
        Random r = new Random();
        int numeroDisco = r.nextInt(3)+1;
        switch (numeroDisco) {
            case 1:
                this.enviarAlDisco1();
                this.CopiarAlDiscoDuro1();
                break;
            case 2:
                this.enviarAlDisco2();
                this.CopiarAlDiscoDuro2();
                break;
            case 3:
                this.enviarAlDisco3();
                this.CopiarAlDiscoDuro3();
                break;
        }
    }
    
    public void enviarAlDisco1(){
        for (int i = 0; i < this.archivoOriginal.size(); i++) {
            String linea = this.archivoOriginal.get(i);
            this.agregarLineaArchivo1(linea);
        }
        
        int cont = 0;
        int limite = this.archivoOriginal.size()/2;
        while(cont<limite){
            String linea = this.archivoOriginal.get(cont);
            this.agregarLineaArchivo2(linea);
            cont++;
        }
        this.agregarParidad("0");
        while(cont<this.archivoOriginal.size()){
            String linea = this.archivoOriginal.get(cont);
            this.agregarLineaArchivo3(linea);
            cont++;
        }
        this.agregarParidad("0");
    }
    
    public void CopiarAlDiscoDuro1(){
        try {
            BufferedWriter out = new BufferedWriter(new FileWriter("RAID5\\RAID5_1\\FullFile" +  archivoOriginal.getNombre() + ".txt"));
            for (int i = 0; i < this.archivo1.size(); i++) {
                out.write(this.archivo1.get(i));
                out.newLine();
            }
            out.close();
        } catch (IOException e) {}
        try {
            BufferedWriter out = new BufferedWriter(new FileWriter("RAID5\\RAID5_1\\Paridad" +  archivoOriginal.getNombre() + ".txt"));
            for (int i = 0; i < this.paridad.size(); i++) {
                out.write(this.paridad.get(i));
                out.newLine();
            }
            out.close();
        } catch (IOException e) {}
        try {
            BufferedWriter out = new BufferedWriter(new FileWriter("RAID5\\RAID5_2\\HalfFile1" +  archivoOriginal.getNombre() + ".txt"));
            for (int i = 0; i < this.archivo2.size(); i++) {
                out.write(this.archivo2.get(i));
                out.newLine();
            }
            out.close();
        } catch (IOException e) {}
        try {
            BufferedWriter out = new BufferedWriter(new FileWriter("RAID5\\RAID5_3\\HalfFile2" +  archivoOriginal.getNombre() + ".txt" ));
            for (int i = 0; i < this.archivo3.size(); i++) {
                out.write(this.archivo3.get(i));
                out.newLine();
            }
            out.close();
        } catch (IOException e) {}
    }
    
    public void enviarAlDisco2(){
        for (int i = 0; i < this.archivoOriginal.size(); i++) {
            String linea = this.archivoOriginal.get(i);
            this.agregarLineaArchivo2(linea);
        }
        
        int cont = 0;
        int limite = this.archivoOriginal.size()/2;
        while(cont<limite){
            String linea = this.archivoOriginal.get(cont);
            this.agregarLineaArchivo1(linea);
            cont++;
        }
        this.agregarParidad("0");
        while(cont<this.archivoOriginal.size()){
            String linea = this.archivoOriginal.get(cont);
            this.agregarLineaArchivo3(linea);
            cont++;
        }
        this.agregarParidad("0");
    }
    
    public void CopiarAlDiscoDuro2(){
        try {
            BufferedWriter out = new BufferedWriter(new FileWriter("RAID5\\RAID5_1\\HalfFile1" +  archivoOriginal.getNombre() + ".txt"));
            for (int i = 0; i < this.archivo1.size(); i++) {
                out.write(this.archivo1.get(i));
                out.newLine();
            }
            out.close();
        } catch (IOException e) {}
        try {
            BufferedWriter out = new BufferedWriter(new FileWriter("RAID5\\RAID5_2\\FullFile" +  archivoOriginal.getNombre() + ".txt"));
            for (int i = 0; i < this.archivo2.size(); i++) {
                out.write(this.archivo2.get(i));
                out.newLine();
            }
            out.close();
        } catch (IOException e) {}
        try {
            BufferedWriter out = new BufferedWriter(new FileWriter("RAID5\\RAID5_2\\Paridad" +  archivoOriginal.getNombre() + ".txt"));
            for (int i = 0; i < this.paridad.size(); i++) {
                out.write(this.paridad.get(i));
                out.newLine();
            }
            out.close();
        } catch (IOException e) {}
        try {
            BufferedWriter out = new BufferedWriter(new FileWriter("RAID5\\RAID5_3\\HalfFile2" +  archivoOriginal.getNombre() + ".txt"));
            for (int i = 0; i < this.archivo3.size(); i++) {
                out.write(this.archivo3.get(i));
                out.newLine();
            }
            out.close();
        } catch (IOException e) {}
    }
    
    public void enviarAlDisco3(){
        for (int i = 0; i < this.archivoOriginal.size(); i++) {
            String linea = this.archivoOriginal.get(i);
            this.agregarLineaArchivo3(linea);
        }
        
        int cont = 0;
        int limite = this.archivoOriginal.size()/2;
        while(cont<limite){
            String linea = this.archivoOriginal.get(cont);
            this.agregarLineaArchivo1(linea);
            cont++;
        }
        this.agregarParidad("0");
        while(cont<this.archivoOriginal.size()){
            String linea = this.archivoOriginal.get(cont);
            this.agregarLineaArchivo2(linea);
            cont++;
        }
        this.agregarParidad("0");
    }
    
    public void CopiarAlDiscoDuro3(){
        try {
            BufferedWriter out = new BufferedWriter(new FileWriter("RAID5\\RAID5_1\\HalfFile1" +  archivoOriginal.getNombre() + ".txt"));
            for (int i = 0; i < this.archivo1.size(); i++) {
                out.write(this.archivo1.get(i));
                out.newLine();
            }
            out.close();
        } catch (IOException e) {}
        try {
            BufferedWriter out = new BufferedWriter(new FileWriter("RAID5\\RAID5_2\\HalfFile2" +  archivoOriginal.getNombre() + ".txt"));
            for (int i = 0; i < this.archivo2.size(); i++) {
                out.write(this.archivo2.get(i));
                out.newLine();
            }
            out.close();
        } catch (IOException e) {}
        try {
            BufferedWriter out = new BufferedWriter(new FileWriter("RAID5\\RAID5_3\\FullFile" +  archivoOriginal.getNombre() + ".txt"));
            for (int i = 0; i < this.archivo3.size(); i++) {
                out.write(this.archivo3.get(i));
                out.newLine();
            }
            out.close();
        } catch (IOException e) {}
        try {
            BufferedWriter out = new BufferedWriter(new FileWriter("RAID5\\RAID5_3\\Paridad" +  archivoOriginal.getNombre() + ".txt"));
            for (int i = 0; i < this.paridad.size(); i++) {
                out.write(this.paridad.get(i));
                out.newLine();
            }
            out.close();
        } catch (IOException e) {}
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
    
    private void sobreescribirParidad(Archivo archivo, ArrayList<String> paridad, int numeroDisco) {
        try {
            BufferedWriter out = new BufferedWriter(new FileWriter("RAID5\\RAID5_"+numeroDisco+"\\Paridad" +  archivo.getNombre() + ".txt"));
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
        String textoReconstruido = ""+archivo.getNombre()+" - RAID5 (Este mensaje NO forma parte del archivo.)\n";
        ArrayList<String> halfFile1 = new ArrayList<>();
        ArrayList<String> halfFile2 = new ArrayList<>();
        ArrayList<String> paridad = new ArrayList<>();
        ArrayList<String> fullFile = new ArrayList<>();
        ArrayList<String> archivoOriginal = this.obtenerLineasArchivo(archivo);
        int disco = 0;
        try{
            halfFile1 = buscarArchivo("HalfFile1"+archivo.getNombre());
            halfFile2 = buscarArchivo("HalfFile2"+archivo.getNombre());
            paridad = buscarArchivo("Paridad"+archivo.getNombre());
            disco = buscarDisco("Paridad"+archivo.getNombre());
            fullFile = buscarArchivo("FullFile"+archivo.getNombre());
            
            int cont = 0;
            while(cont<archivoOriginal.size()){
                for (int i = 0; i < halfFile1.size(); i++) {
                    if(!archivoOriginal.get(cont).equals(halfFile1.get(i))){
                        paridad.remove(0);
                        paridad.add(0, "1");
                    }
                    textoReconstruido = textoReconstruido + halfFile1.get(i)+"\n";
                    cont++;
                }
                
                for (int i = 0; i < halfFile2.size(); i++) {
                    if(!archivoOriginal.get(cont).equals(halfFile2.get(i))){
                        paridad.remove(1);
                        paridad.add(1, "1");
                    }
                    textoReconstruido = textoReconstruido + halfFile2.get(i)+"\n";
                    cont++;
                }
            }
            if(this.verificarParidad(paridad) && cont==archivoOriginal.size()){
                return textoReconstruido;
            }
            else{
                this.sobreescribirParidad(archivo, paridad, disco);
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("Error");
                alert.setContentText("La Paridad indica errores en los bytes.");
                alert.showAndWait();
            }
        }
        catch(NullPointerException e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Error");
            alert.setContentText("Archivos Corruptos o Eliminados.");
            alert.showAndWait();
        }
        
        return "";
    }
    
    private ArrayList<String> buscarArchivo(String nombre){
        ArrayList<String> archivo = new ArrayList<>();
        
        try{
            archivo = this.abrirArchivo("RAID5_1\\"+nombre+".txt");
            return archivo;
        }
        catch(NullPointerException e){
            try{
                archivo = this.abrirArchivo("RAID5_2\\"+nombre+".txt");
                return archivo;
            }
            catch (NullPointerException e1){
                try{
                    archivo = this.abrirArchivo("RAID5_3\\"+nombre+".txt");
                    return archivo;
                }
                catch(NullPointerException e2){
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error");
                    alert.setHeaderText("Error");
                    alert.setContentText("Archivo "+ nombre +".txt esta corrupto o fue eliminado.");
                    alert.showAndWait();
                }
            }
        }
        return null;
    }
    
    private Integer buscarDisco(String nombre){
        int disco = 0;
        ArrayList<String> archivo = new ArrayList<>();
        
        
        try{
            archivo = this.abrirArchivo("RAID5_1\\"+nombre+".txt");
            disco = 1;
        }
        catch(NullPointerException e){
            try{
                archivo = this.abrirArchivo("RAID5_2\\"+nombre+".txt");
                disco = 2;
            }
            catch (NullPointerException e1){
                try{
                    archivo = this.abrirArchivo("RAID5_3\\"+nombre+".txt");
                    disco = 3;
                }
                catch(NullPointerException e2){
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error");
                    alert.setHeaderText("Error");
                    alert.setContentText("No se encuentra el archivo "+ nombre +".txt.");
                    alert.showAndWait();
                }
            }
        }
        return disco;
    }
    
    private ArrayList<String> abrirArchivo(String nombre)
    {
        ArrayList<String> lineas = new ArrayList<>();
        File file = new File ("RAID5\\" +nombre);
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
