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
public class Cara_a_cara{    
    
    private ArrayList<Partida> enfrentamientos=new ArrayList<Partida>();
    private final Jugador actual, rival;
    Estadisticas infoEncuentros;
    
    public Cara_a_cara(Jugador actual, Jugador rival){  //Construye la clase y rellena los campos de jugadores.
        this.actual=actual;
        this.rival=rival; 
        rellenarEnfrentamientos();        
    }
    
    private void rellenarEnfrentamientos(){  //Rellena la lista de partidas jugadas entre ambos jugadores.
        int contadorVictorias=0;
        int minasJ1=0, minasJ2=0;
        for(Partida p : this.actual.getPartidasJugadas()){  //Recorre las partidas jugadas para encontrar en las que participan ambos.
            if((p.getJugadorActual().equals(this.actual) || p.getJugadorActual().equals(this.rival)) && (p.getJugadorEsperando().equals(this.actual) || p.getJugadorEsperando().equals(this.rival))){
                this.enfrentamientos.add(p);
                if(p.getGanador().equals(this.actual)){  //Si el jugador actual fue el ganador de la partida se le suma.
                    contadorVictorias++;
                    minasJ1+=p.getMarcador().getMinasJ1();
                    minasJ2+=p.getMarcador().getMinasJ2();
                }else{  //Si el ganador fue el rival.
                    minasJ1+=p.getMarcador().getMinasJ2();
                    minasJ2+=p.getMarcador().getMinasJ1();
                }
            }
        }
        this.infoEncuentros=new Estadisticas(contadorVictorias, this.enfrentamientos.size()-contadorVictorias, this.enfrentamientos.size(), minasJ1, minasJ2);
    }
        
    public String compararJugadores(){  //Compara las estadisticas del jugador actual con otro dado.
        String comparacion=("Partidas jugadas entre ambos: " + this.infoEncuentros.getJugadas() + "\n" + 
                "Victorias obtenidas por " + this.actual.getUsuario() + ": " + this.infoEncuentros.getGanadas() + ".\n" + 
                "Victorias obtenidas por " + this.rival.getUsuario() + ": " + this.infoEncuentros.getPerdidas() + ".\n" + 
                "Minas totales a favor de " + this.actual.getUsuario() + ": " + this.infoEncuentros.getMinasFavor() + ".\n" + 
                "y en contra: " + this.infoEncuentros.getMinasContra() + ".\n");
        int i=1;
        for(Partida p : this.enfrentamientos){  //Recorre las partidas disputadas entre ambos jugadores.
            comparacion+=("Partida " + i + ": \n" + "El ganador fue: " + p.getGanador().getUsuario() + "\n" + 
                    "y su rival consiguio: ");
            if(p.getMarcador().getMinasJ1()==26){  //Si el ganador fue J1.
                comparacion+=(p.getMarcador().getMinasJ2() + " minas.\n");
            }else{
                comparacion+=(p.getMarcador().getMinasJ1() + " minas.\n");
            }
            i++;
        }
        
        return comparacion;
    }    
    
}
