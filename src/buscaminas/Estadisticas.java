/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package buscaminas;
import java.io.File;
import java.io.Serializable;

/**
 *
 * @author a.gonzalezro.2022
 */
public class Estadisticas implements Serializable{
    
    private int partidasJugadas, partidasGanadas, partidasPerdidas, minas_a_favor, minas_en_contra;
    
    public Estadisticas(int ganadas, int perdidas, int jugadas, int minasFavor, int minasContra){  //Constructor de las estadisticas del jugador usado por la clase cara a cara..
        this.partidasGanadas=ganadas;
        this.partidasPerdidas=perdidas;
        this.partidasJugadas=jugadas;
        this.minas_a_favor=minasFavor;
        this.minas_en_contra=minasContra;
    }
    
    public Estadisticas(){  //Constructor sin argumentos.
        this.partidasJugadas=0;
        this.partidasGanadas=0;
        this.partidasPerdidas=0;
        this.minas_a_favor=0;
        this.minas_en_contra=0;
    }
    
    public void editarEstadisticas(boolean gana, int minasEncontradas, int minasRival){  //Constructor general.
        if(gana==true){
            this.partidasGanadas++;
            this.minas_a_favor+=minasEncontradas;
            this.minas_en_contra+=minasRival;
        }else{
            this.partidasPerdidas++;
            this.minas_a_favor+=minasEncontradas;
            this.minas_en_contra+=minasRival;
        }
        this.partidasJugadas++;
    }
    
    public int getJugadas(){  //Devuelve el número de partidas jugadas.
        return this.partidasJugadas;
    }
    
    public int getGanadas(){  //Devuelve el número de partidas ganadas.
        return this.partidasGanadas;
    }
    
    public int getPerdidas(){  //Devuelve el número de partidas perdidas.
        return this.partidasPerdidas;
    }
    
    public int getMinasContra(){  //Devuelve el número de minas en contra.
        return this.minas_en_contra;
    }
    
    public int getMinasFavor(){  //Devuelve el número de minas a favor.
        return this.minas_a_favor;
    }
    
    public void setMinasContra(int minasContra){  //Guarda el número de minas en contra.
        this.minas_en_contra=minasContra;
    }
    
    public void setMinasFavor(int minasFavor){  //Guarda el número de minas a favor.
        this.minas_en_contra=minasFavor;
    }
    
    public String printEstadisticas(){
        return ("Partidas jugadas: " + this.getJugadas() + "\n" +
                "Partidas ganadas: " + this.getGanadas() + "\n" + 
                "Partidas perdidas: " + this.getPerdidas() + "\n" +
                "Minas a favor: " + this.getMinasFavor() + "\n" +
                "Minas en contra: " + this.getMinasContra() + "\n");
    }
    
}
