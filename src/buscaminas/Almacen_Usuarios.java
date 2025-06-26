/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package buscaminas;
import java.util.ArrayList;
import java.io.File;
import java.util.Collections;
import java.util.Comparator;
import java.io.IOException;
import java.io.Serializable;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileNotFoundException;
import java.io.BufferedReader;
import java.io.FileReader;

/**
 *
 * @author a.gonzalezro.2022
 */
public class Almacen_Usuarios implements Serializable{
    
    private static ArrayList<Usuario> listaUsuarios=new ArrayList<Usuario>();
    
    public void cargarListado(File f){  //Carga el archivo con la lista de usuarios y los añade a la lista.
        try(FileInputStream fis=new FileInputStream(f)){
            
            try(ObjectInputStream ois=new ObjectInputStream(fis)){
                int numeroUsuarios=ois.readInt();
                while(fis.available()>0){  //Admite leer todos los datos mientras queden bytes en el fichero.
                    Usuario u=(Usuario) ois.readObject();
                    this.getListaUsuarios().add(u);  //Añade el usuario a la lista.
                }                    
            }catch(ClassNotFoundException e){
                System.out.println("Error: clase no encontrada.");
            }
            
        }catch(IOException e){
            System.out.println("Error: problema al leer el archivo de usuarios.");
        }
    }
     
    public void cargarListadoNuevosUsuarios(File f){  //Carga el archivo con la lista de usuarios y los añade a la lista.
        try{
            FileReader fr=new FileReader(f);  //Prepara el archivo para leerlo.
            BufferedReader br=new BufferedReader(fr);  //Permite leer el archivo.
            int numUsuarios=Integer.parseInt(br.readLine());  //Guarda el número de usuarios que tiene el fichero.
            String linea; 
            ArrayList<String> contenidoLineas=new ArrayList<String>(); //Crea un array para almacenar las lineas del texto.
            //Guardará las lineas del documento en cada posición.
            while((linea=br.readLine())!=null){  //Recorre las lineas del archivo.
                contenidoLineas.add(linea);  //Guarda la linea en el array.
            }
            for(String s: contenidoLineas){  //Recorre la lista de usuarios.
                String[] datosUsuario=s.split("#");  //Separa la información de la linea en un array de strings.
                Jugador nuevoUsuario=new Jugador(datosUsuario[0], datosUsuario[1]);  //Crea el usuario, que será un jugador.
                this.alta(nuevoUsuario);
            }
            br.close();  //Cierra el fichero.
        }catch(IOException e){
            System.out.println("Error de lectura de fichero: " + e.getMessage());
        }
    }    
    
    public void guardarListado(File f){  //Guarda los usuarios almacenados en un fichero.
        
        try(FileOutputStream fos=new FileOutputStream(f)){
            
            try(ObjectOutputStream oos=new ObjectOutputStream(fos)){
                oos.writeInt(this.getListaUsuarios().size());  //Escribe el número de usuarios primero.
                for(Usuario u : this.getListaUsuarios()){  //Recorre todos los usuarios almacenados.
                    oos.writeObject(u);  //Escribe el usuario en el fichero.
                }                   
            }catch(IOException e){
                System.out.println("Error: problema al escribir el archivo de usuarios.");
            }
            
        }catch(FileNotFoundException e){
            System.out.println("Error: archivo no encontrado.");
        }catch(IOException e){
            System.out.println("Error entrada/salida." + e.getMessage());
        }
    }
    /*
    public void guardarListado(File f){  //Añade los usuarios al fichero.
        try{
            FileWriter archivo=new FileWriter(f);
            PrintWriter pw=new PrintWriter(archivo);
            String infoLinea=new String();
            String numUsuarios=Integer.toString(this.getListaUsuarios().size());  //Guarda el número de usuarios que tiene el programa, dato que será guardado al 
            //principio del documento.
            pw.println(numUsuarios);
            
            for(Usuario u : this.getListaUsuarios()){  //Guarda los elementos de la lista de usuarios en el fichero.
                infoLinea=(u.getNombre() + "#" + u.getClave());  //Escribe en cada linea la infromación con el formato correspondiente.
                pw.println(infoLinea);
            }
            
            pw.close();
        }catch(IOException e){
            System.out.println("Error de escritura de fichero: " + e.getMessage());
        }
    }
    */
    
    public void alta(Jugador j){  //Realiza el alta del usuario.
        this.listaUsuarios.add(j);
    }
    
    public void baja(Jugador j){  //Realiza la baja del usuario.
        this.listaUsuarios.remove(j);  //Elimina al jugador de la lista.
    }
    
    public boolean autenticar(Usuario u){  //Devuelve true si el usuario y clave introducidos son correctos.
        return this.listaUsuarios.contains(u);
    }      
    
    public ArrayList<Jugador> clasificacion_por_victorias(){  //Devuelve un array con los jugadores ordenados por victorias.
        
        Comparator<Jugador> comparador=new Comparator<Jugador>(){  //Uso del tipo comparator para ordenar los jugadores.
            @Override
            public int compare(Jugador j1, Jugador j2){  //Compara el número de victorias de cada pareja de jugadores.
                int ganadasJ1=j1.getEstadisticas().getGanadas();
                int ganadasJ2=j2.getEstadisticas().getGanadas();
                return Integer.compare(ganadasJ2, ganadasJ1);  //Devuelve un valor negativo si j2 ha ganado menos que j1.
            }
        };
        
        ArrayList<Jugador> jugadores=new ArrayList<Jugador>();
        //Almacenará los jugadores ordenados.
        
        for(Usuario u : this.getListaUsuarios()){  //Mueve los jugadores a la lista recién creada para tenerlos en una lista diferente.
            if(u instanceof Jugador){  //Si el usuario es un jugador.
                jugadores.add((Jugador) u);
            }
        }
        
        Collections.sort(jugadores, comparador);  //Ordena la lista de jugadores según el elemento comparador definido antes.
        
        return jugadores;
        
    }
    
    public ArrayList<Jugador> clasificacion_por_nombre(){  //Devuelve una lista ordenada de jugadores por su nombre.
        
        ArrayList<Jugador> jugadores=new ArrayList<Jugador>();  //Guarda la lista de los jugadores.
        
        for(Usuario u : this.listaUsuarios){  //Recorre la lista de usuarios.
            if(u instanceof Jugador){  //Si el usuario es un jugador.
                jugadores.add((Jugador)u);  //Castea el usuario y lo guarda como jugador en la lista.
            }
        }
        
        Collections.sort(jugadores, new Comparator<Jugador>(){  //Utiliza el mtodo sort de la clase Collections para ordenar la lista.
                @Override
                public int compare(Jugador j1, Jugador j2){  //Reescribe el método compare para adaptarlo a la clase Jugador.
                    String nombre1=j1.getNombre();
                    String nombre2=j2.getNombre();  //Guarda los nombres de los jugadores a comparar.
                    return nombre1.compareTo(nombre2);  //Compara los nombres de los jugadores para ponerlos en orden.
                }
        });
        
        return jugadores;
        
    }
    
    public ArrayList<Usuario> getListaUsuarios(){  //Devuelve una lista de usuarios.
        return this.listaUsuarios;               
    }
    
}
