/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simuladorraid;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author Diego Iturriaga
 */
public class Raid0 {
    private ArrayList<String> archivo1;
    private ArrayList<String> archivo2;
    private Archivo archivoOriginal;

    public Raid0(Archivo archivoOriginal) {
        this.archivo1 = new ArrayList<>();
        this.archivo2 = new ArrayList<>();
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
    
    public void procesarArchivoRaid0(){
        for (int i = 0; i < this.archivoOriginal.size(); i++) {
            String linea = this.archivoOriginal.get(i);
            if(i%2==0){
                this.agregarLineaArchivo1(linea);
            }
            else{
                this.agregarLineaArchivo2(linea);
            }
            this.CopiarAlDiscoDuro();
        }
    }
    
    public void CopiarAlDiscoDuro(){
        try {
            BufferedWriter out = new BufferedWriter(new FileWriter("RAID0\\RAID0_1\\Striping1" +  archivoOriginal.getNombre() ));
            for (int i = 0; i < this.archivo1.size(); i++) {
                out.write(this.archivo1.get(i));
                out.newLine();
            }
            out.close();
        } catch (IOException e) {}
        
        try {
            BufferedWriter out = new BufferedWriter(new FileWriter("RAID0\\RAID0_2\\Striping2" +  archivoOriginal.getNombre() ));
            for (int i = 0; i < this.archivo2.size(); i++) {
                out.write(this.archivo2.get(i));
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
}
