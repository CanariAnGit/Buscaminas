/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package buscaminas;
import java.util.ArrayList;
import java.awt.Color;
import java.io.Serializable;


/**
 *
 * @author a.gonzalezro.2022
 */
public class Partida implements Serializable{
    
    private Jugador jugador_actual, jugador_esperando, ganador;
    private final Tablero zonadejuego=new Tablero();
    private Marcador marcador=new Marcador();
    private static Almacen_Partidas almacen=new Almacen_Partidas();
    private ArrayList<Movimiento> movimientos=new ArrayList<Movimiento>();
    private transient int posX, posY;
    private transient boolean jugador1;  //Sirve para indentificar quien está jugando y quien gana.
    //Recogen las posiciones de la casilla seleccionada por el jugador.
    private transient Color colorCasilla=Color.RED;
    //Utilizado para cambiar el color correspondiente al primer y segundo jugador en cada turno.
    
    public Partida(Jugador primero, Jugador segundo){  //Construye la partida, preparando el tablero y los jugadores.
        this.jugador_actual=primero;
        this.jugador_esperando=segundo; 
        this.jugador1=true;
    }
    
    public void setMovimiento(int X, int Y){  //Coloca las coordenadas de la casilla seleccionada.
        Movimiento nuevoMovimiento=new Movimiento(jugador_actual.getUsuario(), X, Y);
        this.movimientos.add(nuevoMovimiento);
        this.posX=X;
        this.posY=Y;
    }
    
    public Tablero getTablero(){
        return this.zonadejuego;
    }
    
    public Color getColorCasilla(){
        return this.colorCasilla;
    }
    
    public void setColorCasilla(Color nuevoColor){
        this.colorCasilla=nuevoColor;
    }
    
    public ArrayList<Movimiento> getMovimientos(){
        return this.movimientos;
    }
    
    public Jugador getJugadorActual(){  //Devuelve el jugador actual.
        return this.jugador_actual;
    }
    
    public Jugador getJugadorEsperando(){  //Devuelve el jugador esperando.
        return this.jugador_esperando;
    }
    
    public void setJugadorActual(Jugador nuevoActual){  //Cambia el jugador actual por otro dado.
        this.jugador_actual=nuevoActual;
    }
    
    public void setJugadorEsperando(Jugador nuevoEsperando){  //Cambia el juegador esperando por otro dado.
        this.jugador_esperando=nuevoEsperando;
    }
    
    public Jugador getGanador(){  //Devuelve el jugador que ha ganado la partida.
        return this.ganador;
    }
    
    public void setGanador(Jugador victorioso){  //Devuelve el jugador que ha ganado la partida.
        this.ganador=victorioso;
    }
    
    public String imprimirTablero(){
        String matriz="";
        String fila="";
        for(int i=0; i<16; i++){  //Recorre las filas.
            for(int j=0; j<16; j++){  //Recorre las columnas.
                fila+=(zonadejuego.getCasilla(i, j).getContenido() + " ");
            }
            matriz+=(fila+"\n");  //Añade el contenido de la fila a la matriz conjunto.
            fila="";  //Resetea la fila para no acumularlas.
        }
        
        return matriz;
    }
    
    public void info_partida(){  //Devuelve la información de la partida seleccionada.
        almacen.info_partida(this);
    }
    
    private void cambiarTurno(){  //Cambia los punteros de los jugadores para que el que esperaba pase a ser el actual.        
        Jugador aux=this.jugador_actual;
        this.setJugadorActual(this.getJugadorEsperando());
        this.setJugadorEsperando(aux);
        this.jugador1=!this.jugador1;  //Cambia el valor que dice si el jugador actual es el 1.
        if(this.getColorCasilla().equals(Color.BLUE)){  //Si el color actual es azul.
            this.setColorCasilla(Color.RED);
        }else{
            this.setColorCasilla(Color.BLUE);
        }
    }
    
    public void jugar(){  //Lleva a cabo la ejecución de la partida.        
        if(this.zonadejuego.hayMina(this.posX,this.posY)){  //Si hay mina en la posición seleccionada, entonces actualiza el marcador.
            if(this.jugador1==true){  //Si el jugador actual es el J1 actualiza su marcador.
                this.marcador.addMinaJ1();
            }else{
                this.marcador.addMinaJ2();
            }
        }else{  //Si no hay mina se comprobarán las casillas adyacentes a la seleccionada.
            this.cambiarTurno();  //Cambiamos el turno.
        }            
    }
    
    public boolean gameOver(){
        if(marcador.getMinasJ1()==26 || marcador.getMinasJ2()==26){
            this.ganador=this.jugador_actual;
            return true;
        }else{
            return false;
        }
    }
    
    public Almacen_Partidas getAlmacenPartidas(){
        return this.almacen;
    }
    
    public Marcador getMarcador(){
        return this.marcador;
    }
    
    public String printMovimientos(){
        String movimientos="";
        for(Movimiento m : this.getMovimientos()){
            movimientos+=(m.imprimirMovimiento() + "\n");
        }
        return movimientos;
    }
    
    @Override
    public boolean equals(Object o){  //Compara dos partidas.
        if(o==this) return true;
        if(o==null) return false;
        if(o.getClass()!=this.getClass()) return false;
        
        Partida aux=(Partida) o;
        return((this.jugador_actual.equals(aux.jugador_actual) || this.jugador_actual.equals(aux.jugador_esperando)) && (this.jugador_esperando.equals(aux.jugador_actual) || this.jugador_esperando.equals(aux.jugador_esperando)) && this.getMarcador().equals(aux.getMarcador()) && this.getMovimientos().containsAll(aux.getMovimientos()));
    }
    
    @Override
    public String toString(){  //Redescribe el método para obtener nombres de las partidas.
        return(this.getJugadorActual().getUsuario() + "vs" + this.getJugadorEsperando().getUsuario());
    }
    
    
}
