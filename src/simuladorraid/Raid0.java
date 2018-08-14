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

/**
 *
 * @author Diego Iturriaga
 */
public class Raid0 {
    private ArrayList<String> archivo1;
    private ArrayList<String> archivo2;
    private ArrayList<String> archivo3;
    private ArrayList<String> archivo4;
    private Archivo archivoOriginal;

    public Raid0(Archivo archivoOriginal) {
        this.archivo1 = new ArrayList<>();
        this.archivo2 = new ArrayList<>();
        this.archivo3 = new ArrayList<>();
        this.archivo4 = new ArrayList<>();
        this.archivoOriginal = archivoOriginal;
    }
    
    public Raid0() {
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
    
    public void procesarArchivoRaid0(){
        int cont = 0;
        int limite = this.archivoOriginal.size()/4;
        while(cont<limite){
            String linea = this.archivoOriginal.get(cont);
            this.agregarLineaArchivo1(linea);
            cont++;
        }
        while(cont<(2*limite)){
            String linea = this.archivoOriginal.get(cont);
            this.agregarLineaArchivo2(linea);
            cont++;
        }
        while(cont<(3*limite)){
            String linea = this.archivoOriginal.get(cont);
            this.agregarLineaArchivo3(linea);
            cont++;
        }
        while(cont<this.archivoOriginal.size()){
            String linea = this.archivoOriginal.get(cont);
            this.agregarLineaArchivo4(linea);
            cont++;
        }
        this.CopiarAlDiscoDuro();
    }
    
    public void CopiarAlDiscoDuro(){
        try {
            BufferedWriter out = new BufferedWriter(new FileWriter("RAID0\\RAID0_1\\Striping1" +  archivoOriginal.getNombre() + ".txt"));
            for (int i = 0; i < this.archivo1.size(); i++) {
                out.write(this.archivo1.get(i));
                out.newLine();
            }
            out.close();
        } catch (IOException e) {}
        
        try {
            BufferedWriter out = new BufferedWriter(new FileWriter("RAID0\\RAID0_2\\Striping2" +  archivoOriginal.getNombre() + ".txt"));
            for (int i = 0; i < this.archivo2.size(); i++) {
                out.write(this.archivo2.get(i));
                out.newLine();
            }
            out.close();
        } catch (IOException e) {}
        try {
            BufferedWriter out = new BufferedWriter(new FileWriter("RAID0\\RAID0_1\\Striping3" +  archivoOriginal.getNombre() + ".txt"));
            for (int i = 0; i < this.archivo3.size(); i++) {
                out.write(this.archivo3.get(i));
                out.newLine();
            }
            out.close();
        } catch (IOException e) {}
        try {
            BufferedWriter out = new BufferedWriter(new FileWriter("RAID0\\RAID0_2\\Striping4" +  archivoOriginal.getNombre() + ".txt"));
            for (int i = 0; i < this.archivo4.size(); i++) {
                out.write(this.archivo4.get(i));
                out.newLine();
            }
            out.close();
        } catch (IOException e) {}
    }
    
    public void imprimir(){
        System.out.println("Archivo1: ");
        for (int i = 0; i < this.cantidadLineasArchivo1(); i++) {
            System.out.println(this.obtenerLineaArchivo1(i));
        }
        System.out.println("____________________________________________________________________________");
        System.out.println("Archivo2: ");
        for (int i = 0; i < this.cantidadLineasArchivo2(); i++) {
            System.out.println(this.obtenerLineaArchivo2(i));
        }
    }
    
    public String reconstruir(String nombreArchivo){
        
        
        return "";
    }
    
    private ArrayList<String> abrirArchivo(String nombre)
    {
        ArrayList<String> lineas = null;
        File file = new File ("RAID0\\" +nombre);
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
