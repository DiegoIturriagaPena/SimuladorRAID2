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
import javafx.scene.control.Alert;

/**
 *
 * @author Diego Iturriaga
 */
public class Raid6 {
    private ArrayList<String> archivo1;
    private ArrayList<String> archivo2;
    private ArrayList<String> archivo3;
    private ArrayList<String> archivo4;
    private ArrayList<String> archivo5;
    private ArrayList<String> paridad1;
    private ArrayList<String> paridad2;
    private Archivo archivoOriginal;

    public Raid6(Archivo archivoOriginal) {
        this.archivo1 = new ArrayList<>();
        this.archivo2 = new ArrayList<>();
        this.archivo3 = new ArrayList<>();
        this.archivo4 = new ArrayList<>();
        this.archivo5 = new ArrayList<>();
        this.paridad1 = new ArrayList<>();
        this.paridad2 = new ArrayList<>();
        this.archivoOriginal = archivoOriginal;
    }
    
    public Raid6(){
        
    }
    
    public void procesarArchivoRaid6(){
        Random r = new Random();
        int numeroDisco = r.nextInt(4)+1;
        switch (numeroDisco) {
            case 1:
                this.enviarAlDisco1Disco2();
                this.CopiarAlDiscoDuro12();
                break;
            case 2:
                this.enviarAlDisco2Disco3();
                this.CopiarAlDiscoDuro23();
                break;
            case 3:
                this.enviarAlDisco3Disco4();
                this.CopiarAlDiscoDuro34();
                break;
            case 4:
                this.enviarAlDisco4Disco5();
                this.CopiarAlDiscoDuro45();
                break;
        }
    }

    private void enviarAlDisco1Disco2() {
        for (int i = 0; i < this.archivoOriginal.size(); i++) {
            String linea = this.archivoOriginal.get(i);
            this.agregarLineaArchivo1(linea);
            if(i<(this.archivoOriginal.size()/2)){
                this.agregarParidad("0");
            }
            else{
                this.agregarParidad2("0");
            }
        }
        
        int cont = 0;
        int limite = this.archivoOriginal.size()/3;
        while(cont<limite){
            String linea = this.archivoOriginal.get(cont);
            this.agregarLineaArchivo3(linea);
            cont++;
        }
        while(cont<(limite+limite)){
            String linea = this.archivoOriginal.get(cont);
            this.agregarLineaArchivo4(linea);
            cont++;
        }
        while(cont<this.archivoOriginal.size()){
            String linea = this.archivoOriginal.get(cont);
            this.agregarLineaArchivo5(linea);
            cont++;
        }
    }

    private void CopiarAlDiscoDuro12() {
        try {
            BufferedWriter out = new BufferedWriter(new FileWriter("RAID6\\RAID6_1\\Paridad1" +  archivoOriginal.getNombre() + ".txt"));
            for (int i = 0; i < this.paridad1.size(); i++) {
                out.write(this.paridad1.get(i));
                out.newLine();
            }
            out.close();
        } catch (IOException e) {}
        try {
            BufferedWriter out = new BufferedWriter(new FileWriter("RAID6\\RAID6_2\\Paridad2" +  archivoOriginal.getNombre() + ".txt"));
            for (int i = 0; i < this.paridad2.size(); i++) {
                out.write(this.paridad2.get(i));
                out.newLine();
            }
            out.close();
        } catch (IOException e) {}
        try {
            BufferedWriter out = new BufferedWriter(new FileWriter("RAID6\\RAID6_3\\Third1" +  archivoOriginal.getNombre() + ".txt"));
            for (int i = 0; i < this.archivo3.size(); i++) {
                out.write(this.archivo3.get(i));
                out.newLine();
            }
            out.close();
        } catch (IOException e) {}
        try {
            BufferedWriter out = new BufferedWriter(new FileWriter("RAID6\\RAID6_4\\Third2" +  archivoOriginal.getNombre() + ".txt"));
            for (int i = 0; i < this.archivo4.size(); i++) {
                out.write(this.archivo4.get(i));
                out.newLine();
            }
            out.close();
        } catch (IOException e) {}
        try {
            BufferedWriter out = new BufferedWriter(new FileWriter("RAID6\\RAID6_5\\Third3" +  archivoOriginal.getNombre() + ".txt"));
            for (int i = 0; i < this.archivo5.size(); i++) {
                out.write(this.archivo5.get(i));
                out.newLine();
            }
            out.close();
        } catch (IOException e) {}
    }

    private void enviarAlDisco2Disco3() {
        for (int i = 0; i < this.archivoOriginal.size(); i++) {
            String linea = this.archivoOriginal.get(i);
            this.agregarLineaArchivo2(linea);
            if(i<(this.archivoOriginal.size()/2)){
                this.agregarParidad("0");
            }
            else{
                this.agregarParidad2("0");
            }
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
            this.agregarLineaArchivo4(linea);
            cont++;
        }
        while(cont<this.archivoOriginal.size()){
            String linea = this.archivoOriginal.get(cont);
            this.agregarLineaArchivo5(linea);
            cont++;
        }
    }

    private void CopiarAlDiscoDuro23() {
        try {
            BufferedWriter out = new BufferedWriter(new FileWriter("RAID6\\RAID6_1\\Third1" +  archivoOriginal.getNombre() + ".txt"));
            for (int i = 0; i < this.archivo1.size(); i++) {
                out.write(this.archivo1.get(i));
                out.newLine();
            }
            out.close();
        } catch (IOException e) {}
        try {
            BufferedWriter out = new BufferedWriter(new FileWriter("RAID6\\RAID6_2\\Paridad1" +  archivoOriginal.getNombre() + ".txt"));
            for (int i = 0; i < this.paridad1.size(); i++) {
                out.write(this.paridad1.get(i));
                out.newLine();
            }
            out.close();
        } catch (IOException e) {}
        try {
            BufferedWriter out = new BufferedWriter(new FileWriter("RAID6\\RAID6_3\\Paridad2" +  archivoOriginal.getNombre() + ".txt"));
            for (int i = 0; i < this.paridad2.size(); i++) {
                out.write(this.paridad2.get(i));
                out.newLine();
            }
            out.close();
        } catch (IOException e) {}
        try {
            BufferedWriter out = new BufferedWriter(new FileWriter("RAID6\\RAID6_4\\Third2" +  archivoOriginal.getNombre() + ".txt"));
            for (int i = 0; i < this.archivo4.size(); i++) {
                out.write(this.archivo4.get(i));
                out.newLine();
            }
            out.close();
        } catch (IOException e) {}
        try {
            BufferedWriter out = new BufferedWriter(new FileWriter("RAID6\\RAID6_5\\Third3" +  archivoOriginal.getNombre() + ".txt"));
            for (int i = 0; i < this.archivo5.size(); i++) {
                out.write(this.archivo5.get(i));
                out.newLine();
            }
            out.close();
        } catch (IOException e) {}
    }

    private void enviarAlDisco3Disco4() {
        for (int i = 0; i < this.archivoOriginal.size(); i++) {
            String linea = this.archivoOriginal.get(i);
            this.agregarLineaArchivo3(linea);
            if(i<(this.archivoOriginal.size()/2)){
                this.agregarParidad("0");
            }
            else{
                this.agregarParidad2("0");
            }
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
            this.agregarLineaArchivo5(linea);
            cont++;
        }
    }

    private void CopiarAlDiscoDuro34() {
        try {
            BufferedWriter out = new BufferedWriter(new FileWriter("RAID6\\RAID6_1\\Third1" +  archivoOriginal.getNombre() + ".txt"));
            for (int i = 0; i < this.archivo1.size(); i++) {
                out.write(this.archivo1.get(i));
                out.newLine();
            }
            out.close();
        } catch (IOException e) {}
        try {
            BufferedWriter out = new BufferedWriter(new FileWriter("RAID6\\RAID6_2\\Third2" +  archivoOriginal.getNombre() + ".txt"));
            for (int i = 0; i < this.archivo2.size(); i++) {
                out.write(this.archivo2.get(i));
                out.newLine();
            }
            out.close();
        } catch (IOException e) {}
        try {
            BufferedWriter out = new BufferedWriter(new FileWriter("RAID6\\RAID6_3\\Paridad1" +  archivoOriginal.getNombre() + ".txt"));
            for (int i = 0; i < this.paridad1.size(); i++) {
                out.write(this.paridad1.get(i));
                out.newLine();
            }
            out.close();
        } catch (IOException e) {}
        try {
            BufferedWriter out = new BufferedWriter(new FileWriter("RAID6\\RAID6_4\\Paridad2" +  archivoOriginal.getNombre() + ".txt"));
            for (int i = 0; i < this.paridad2.size(); i++) {
                out.write(this.paridad2.get(i));
                out.newLine();
            }
            out.close();
        } catch (IOException e) {}
        try {
            BufferedWriter out = new BufferedWriter(new FileWriter("RAID6\\RAID6_5\\Third3" +  archivoOriginal.getNombre() + ".txt"));
            for (int i = 0; i < this.archivo5.size(); i++) {
                out.write(this.archivo5.get(i));
                out.newLine();
            }
            out.close();
        } catch (IOException e) {}
    }
    
    private void enviarAlDisco4Disco5() {
        for (int i = 0; i < this.archivoOriginal.size(); i++) {
            String linea = this.archivoOriginal.get(i);
            this.agregarLineaArchivo4(linea);
            if(i<(this.archivoOriginal.size()/2)){
                this.agregarParidad("0");
            }
            else{
                this.agregarParidad2("0");
            }
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

    private void CopiarAlDiscoDuro45() {
        try {
            BufferedWriter out = new BufferedWriter(new FileWriter("RAID6\\RAID6_1\\Third1" +  archivoOriginal.getNombre() + ".txt"));
            for (int i = 0; i < this.archivo1.size(); i++) {
                out.write(this.archivo1.get(i));
                out.newLine();
            }
            out.close();
        } catch (IOException e) {}
        try {
            BufferedWriter out = new BufferedWriter(new FileWriter("RAID6\\RAID6_2\\Third2" +  archivoOriginal.getNombre() + ".txt"));
            for (int i = 0; i < this.archivo2.size(); i++) {
                out.write(this.archivo2.get(i));
                out.newLine();
            }
            out.close();
        } catch (IOException e) {}
        try {
            BufferedWriter out = new BufferedWriter(new FileWriter("RAID6\\RAID6_3\\Third3" +  archivoOriginal.getNombre() + ".txt"));
            for (int i = 0; i < this.archivo3.size(); i++) {
                out.write(this.archivo3.get(i));
                out.newLine();
            }
            out.close();
        } catch (IOException e) {}
        try {
            BufferedWriter out = new BufferedWriter(new FileWriter("RAID6\\RAID6_4\\Paridad1" +  archivoOriginal.getNombre() + ".txt"));
            for (int i = 0; i < this.paridad1.size(); i++) {
                out.write(this.paridad1.get(i));
                out.newLine();
            }
            out.close();
        } catch (IOException e) {}
        try {
            BufferedWriter out = new BufferedWriter(new FileWriter("RAID6\\RAID6_5\\Paridad2" +  archivoOriginal.getNombre() + ".txt"));
            for (int i = 0; i < this.paridad2.size(); i++) {
                out.write(this.paridad2.get(i));
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
    
    public int cantidadLineasArchivo5() {
        return archivo5.size();
    }

    public String obtenerLineaArchivo5(int index) {
        return archivo5.get(index);
    }

    public boolean agregarLineaArchivo5(String e) {
        return archivo5.add(e);
    }

    public String eliminarLineaArchivo5(int index) {
        return archivo5.remove(index);
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
    
    private void sobreescribirParidad(Archivo archivo, ArrayList<String> paridad, int numeroDisco, int numero) {
        try {
            BufferedWriter out = new BufferedWriter(new FileWriter("RAID6\\RAID6_"+numeroDisco+"\\Paridad"+ numero +  archivo.getNombre() + ".txt"));
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
        String textoReconstruido = "";
        ArrayList<String> third1 = new ArrayList<>();
        ArrayList<String> third2 = new ArrayList<>();
        ArrayList<String> third3 = new ArrayList<>();
        ArrayList<String> paridad1D = new ArrayList<>();
        ArrayList<String> paridad2D = new ArrayList<>();
        ArrayList<String> archivoOriginalA = this.obtenerLineasArchivo(archivo);
        int disco1 = 0;
        int disco2 = 0;
        try{
            third1 = buscarArchivo("Third1"+archivo.getNombre());
            third2 = buscarArchivo("Third2"+archivo.getNombre());
            third3 = buscarArchivo("Third3"+archivo.getNombre());
            paridad1D = buscarArchivo("Paridad1"+archivo.getNombre());
            paridad2D = buscarArchivo("Paridad2"+archivo.getNombre());
            disco1 = buscarDisco("Paridad1"+archivo.getNombre());
            disco2 = buscarDisco("Paridad2"+archivo.getNombre());
            //fullFile = buscarArchivo("FullFile"+archivo.getNombre());
            
            int cont = 0;
            int cont1 = 0;
            while(cont<archivoOriginalA.size()){
                for (int i = 0; i < third1.size(); i++) {
                    if(!archivoOriginalA.get(cont).equals(third1.get(i))){
                        paridad1D.remove(i);
                        paridad1D.add(i, "1");
                    }
                    textoReconstruido = textoReconstruido + third1.get(i)+"\n";
                    cont++;
                }
                
                for (int i = 0; i < third2.size(); i++) {
                    if(!archivoOriginalA.get(cont).equals(third2.get(i))){
                        if(cont<archivoOriginalA.size()){
                            paridad1D.remove(cont);
                            paridad1D.add(cont, "1");
                        }
                        else{
                            paridad2D.remove(cont1);
                            paridad2D.add(cont1, "1");
                        }
                    }
                    textoReconstruido = textoReconstruido + third2.get(i)+"\n";
                    cont++;
                    cont1++;
                }
                
                for (int i = 0; i < third3.size(); i++) {
                    if(!archivoOriginalA.get(cont).equals(third3.get(i))){
                        paridad2D.remove(cont1);
                        paridad2D.add(cont1, "1");
                    }
                    textoReconstruido = textoReconstruido + third3.get(i)+"\n";
                    cont++;
                    cont1++;
                }
            }
            if(this.verificarParidad(paridad1D) && this.verificarParidad(paridad2D) && cont==archivoOriginalA.size()){
                return textoReconstruido;
            }
            else{
                this.sobreescribirParidad(archivo, paridad1D, disco1, 1);
                this.sobreescribirParidad(archivo, paridad2D, disco2, 2);
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
            archivo = this.abrirArchivo("RAID6_1\\"+nombre+".txt");
        }
        catch(NullPointerException e){
            try{
                archivo = this.abrirArchivo("RAID6_2\\"+nombre+".txt");
            }
            catch (NullPointerException e1){
                try{
                    archivo = this.abrirArchivo("RAID6_3\\"+nombre+".txt");
                }
                catch(NullPointerException e2){
                    try{
                        archivo = this.abrirArchivo("RAID6_4\\"+nombre+".txt");
                    }
                    catch(NullPointerException e3){
                        try{
                            archivo = this.abrirArchivo("RAID6_5\\"+nombre+".txt");
                        }
                        catch(NullPointerException e4){
                            Alert alert = new Alert(Alert.AlertType.ERROR);
                            alert.setTitle("Error");
                            alert.setHeaderText("Error");
                            alert.setContentText("Archivo "+ nombre +".txt esta corrupto o fue eliminado.");
                            alert.showAndWait();
                        }
                    }
                }
            }
        }
        return archivo;
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
                archivo = this.abrirArchivo("RAID6_2\\"+nombre+".txt");
                disco = 2;
            }
            catch (NullPointerException e1){
                try{
                    archivo = this.abrirArchivo("RAID6_3\\"+nombre+".txt");
                    disco = 3;
                }
                catch(NullPointerException e2){
                    try{
                        archivo = this.abrirArchivo("RAID6_4\\"+nombre+".txt");
                        disco = 4;
                    }
                    catch(NullPointerException e3){
                        try{
                            archivo = this.abrirArchivo("RAID6_5\\"+nombre+".txt");
                            disco = 5;
                        }
                        catch(NullPointerException e4){
                            Alert alert = new Alert(Alert.AlertType.ERROR);
                            alert.setTitle("Error");
                            alert.setHeaderText("Error");
                            alert.setContentText("Archivo "+ nombre +".txt esta corrupto o fue eliminado.");
                            alert.showAndWait();
                        }
                    }
                }
            }
        }
        return disco;
    }
    
    private ArrayList<String> abrirArchivo(String nombre)
    {
        ArrayList<String> lineas = new ArrayList<>();
        File file = new File ("RAID6\\" +nombre);
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
