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
public class Raid3 {
    private ArrayList<String> archivo1;
    private ArrayList<String> archivo2;
    private ArrayList<String> paridad;
    private Archivo archivoOriginal;

    public Raid3(Archivo archivoOriginal) {
        this.archivo1 = new ArrayList<>();
        this.archivo2 = new ArrayList<>();
        this.paridad = new ArrayList<>();
        this.archivoOriginal = archivoOriginal;
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
    
    public void procesarArchivoRaid3(){
        int cont = 0;
        int limite = this.archivoOriginal.size()/2;
        while(cont<limite){
            String linea = this.archivoOriginal.get(cont);
            this.agregarLineaArchivo1(linea);
            this.agregarParidad("1");
            cont++;
        }
        while(cont<this.archivoOriginal.size()){
            String linea = this.archivoOriginal.get(cont);
            this.agregarLineaArchivo2(linea);
            this.agregarParidad("1");
            cont++;
        }
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
            BufferedWriter out = new BufferedWriter(new FileWriter("RAID3\\RAID3_P\\Paridad" +  archivoOriginal.getNombre() + ".txt"));
            for (int i = 0; i < this.paridad.size(); i++) {
                out.write(this.paridad.get(i));
                out.newLine();
            }
            out.close();
        } catch (IOException e) {}
    }
    
    private ArrayList<String> abrirArchivo(String nombre)
    {
        ArrayList<String> lineas = new ArrayList<>();
        File file = new File ("RAID3\\" +nombre);
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
