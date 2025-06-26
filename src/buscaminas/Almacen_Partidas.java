/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package buscaminas;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.Serializable;

/**
 *
 * @author a.gonzalezro.2022
 */
public class Almacen_Partidas implements Serializable{
    
    private static ArrayList<Partida> listaPartidas=new ArrayList<Partida>();
    
    /*
    public void rellenarListaPartidas(File f){  //Devuelve la informacion obtenida a partir de un fichero sobre una partida.
        try{
            FileReader fr=new FileReader(f);  //Prepara el archivo para leerlo.
            BufferedReader br=new BufferedReader(fr);  //Permite leer el archivo.
            String linea; 
            ArrayList<String> contenidoLineas=new ArrayList<String>(); //Crea un array para almacenar las lineas del texto.
            //Guardará las lineas del documento en cada posición.
            while((linea=br.readLine())!=null){  //Recorre las lineas del archivo.
                contenidoLineas.add(linea);  //Guarda la linea en el array.
            }
            for(int i=0; i<contenidoLineas.size(); i++){  //Recorre la lista de partidas.
                if(contenidoLineas.get(i).equals("Jugadores:")){ //Si la linea actual corresponde con un separador de partidas.
                    String[] jugador1Info=contenidoLineas.get(i+1).split("#");  //Obtiene los datos del primer jugador.
                    Jugador j1=new Jugador(jugador1Info[0], jugador1Info[1]);  //Crea dicho jugador.
                    String[] jugador2Info=contenidoLineas.get(i+2).split("#");  //Lee la información de la siguiente linea del siguiente jugador.
                    Jugador j2=new Jugador(jugador2Info[0], jugador2Info[1]);
                    Partida nuevaPartida=new Partida(j1, j2);  //Crea la partida con los dos jugadores.
                    this.listaPartidas.add(nuevaPartida);  //Añade la partida a la lista.
                }else if(contenidoLineas.get(i).equals("Marcador:")){  //Si la linea actual corresponde con el separador de marcador.
                    Partida aux=this.listaPartidas.get(this.listaPartidas.size()-1);  //Seleccionar el último elemento guardado en la lista.
                    Marcador auxMarcador=aux.getMarcador();  //Obtiene el marcador de la partida.
                    String[] puntos=contenidoLineas.get(i+1).split("#");  //Separa las minas de los dos jugadores.
                    auxMarcador.setMarcador(Integer.valueOf(puntos[0]), Integer.valueOf(puntos[1]));  //Rellena los valores del marcador.
                }
                
            }
            br.close();  //Cierra el fichero.
        }catch(IOException e){
            System.out.println("Ha sucedido un error de lectura. " + e.getMessage());
        }
    }    
    */
    
    public ArrayList<Partida> getListaPartida(){
        return this.listaPartidas;
    }
    
    public void cargarFichero(File f){
        try(FileInputStream fis=new FileInputStream(f)){
            
            try(ObjectInputStream ois=new ObjectInputStream(fis)){
                while(fis.available()>0){  //Admite leer todos los datos mientras queden bytes en el fichero.
                    Partida p=(Partida) ois.readObject();
                    this.getListaPartida().add(p);  //Añade el usuario a la lista.
                }                    
            }catch(ClassNotFoundException e){
                System.out.println("Error: clase no encontrada.");
            }
            
        }catch(IOException e){
            System.out.println("Error: problema al leer el archivo de partidas.");
        }
    }
    
    /*
    public void cargarFichero(File nombreFichero){  //Traspasa las partidas del fichero a la lista de partidas.
        try{
            ObjectInputStream entrada=new ObjectInputStream(new FileInputStream(nombreFichero));
            while(true){  //Repite el bucle mientras tenga elementos por copiar.
                Partida partida=(Partida) entrada.readObject();  //Lee un elemento de tipo partida.
                this.listaPartidas.add(partida);  //Guarda la partida en la lista.
            }
        }catch(IOException e){
            System.out.println("Error de entrada/salida: " + e.getMessage());
        }catch(ClassNotFoundException ce){
            System.out.println("Error, la clase no ha sido encontrada. " + ce.getMessage());
        }
    }
    */
    
    public void guardarFichero(File f){  //Guarda las partidas en un fichero binario serializado.
        try(FileOutputStream fos=new FileOutputStream(f)){
            
            try(ObjectOutputStream oos=new ObjectOutputStream(fos)){
                for(Partida p : this.getListaPartida()){  //Recorre todas las partidas almacenadas.
                    oos.writeObject(p);  //Escribe el usuario en el fichero.
                }                   
            }catch(IOException e){
                System.out.println("Error: problema al escribir el archivo de partidas.");
            }
            
        }catch(FileNotFoundException e){
            System.out.println("Error: archivo no encontrado.");
        }catch(IOException e){
            System.out.println("Error entrada/salida." + e.getMessage());
        }
    }
    
    /*
    public void guardarFichero(File nombreFichero){  //Guarda las partidas en un fichero.
        try{
            try (ObjectOutputStream salida = new ObjectOutputStream(new FileOutputStream(nombreFichero))) {
                for (Partida p : this.listaPartidas) {
                    //Recorre la lista de partidas.
                    salida.writeObject(p);  //Escribe el elemento en el fichero con el formato adecuado.
                }
                //Cierra el fichero.
            }
        }catch(IOException e){
            System.out.println("Error de entrada/salida: " + e.getMessage());
        }
    }
    */
    
    /*
    public void tablero_y_movimientos(Partida p, File f){  //Devuelve el tablero y los movimientos de esa partida.
        
        try{
            FileReader fr=new FileReader(f);  //Prepara el archivo para leerlo.
            BufferedReader br=new BufferedReader(fr);  //Permite leer el archivo.
            String linea; 
            ArrayList<String> contenidoLineas=new ArrayList<String>(); //Crea un array para almacenar las lineas del texto.
            //Guardará las lineas del documento en cada posición.
            while((linea=br.readLine())!=null){  //Recorre las lineas del archivo.
                contenidoLineas.add(linea);  //Guarda la linea en el array.
            }        
            
            for(int i=0; i<contenidoLineas.size(); i++){  //Recorre todas las lineas.
                if(contenidoLineas.get(i).equals("Jugadores:")){  //Si está en la linea de descripción de Jugadores.
                    i++;
                    String[] jugador1Info=contenidoLineas.get(i).split("#");  //Obtiene los datos del primer jugador.
                    Jugador j1=new Jugador(jugador1Info[0], jugador1Info[1]);  //Crea dicho jugador.
                    i++;
                    String[] jugador2Info=contenidoLineas.get(i).split("#");  //Lee la información de la siguiente linea del siguiente jugador.
                    Jugador j2=new Jugador(jugador2Info[0], jugador2Info[1]);
                    Partida nuevaPartida=new Partida(j1, j2);  //Crea la partida con los dos jugadores.
                    for(Partida juego : this.listaPartidas){  //Recorre todas las partidas guardadas.
                        if(juego.equals(nuevaPartida)){  //Buscamos la partida en la lista.
                            File ficheroTableroMovi=new File("tablero_y_movimientos.txt");
                            try{
                                FileWriter archivo=new FileWriter(ficheroTableroMovi);
                                PrintWriter pw=new PrintWriter(archivo);
                                pw.println(j1.getUsuario() + "#" + j1.getContraseña());  //Imprime en las primeras lineas los datos de los jugadores.
                                pw.println(j2.getUsuario() + "#" + j2.getContraseña());
                                i++;
                                
                                do{
                                    pw.println(contenidoLineas.get(i));  //Escribe en el fichero el contenido de cada linea.
                                    i++;
                                }while(contenidoLineas.get(i).equals("Marcador:"));                                

                                pw.close();
                            }catch(IOException e){
                                System.out.println("Error de escritura de fichero: " + e.getMessage());
                            }                            
                        }
                    }
                }
            }
            
            br.close();  //Cierra el fichero.
        }catch(IOException e){
            System.out.println("Ha sucedido un error de lectura. " + e.getMessage());
        }
        
    }
    */
    
    public void tablero_y_movimientos(Partida p, File f){  //Imprime en un fichero dado el tablero y los movimientos de la partida.
        for(Partida juego : listaPartidas){  //Recorre la lista de partidas.
            if(juego.equals(p)){  //Se ha encontrado la partida en la lista.
                try{
                FileWriter archivo=new FileWriter(f);
                PrintWriter pw=new PrintWriter(archivo);
                pw.println("Jugadores: ");
                pw.println(juego.getJugadorActual().getUsuario() + "#" + juego.getJugadorActual().getContraseña());
                pw.println(juego.getJugadorEsperando().getUsuario() + "#" + juego.getJugadorEsperando().getContraseña());
                pw.println(juego.imprimirTablero());
                for(Movimiento m : juego.getMovimientos()){  //Recorre la lista de movimientos.
                    pw.println(m.imprimirMovimiento());
                }
                pw.close();
                    
                }catch(IOException e){
                    
                }
            }
        }
    }
    
    public String info_partida(Partida p){  //Imprime la información de nombre de los jugadores y marcador de la partida pasada.
        return ("El marcador quedo:\n" + p.getMarcador().getMinasJ1() + "minas encontradas por " + 
                p.getJugadorActual().getUsuario() + "\n" + p.getMarcador().getMinasJ2() + "minas encontradas por " + 
                p.getJugadorEsperando() + "\n");
    }
    
}


