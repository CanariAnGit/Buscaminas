/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package buscaminas;
import java.util.ArrayList;
import java.io.Serializable;

/**
 *
 * @author a.gonzalezro.2022
 */
public class Tablero implements Serializable{
    
    private static final int NUM_MINAS=51;
    //Guarda el número de minas que tendrá el tablero.
    private Casilla matrizTablero[][];
    //Almacena las casillas del tablero.
    
    public Tablero(){  //Construye el tablero para la partida creando las casillas.
        this.matrizTablero=new Casilla[16][16];
        this.iniciarTablero();
        this.rellenarMinas();
        this.rellenarAdyacentes();
    }
    
    private void iniciarTablero(){ //Rellena las casillas del tablero con valor de inicio.
        for(int fila=0; fila<16; fila++){ 
            for(int columna=0; columna<16; columna++){
                this.matrizTablero[fila][columna]=new Casilla(fila,columna);
            }
        } 
    }
    
    public Casilla[][] getTablero(){  //Devuelve la matriz del tablero.
        return this.matrizTablero;
    }
    
    public Casilla getCasilla(int cX, int cY){  //Devuelve una casilla.
        return this.matrizTablero[cX][cY];
    }
    
    private void rellenarMinas(){
        
        int minas=NUM_MINAS;
        
        while(minas>0){  //Coloca las minas en posiciones aleatorias dentro del tablero.
            
            int filaAleatoria=(int) (Math.random() * 16);
            int columnaAleatoria=(int) (Math.random() * 16);
            //Genera una dirección aleatoria donde introducir la mina.
            Casilla actualCasilla=this.getCasilla(filaAleatoria, columnaAleatoria);
            
            if(actualCasilla.getContenido().equals("*")==false){  
                //Si la casilla aun no tiene mina se le coloca.
                this.matrizTablero[filaAleatoria][columnaAleatoria].setContenido("*");
                minas--;  //Actualizar el número de minas restantes.
            }            
            
        }
        
    }
    
    private void rellenarAdyacentes(){  //Coloca un número en cada casilla referente al número de minas que tiene alrededor.
        
        int contador;
        //Contará el número de minas que hay alrededor de una casilla.
        
        for(int filas=0; filas<16; filas++){  //Recorre las filas.
            for(int columnas=0; columnas<16; columnas++){  //Recorre las columnas. 
                
                Casilla casillaActual=this.getCasilla(filas, columnas);
                
                if(casillaActual.getContenido().equals("*")==false){  //Si la casilla recorrida no tiene mina.
                    ArrayList<Casilla> adyacentes=this.casillasAdyacentes(casillaActual);
                    contador=0;

                    for(Casilla c: adyacentes){  //Recorre la lista contando el número de minas alrededor.
                    if(c.getContenido().equals("*")) contador++;
                    }
                    String contenido=String.valueOf(contador);
                    casillaActual.setContenido(contenido);
                }                
                
            }
        }      
        
    }
    
    public ArrayList<Casilla> casillasAdyacentes(Casilla actualCasilla){  //Devuelve un array con las casillas adyacentes a una dada.
        
        ArrayList<Casilla> adyacentes=new ArrayList();  //Guarda las casillas adyacentes.
        int fila=actualCasilla.getX();
        int columna=actualCasilla.getY();
        
        for(int i=-1; i<=1; i++){
            for(int j=-1; j<=1; j++){
                if(((fila+i)<16 && (fila+i)>=0) && ((columna+j)<16 && (columna+j)>=0) && (i!=0 || j!=0)){ 
                    //La casilla está dentro del tablero.
                    adyacentes.add(this.getCasilla((fila+i),(columna+j)));
                }
            }
        }
        
        return adyacentes;
        
    }
    
    public boolean hayMina(int cx, int cy){  //Devuelve true si en la posición seleccionada hay una mina.
        return (this.getCasilla(cx, cy).getContenido().equals("*"));
    }
    
}
