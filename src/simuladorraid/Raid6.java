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
import java.util.Random;

/**
 *
 * @author Diego Iturriaga
 */
public class Raid6 {
    private ArrayList<String> archivo1;
    private ArrayList<String> archivo2;
    private ArrayList<String> archivo3;
    private ArrayList<String> archivo4;
    private Archivo archivoOriginal;

    public Raid6(Archivo archivoOriginal) {
        this.archivo1 = new ArrayList<>();
        this.archivo2 = new ArrayList<>();
        this.archivo3 = new ArrayList<>();
        this.archivo4 = new ArrayList<>();
        this.archivoOriginal = archivoOriginal;
    }
    
    public void procesarArchivoRaid6(){
        Random r = new Random();
        int numeroDisco = r.nextInt(4)+1;
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
            case 4:
                this.enviarAlDisco4();
                this.CopiarAlDiscoDuro4();
                break;
        }
    }

    private void enviarAlDisco1() {
        for (int i = 0; i < this.archivoOriginal.size(); i++) {
            String linea = this.archivoOriginal.get(i);
            this.agregarLineaArchivo1(linea);
        }
        
        int cont = 0;
        int limite = this.archivoOriginal.size()/3;
        while(cont<limite){
            String linea = this.archivoOriginal.get(cont);
            this.agregarLineaArchivo2(linea);
            cont++;
        }
        while(cont<(limite+limite)){
            String linea = this.archivoOriginal.get(cont);
            this.agregarLineaArchivo3(linea);
            cont++;
        }
        while(cont<this.archivoOriginal.size()){
            String linea = this.archivoOriginal.get(cont);
            this.agregarLineaArchivo4(linea);
            cont++;
        }
    }

    private void CopiarAlDiscoDuro1() {
        try {
            BufferedWriter out = new BufferedWriter(new FileWriter("RAID6\\RAID6_1\\FullFile" +  archivoOriginal.getNombre() ));
            for (int i = 0; i < this.archivo1.size(); i++) {
                out.write(this.archivo1.get(i));
                out.newLine();
            }
            out.close();
        } catch (IOException e) {}
        try {
            BufferedWriter out = new BufferedWriter(new FileWriter("RAID6\\RAID6_2\\Third1" +  archivoOriginal.getNombre() ));
            for (int i = 0; i < this.archivo2.size(); i++) {
                out.write(this.archivo2.get(i));
                out.newLine();
            }
            out.close();
        } catch (IOException e) {}
        try {
            BufferedWriter out = new BufferedWriter(new FileWriter("RAID6\\RAID6_3\\Third2" +  archivoOriginal.getNombre() ));
            for (int i = 0; i < this.archivo3.size(); i++) {
                out.write(this.archivo3.get(i));
                out.newLine();
            }
            out.close();
        } catch (IOException e) {}
        try {
            BufferedWriter out = new BufferedWriter(new FileWriter("RAID6\\RAID6_4\\Third3" +  archivoOriginal.getNombre() ));
            for (int i = 0; i < this.archivo4.size(); i++) {
                out.write(this.archivo4.get(i));
                out.newLine();
            }
            out.close();
        } catch (IOException e) {}
    }

    private void enviarAlDisco2() {
        for (int i = 0; i < this.archivoOriginal.size(); i++) {
            String linea = this.archivoOriginal.get(i);
            this.agregarLineaArchivo2(linea);
        }
        
        int cont = 0;
        int limite = this.archivoOriginal.size()/3;
        while(cont<limite){
            String linea = this.archivoOriginal.get(cont);
            this.agregarLineaArchivo1(linea);
            cont++;
        }
        while(cont<(limite+limite)){
            String linea = this.archivoOriginal.get(cont);
            this.agregarLineaArchivo3(linea);
            cont++;
        }
        while(cont<this.archivoOriginal.size()){
            String linea = this.archivoOriginal.get(cont);
            this.agregarLineaArchivo4(linea);
            cont++;
        }
    }

    private void CopiarAlDiscoDuro2() {
        try {
            BufferedWriter out = new BufferedWriter(new FileWriter("RAID6\\RAID6_1\\Third1" +  archivoOriginal.getNombre() ));
            for (int i = 0; i < this.archivo1.size(); i++) {
                out.write(this.archivo1.get(i));
                out.newLine();
            }
            out.close();
        } catch (IOException e) {}
        try {
            BufferedWriter out = new BufferedWriter(new FileWriter("RAID6\\RAID6_2\\FullFile" +  archivoOriginal.getNombre() ));
            for (int i = 0; i < this.archivo2.size(); i++) {
                out.write(this.archivo2.get(i));
                out.newLine();
            }
            out.close();
        } catch (IOException e) {}
        try {
            BufferedWriter out = new BufferedWriter(new FileWriter("RAID6\\RAID6_3\\Third2" +  archivoOriginal.getNombre() ));
            for (int i = 0; i < this.archivo3.size(); i++) {
                out.write(this.archivo3.get(i));
                out.newLine();
            }
            out.close();
        } catch (IOException e) {}
        try {
            BufferedWriter out = new BufferedWriter(new FileWriter("RAID6\\RAID6_4\\Third3" +  archivoOriginal.getNombre() ));
            for (int i = 0; i < this.archivo4.size(); i++) {
                out.write(this.archivo4.get(i));
                out.newLine();
            }
            out.close();
        } catch (IOException e) {}
    }

    private void enviarAlDisco3() {
        for (int i = 0; i < this.archivoOriginal.size(); i++) {
            String linea = this.archivoOriginal.get(i);
            this.agregarLineaArchivo3(linea);
        }
        
        int cont = 0;
        int limite = this.archivoOriginal.size()/3;
        while(cont<limite){
            String linea = this.archivoOriginal.get(cont);
            this.agregarLineaArchivo1(linea);
            cont++;
        }
        while(cont<(limite+limite)){
            String linea = this.archivoOriginal.get(cont);
            this.agregarLineaArchivo2(linea);
            cont++;
        }
        while(cont<this.archivoOriginal.size()){
            String linea = this.archivoOriginal.get(cont);
            this.agregarLineaArchivo4(linea);
            cont++;
        }
    }

    private void CopiarAlDiscoDuro3() {
        try {
            BufferedWriter out = new BufferedWriter(new FileWriter("RAID6\\RAID6_1\\Third1" +  archivoOriginal.getNombre() ));
            for (int i = 0; i < this.archivo1.size(); i++) {
                out.write(this.archivo1.get(i));
                out.newLine();
            }
            out.close();
        } catch (IOException e) {}
        try {
            BufferedWriter out = new BufferedWriter(new FileWriter("RAID6\\RAID6_2\\Third2" +  archivoOriginal.getNombre() ));
            for (int i = 0; i < this.archivo2.size(); i++) {
                out.write(this.archivo2.get(i));
                out.newLine();
            }
            out.close();
        } catch (IOException e) {}
        try {
            BufferedWriter out = new BufferedWriter(new FileWriter("RAID6\\RAID6_3\\FullFile" +  archivoOriginal.getNombre() ));
            for (int i = 0; i < this.archivo3.size(); i++) {
                out.write(this.archivo3.get(i));
                out.newLine();
            }
            out.close();
        } catch (IOException e) {}
        try {
            BufferedWriter out = new BufferedWriter(new FileWriter("RAID6\\RAID6_4\\Third3" +  archivoOriginal.getNombre() ));
            for (int i = 0; i < this.archivo4.size(); i++) {
                out.write(this.archivo4.get(i));
                out.newLine();
            }
            out.close();
        } catch (IOException e) {}
    }
    
    private void enviarAlDisco4() {
        for (int i = 0; i < this.archivoOriginal.size(); i++) {
            String linea = this.archivoOriginal.get(i);
            this.agregarLineaArchivo4(linea);
        }
        
        int cont = 0;
        int limite = this.archivoOriginal.size()/3;
        while(cont<limite){
            String linea = this.archivoOriginal.get(cont);
            this.agregarLineaArchivo1(linea);
            cont++;
        }
        while(cont<(limite+limite)){
            String linea = this.archivoOriginal.get(cont);
            this.agregarLineaArchivo2(linea);
            cont++;
        }
        while(cont<this.archivoOriginal.size()){
            String linea = this.archivoOriginal.get(cont);
            this.agregarLineaArchivo3(linea);
            cont++;
        }
    }

    private void CopiarAlDiscoDuro4() {
        try {
            BufferedWriter out = new BufferedWriter(new FileWriter("RAID6\\RAID6_1\\Third1" +  archivoOriginal.getNombre() ));
            for (int i = 0; i < this.archivo1.size(); i++) {
                out.write(this.archivo1.get(i));
                out.newLine();
            }
            out.close();
        } catch (IOException e) {}
        try {
            BufferedWriter out = new BufferedWriter(new FileWriter("RAID6\\RAID6_2\\Third2" +  archivoOriginal.getNombre() ));
            for (int i = 0; i < this.archivo2.size(); i++) {
                out.write(this.archivo2.get(i));
                out.newLine();
            }
            out.close();
        } catch (IOException e) {}
        try {
            BufferedWriter out = new BufferedWriter(new FileWriter("RAID6\\RAID6_3\\Third3" +  archivoOriginal.getNombre() ));
            for (int i = 0; i < this.archivo3.size(); i++) {
                out.write(this.archivo3.get(i));
                out.newLine();
            }
            out.close();
        } catch (IOException e) {}
        try {
            BufferedWriter out = new BufferedWriter(new FileWriter("RAID6\\RAID6_4\\FullFile" +  archivoOriginal.getNombre() ));
            for (int i = 0; i < this.archivo4.size(); i++) {
                out.write(this.archivo4.get(i));
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
}
