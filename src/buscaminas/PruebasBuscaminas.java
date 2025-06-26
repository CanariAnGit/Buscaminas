/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package buscaminas;
import java.util.Scanner;
import java.util.ArrayList;
import java.io.File;

/**
 *
 * @author a.gonzalezro.2022
 */
public class PruebasBuscaminas {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        //Vamos a generar un tablero de prueba para comprobar que funciona el constructor y hace lo que debe.
        
        /*Tablero zonadejuego=new Tablero();  //Se crea al tablero y se rellenan las minas.
        
        for(int i=0; i<16; i++){  //Recorre las filas.
            for(int j=0; j<16; j++){  //Recorre las columnas.
                System.out.print(zonadejuego.getCasilla(i, j).getContenido() + " ");
            }
            System.out.println();
        }
        
        System.out.println("Muy bien, parece que esto funciona, ahora a comprobar si devuelve las casillas adyacentes correctamente.");
        System.out.println("Dime la casilla que quieres comprobar empezando por su coordenada X:");
        Scanner scan=new Scanner(System.in);
        int x=scan.nextInt();
        
        System.out.println();
        System.out.println("Ahora la casilla Y:");
        int y=scan.nextInt();
        
        ArrayList<Casilla> listaAdyacentes=zonadejuego.casillasAdyacentes(zonadejuego.getCasilla(x,y));
        
        System.out.println("Este es el contenido de las casillas adyacentes:");
        
        for(Casilla c: listaAdyacentes){
            System.out.println(c.getContenido());
        }
        */  //Hasta aqui funciona.
        
        /*
        Jugador jugador1=new Jugador("Pedro", "1234");
        Jugador jugador2=new Jugador("Luis", "4321");
        Partida juego=new Partida(jugador1, jugador2);
        
        juego.imprimirTablero();
        */
        
        //Prueba de escritura y lectura de ficheros.
        /*Almacen_Usuarios usuariosAlmacenados=new Almacen_Usuarios();
        File ficheroUsuarios=new File("usuarios.txt");
        Scanner scan=new Scanner(System.in);
        
        System.out.println("Esto es lo que hay guardado ahora mismo en el fichero:");
        usuariosAlmacenados.cargarListado(ficheroUsuarios);  //Carga el fichero y pasa los usuarios al arraylist.
        
        for(Usuario u : usuariosAlmacenados.getListaUsuarios()){
            System.out.println("Nombre: " + u.getNombre() + "   Contraseña: " + u.getClave());
        }
        System.out.print("\n");
        
        /*
        for(int i=0; i<5; i++){
            System.out.println("Escribe a continuacion el nombre del usuario:");
            String nombre=scan.nextLine();
            System.out.println("Ahora escribe la contraseña.");
            String clave=scan.nextLine();
            Jugador nuevoJugador=new Jugador(nombre, clave);
            usuariosAlmacenados.alta(nuevoJugador);
        }    
        */
        
        /*
            for(Usuario u : usuariosAlmacenados.getListaUsuarios()){
            System.out.println("Nombre: " + u.getNombre() + "   Contraseña: " + u.getClave());
        }
        */
        
        /*usuariosAlmacenados.guardarListado(ficheroUsuarios); 
        
        /*
        System.out.println("\n");
        System.out.println("Ahora vamos a comprobar el autenticador de usuarios.");
        System.out.println("Escribe el usuario:");
        String user=scan.nextLine();
        System.out.println("A continuacion, escribe la clave:");
        String key=scan.nextLine();
        
        Usuario u=new Jugador(user, key);
        
        if(usuariosAlmacenados.autenticar(u)==true){
            System.out.println("Sesion iniciada con exito.");
        }else{
            System.out.println("Ha ocurrido un error. Usuario o contraseña incorrectos.");
        }
        */
        
        
        /*
        System.out.println("\n");
        System.out.println("Ahora vamos a comprobar el borrador de usuarios.");
        System.out.println("Escribe el usuario:");
        String user=scan.nextLine();
        System.out.println("A continuacion, escribe la clave:");
        String key=scan.nextLine();
        
        Jugador u=new Jugador(user, key);
        usuariosAlmacenados.baja(u);
        
        for(Usuario use : usuariosAlmacenados.getListaUsuarios()){
            System.out.println("Nombre: " + use.getNombre() + "   Contraseña: " + use.getClave());
        }
        System.out.print("\n"); 
        
        usuariosAlmacenados.guardarListado(ficheroUsuarios);
        
        System.out.println("Esto es lo que hay guardado ahora mismo en el fichero:");
        usuariosAlmacenados.cargarListado(ficheroUsuarios);  //Carga el fichero y pasa los usuarios al arraylist.
        
        for(Usuario up : usuariosAlmacenados.getListaUsuarios()){
            System.out.println("Nombre: " + up.getNombre() + "   Contraseña: " + up.getClave());
        }
        System.out.print("\n");
        */
        
        /*
        System.out.println("Asi queda la lista de usuarios ordenada por nombre:");
        ArrayList<Jugador> ordenada=usuariosAlmacenados.clasificacion_por_nombre();
        
        for(Usuario use : ordenada){
            System.out.println("Nombre: " + use.getNombre() + "   Contraseña: " + use.getClave());
        }
        System.out.print("\n");
        */
        
        /*
        Jugador jugador=new Jugador("Pepe" , "1234");
        jugador.getEstadisticas().editarEstadisticas(true, 26, 15);
        System.out.println(jugador.getEstadisticas().printEstadisticas());
        */
        
        //Prueba de impresión de tablero
        Jugador j1=new Jugador("Pepe", "1234");
        Jugador j2=new Jugador("Luis", "1234");
        Partida juego=new Partida(j1, j2);
        System.out.println(juego.imprimirTablero());
                
    }
    
}
