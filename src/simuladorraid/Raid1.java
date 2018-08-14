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
public class Raid1 {
    private ArrayList<String> archivo1;
    private ArrayList<String> archivo2;
    private Archivo archivoOriginal;

    public Raid1(Archivo archivoOriginal) {
        this.archivo1 = new ArrayList<>();
        this.archivo2 = new ArrayList<>();
        this.archivoOriginal = archivoOriginal;
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
        BufferedWriter out = new BufferedWriter(new FileWriter("RAID1\\RAID1_1\\mirror1" + archivoOriginal.getNombre() + ".txt" ));
            for (int i = 0; i < archivo1.size(); i++) {
                out.write(archivo1.get(i));
                out.newLine();
            }
            out.close();
        } catch (IOException e) {}
        try {
        BufferedWriter out = new BufferedWriter(new FileWriter("RAID2\\RAID1_2\\mirror2" +  archivoOriginal.getNombre() + ".txt" ));
            for (int i = 0; i < archivo2.size(); i++) {
                out.write(archivo2.get(i));
                out.newLine();
            }
            out.close();
        } catch (IOException e) {}
    }
    
    
}