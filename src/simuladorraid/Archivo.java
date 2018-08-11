/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simuladorraid;

import java.util.ArrayList;

/**
 *
 * @author Ariel
 */
public class Archivo {
    private ArrayList<String> archivoCargado;
    private String nombre;
    private String direccion;
   
    public Archivo(String nombre, String direccion) {
        this.archivoCargado = new ArrayList<>();
        this.nombre = nombre;
        this.direccion = direccion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public int size() {
        return archivoCargado.size();
    }

    public String get(int index) {
        return archivoCargado.get(index);
    }

    public boolean add(String e) {
        return archivoCargado.add(e);
    }

    public String remove(int index) {
        return archivoCargado.remove(index);
    }
    
    
}
