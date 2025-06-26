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
public class Casilla implements Serializable{ //Guarda la información del estado actual de la casilla.
    
    private int coord_X, coord_Y;
    //Describen las coordenadas de la casilla.
    private String contenido;
    //Guarda el valor que almacena la casilla (* mina, numero: minas cerca).
    
    public Casilla(int cX, int cY){  //Construye la casilla y asigna uss valores de coordenadas y contenido.
        this.coord_X=cX;
        this.coord_Y=cY;
        this.contenido="0";
    }
    
    public int getX(){  //Devuelve la coordenada X.
        return this.coord_X;
    }
    
    public int getY(){  //Devuelve el valor de la coordenada Y.
        return this.coord_Y;
    }
    
    public String getContenido(){  //Devuelve el valor guardado en la casilla.
        return this.contenido;
    }
    
    public void setContenido(String simbolo){  //Cambia el valor contenido en la casilla.
        this.contenido=simbolo;
    }
    
    @Override
    public boolean equals(Object o){  //Redefine el equals para las casillas.
        if(o==this) return true;
        if(o==null) return false;
        if(o.getClass()!=this.getClass()) return false;
        
        Casilla aux=(Casilla) o;
        return(this.getX()==aux.getX() && this.getY()==aux.getY() && this.getContenido().equals(aux.getContenido()));
    }
    
}
