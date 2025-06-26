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
public class Marcador implements Serializable{
    
    private int minas_J1, minas_J2;
    
    public Marcador(){
        minas_J1=0;
        minas_J2=0;
    }
    
    public void addMinaJ1(){  //Añade una mina al jugador 1.
        minas_J1++;
    }
    
    public void addMinaJ2(){  //Añade una mina al jugador 2.
        minas_J2++;
    }
    
    public int getMinasJ1(){  //Devuelve el numero de minas encontradas por jugador 1.
        return minas_J1;
    }
    
    public int getMinasJ2(){  //Devuelve el numero de minas encontradas por jugador 2.
        return minas_J2;
    }
    
    public void setMarcador(int minasJ1, int minasJ2){  //Permite poner los valores del marcador directamente.
        //Usado por el almacen de partidas.
        this.minas_J1=minasJ1;
        this.minas_J2=minasJ2;
    }
    
    @Override
    public boolean equals(Object o){
        if(this==o) return true;
        if(o==null) return false;
        if(this.getClass()!=o.getClass()) return false;
        
        Marcador cast=(Marcador) o;
        return(cast.getMinasJ1()==this.getMinasJ1() && this.getMinasJ2()==cast.getMinasJ2());
    }
    
}
