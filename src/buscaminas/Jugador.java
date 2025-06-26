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
public class Jugador extends Usuario implements Serializable{
    
    private Estadisticas info=new Estadisticas();
    private ArrayList<Partida> listaPartidasJugadas=new ArrayList<Partida>();
    private Cara_a_cara comparativa;

    public Jugador(String pseudonimo, String clave) {
        super(pseudonimo, clave);
        listaPartidasJugadas=new ArrayList<Partida>();
    }    
    
    public ArrayList<Partida> getPartidasJugadas(){  //Devuelve la lista de partidas del jugador.
        return this.listaPartidasJugadas;
    }
    
    public void actualizarEstadisticas(Partida p, int minasEncontradas, int minasRival){  //Actualiza las estadísticas del jugador.
        this.getEstadisticas().editarEstadisticas(this.equals(p.getGanador()), minasEncontradas, minasRival);
    }
    
    public Estadisticas getEstadisticas(){  //Devuelve las estadísticas del jugador.
        return this.info;
    }
    
    public String getUsuario(){  //Devuelve el nombre del jugador.
        return this.getNombre();
    }
    
    public String getContraseña(){  //Devuelve la contraseña del jugador.
        return this.getClave();
    }
    
    public void añadirPartida(Partida nuevaPartida){  //Añade una partida a la lista.
        this.listaPartidasJugadas.add(nuevaPartida);
    }
    
    @Override
    public boolean equals(Object o){  //Compara dos jugadores y devuelve true si son iguales.
        if(o==this) return true;
        if(o==null) return false;
        if(o.getClass()!=this.getClass()) return false;
        
        Jugador aux=(Jugador) o;
        return(aux.getContraseña().equals(this.getContraseña()) && aux.getUsuario().equals(this.getUsuario()));
    }
    
    public String verPerfil(){  //Muestra el nombre y sus estadisticas.
        return("Nombre: " + this.getUsuario() + "\n" + 
                "Estadisticas: \n" + this.getEstadisticas().printEstadisticas());
    }
    
    public void crearComparativa(Jugador j2){  //Crea una variable para guardar los resultados de la comparación.
        comparativa=new Cara_a_cara(this, j2);
    }
    
    public Cara_a_cara getComparativa(){
        return this.comparativa;
    }    
    
    public boolean partidaRepetida(Partida p){
        boolean resultado=false;
        for(Partida par : this.listaPartidasJugadas){
            if(par.equals(p)){  //Si la partida ya está guardada en la lista del jugador.
                resultado=true;  //Cambia el resultado a true.
            }
        }
        return resultado;
    }
    
}
