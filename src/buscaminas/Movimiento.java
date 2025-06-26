/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package buscaminas;
import java.io.Serializable;

/**
 *
 * @author a.gonzalezro.2022
 */
public class Movimiento implements Serializable{
    
    private String nombreJugador;
    private int abscisas, ordenadas;
    
    public Movimiento(String nombre, int cX, int cY){  //Construye el movimiento con el nombre y coordenadas.
        this.nombreJugador=nombre;
        this.abscisas=cX;
        this.ordenadas=cY;
    }
    
    public String imprimirMovimiento(){  //Devuelve un String con el movimiento.
        return (this.nombreJugador + " " + Integer.toString(this.abscisas) + " " + Integer.toString(this.ordenadas));
    }
    
    @Override
    public boolean equals(Object o){
        if(this==o) return true;
        if(o==null) return false;
        if(this.getClass()!=o.getClass()) return false;
        
        Movimiento cast=(Movimiento) o;
        return(this.nombreJugador.equals(cast.nombreJugador) && this.abscisas==cast.abscisas && this.ordenadas==cast.ordenadas);
    }
    
}
