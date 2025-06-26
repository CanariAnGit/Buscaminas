/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package buscaminas;
import java.io.File;

/**
 *
 * @author a.gonzalezro.2022
 */
public class DatosCompartidosVentanas {
    
    private static final Administrador admin=new Administrador();  
    private static Almacen_Usuarios usuarios=new Almacen_Usuarios();  //Recibe el almacen de usuarios.
    private static Jugador aux, J1, J2;
    private Partida partida;
    private File ficheroUsuarios=new File("usuarios.ser");
    private File ficheroNuevosUsuarios=new File("nuevosUsuarios.txt");
    private File ficheroPartidas=new File("partidas.ser");
    private static Almacen_Partidas listaJuegos=new Almacen_Partidas();  //Contendrá todas las partidas que se han jugado.
    private File ficheroTableroMovimientos=new File("tableroymovimientos.txt");    
    
    public File getFicheroTableroMovimientos(){
        return this.ficheroTableroMovimientos;
    }
    public Administrador getAdmin(){
        return this.admin;
    }
    
    public File getFicheroNuevosUsuarios(){
        return this.ficheroNuevosUsuarios;
    }
    
    public File getFicheroUsuarios(){
        return this.ficheroUsuarios;
    }
    
    public File getFicheroPartidas(){
        return this.ficheroPartidas;
    }
    
    public Almacen_Usuarios getUsuarios(){
        return this.usuarios;
    }
    
    public void setJugadorAux(Jugador j){
        this.aux=j;
    }
    
    /*
    public void agregarInfoJugador(Jugador j){  //Busca al jugador en la lista importada y le añade la información de partidas.
        for(Partida p : this.getAlmacenPartidas().getListaPartida()){
            if(p.getJugadorActual().equals(j) || p.getJugadorEsperando().equals(j)){  //Si el jugador participa en la partida.
                j.añadirPartida(partida);
            }
        }        
        for(Partida p : j.getPartidasJugadas()){
            Jugador actual=p.getJugadorActual();
            if(j.equals(actual)){
                j.actualizarEstadisticas(p, p.getMarcador().getMinasJ1(), p.getMarcador().getMinasJ2());
            }else{
                j.actualizarEstadisticas(p, p.getMarcador().getMinasJ2(), p.getMarcador().getMinasJ1());
            }
            
        }
    }
    */
    
    public void organizarPartidas(){  //Reparte las partidas de la lista a cada usuario.
        for(Partida p : this.getAlmacenPartidas().getListaPartida()){  //Recorre la lista de partidas.
            Jugador jugador1=p.getJugadorActual();
            Jugador jugador2=p.getJugadorEsperando();
            for(Usuario u : this.getUsuarios().getListaUsuarios()){  //Recorre los usuarios.
                Jugador j=(Jugador) u;
                if((j.equals(jugador1) || j.equals(jugador2)) && j.partidaRepetida(p)==false){  
                    //Si el usuario coincide con alguno de los jugadores y la partida no está ya guardada en su lista.
                    j.añadirPartida(p);  //Se le añade la partida a su lista.
                    Jugador actual=p.getJugadorActual();
                    if(j.equals(actual)){
                        j.actualizarEstadisticas(p, p.getMarcador().getMinasJ1(), p.getMarcador().getMinasJ2());
                    }else{
                        j.actualizarEstadisticas(p, p.getMarcador().getMinasJ2(), p.getMarcador().getMinasJ1());
                    }
                }
            }
        }
    }
    
    public void rellenarResultadoPartida(){
        Partida p=this.getPartida();
        Marcador puntuacion=p.getMarcador();
        this.getJ1().actualizarEstadisticas(p, puntuacion.getMinasJ1(), puntuacion.getMinasJ2());
        this.getJ2().actualizarEstadisticas(p, puntuacion.getMinasJ2(), puntuacion.getMinasJ1());
        this.getJ1().añadirPartida(p);
        this.getJ2().añadirPartida(p);  //Añade la partida actual a ambos jugadores.        
        this.getAlmacenPartidas().getListaPartida().add(p);  //Añade la partida a la lista.
    }
    
    public Almacen_Partidas getAlmacenPartidas(){  //Devuelve el almacén con todas las partidas jugadas.
        return DatosCompartidosVentanas.listaJuegos;
    }
    
    public void setJ1(Jugador j1){
        DatosCompartidosVentanas.J1=j1;
    }
    
    public Jugador getJ1(){
        return DatosCompartidosVentanas.J1;
    }
    
    public void setJ2(Jugador j2){
        DatosCompartidosVentanas.J2=j2;
    }
    
    public Jugador getJ2(){
        return DatosCompartidosVentanas.J2;
    }
    
    public void crearPartida(){
        this.partida=new Partida(getJ1(), getJ2());
    }
    
    public Partida getPartida(){
        return this.partida;
    }
    
    public Jugador getJugadorAux(){  //Devuelve el usuario aux.
        return DatosCompartidosVentanas.aux;
    }
    
    public boolean existeUsuario(String nombre){
        boolean resultado=false;
        for(Usuario u : this.getUsuarios().getListaUsuarios()){  //Recorre la lista comparando los nombres de usuario.
            if(nombre.equals(u.getNombre())){  //Si los usuarios coinciden.
                resultado=true;
            }
        }        
        return resultado;
    }
    
}
